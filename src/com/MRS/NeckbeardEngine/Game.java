/* 
 * PROJECT:LodeStar
 * Source can be found at www.github.com/michaelvu97/LodeStar
 * Authors: Qazi Qazi (Project Manager), Roy Liu, Michael Vu
 * Date: 9/17/14
 *
 * This is the panel where all calculations and input/output is performed
 */

package com.MRS.NeckbeardEngine;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.MRS.NeckbeardEngine.Enemies.*;
import com.MRS.NeckbeardEngine.Projectiles.*;

public class Game extends JPanel implements KeyListener, MouseListener {
  
  public boolean started; //If the game has begun, this may become deprecated depending on how levels are handled
  
  private boolean stateAlreadySwitched;
  public KeyInputHandler keyInputHandler; //contains booleans for all keys
  
  //On screen object lists
  public Player player;
  public ArrayList<Enemy> enemies = new ArrayList<Enemy>();
  
  private Sound audioPlayer;
  
  BufferedImage img_blueGlow = null, img_redGlow = null, img_playerRed = null, img_playerBlue = null, img_mookRed = null, img_mookBlue = null, img_shotBlue = null, img_shotRed = null, img_spaceBG1 = null, img_vignette = null;
  
  //More on screen object lists
  public ArrayList<HitScan> hitScans = new ArrayList<HitScan>();
  public ArrayList<Projectile> enemyProjectiles = new ArrayList<Projectile>();
  public ArrayList<Projectile> playerProjectiles = new ArrayList<Projectile>();
  public ArrayList<PowerUpPickup> powerUpPickups = new ArrayList<PowerUpPickup>();
  public ArrayList<Explosion> explosions = new ArrayList<Explosion>();
  
  public Level level;
  
  public Game () {
    //Class constructor
    keyInputHandler = new KeyInputHandler ();
    initialize();
    
  }
  
  public void initialize () {
    //Variable setup
    started = false;
    stateAlreadySwitched = false;
    addKeyListener(this);
    addMouseListener(this);
    enemies.add(new Mook(State.RED, 100, 100, 0, 0, "Shot", null, 0, true));
    enemies.add(new Mook(State.RED, 300, 100, 0, 0, "Shot", null, 0, true));
    enemies.get(1).setHealth(5);
    powerUpPickups.add(new PowerUpPickup(150, 150, null));
    
    //audioPlayer.testSound
    player = new Player(500, 500, 3, State.RED);
    loadImages();
  }
  
  public void step () {
    /*
     * Called by main every time interval
     */
    
    //Propulsion
    if (keyInputHandler.up && !keyInputHandler.down) {
      player.accelerateForward();
    }
    if (keyInputHandler.down && !keyInputHandler.up) {
      player.accelerateBackward();
    }
    if (keyInputHandler.right && !keyInputHandler.left) {
      player.accelerateRight();
    }
    if (keyInputHandler.left && !keyInputHandler.right) {
      player.accelerateLeft();
    }
    
    //Vertical Drag
    if (!keyInputHandler.up && !keyInputHandler.down && player.getYVelocity() != 0) {
      if (Math.abs(player.getYVelocity()) <= Player.DRAG) {
        player.setYVelocity(0);
      } else {
        if (player.getYVelocity() > 0) {
          player.setYVelocity(player.getYVelocity() - Player.DRAG);//just set it to 0
        } else {
          player.setYVelocity(player.getYVelocity() + Player.DRAG);//just set it to 0
        }
      }
    }
    //Horizontal Drag
    if (!keyInputHandler.right && !keyInputHandler.left && player.getXVelocity() != 0) {
      if (Math.abs(player.getXVelocity()) <= Player.DRAG) {
        player.setXVelocity(0);
      } else {
        if (player.getXVelocity() > 0) {
          player.setXVelocity(player.getXVelocity() - Player.DRAG);//just set it to 0
        } else {
          player.setXVelocity(player.getXVelocity() + Player.DRAG);//just set it to 0
        }
      }
    }
    
    //Shooting
    if (!player.canShoot()) {
      player.setShotCoolDown(player.getShotCoolDown() - 1);
      
      if (player.getShotCoolDown() <= 0) {
        player.setCanShoot(true);
      }
    }
    if (player.canShoot()) {
      if (keyInputHandler.shoot && player.getShotCoolDown() <= 0) {
        //Play laser shot sound
        if (player.getState()==State.RED) {
          playerProjectiles.add((Projectile) new Shot(State.RED, player.getX() + 40, player.getY(), 0, -1 * Projectile.ShotVelocity, "swag", 30));
        } else {
          playerProjectiles.add((Projectile) new Shot(State.BLUE, player.getX() + 40, player.getY(), 0, -1 * Projectile.ShotVelocity, "swag", 30));
        }
        player.setShotCoolDown(Player.MAXSHOTCOOLDOWN);
        player.setCanShoot(false);
      }
    }
    
    //PlayerProjectiles Movement
    for (int i = 0; i < playerProjectiles.size(); i++) {
      playerProjectiles.get(i).move();
    }
    
    //enemy movement
    for (int i = 0; i < enemies.size(); i++) {
      Enemy e = enemies.get(i);
      e.move();
      for (int j = 0; j < playerProjectiles.size(); j++) {
        Projectile p1 = playerProjectiles.get(j);
        HitBox eHitBox = e.getHitBox();
        HitBox pHitBox = p1.getHitBox();
        
        //MAKE SURE TO CHANGE CHECK FIRST ONCE RADIAL HITBOXES ARE ADDED
        if (HitBox.checkCollisionRectRect(eHitBox,pHitBox)) {
          e.setHealth(e.getHealth() - 1);
          explosions.add(new Explosion((int)p1.getX(), (int)p1.getY(), Explosion.EXPLOSIONTYPE_HIT));
          playerProjectiles.remove(p1);
          if (e.getHealth() <= 0) {
            explosions.add(new Explosion((int)e.getX(), (int)e.getY(), Explosion.EXPLOSIONTYPE_DEATHMEDIUM));
            enemies.remove(e);
            
          }
        }
      }
    }
    
    //player movement
    player.move();
    
    //Switch State
    if (keyInputHandler.switchState) {
      if (!stateAlreadySwitched) {
        if (player.getState() == State.RED) {
          player.setState(State.BLUE);
          stateAlreadySwitched = true;
        } else if (player.getState() == State.BLUE) {
          player.setState(State.RED);
          stateAlreadySwitched = true;
        }
      }
    } else if (stateAlreadySwitched) {
      stateAlreadySwitched = false; 
    }
    
    repaint();
  }
  
  /*
   * Controls all painting
   */
  public void paintComponent(Graphics g1) {
    Graphics2D g = (Graphics2D) g1;
    //Paints all gui
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
    g.drawImage(img_spaceBG1, 0, 0, null);
    
    player.paint(g);
    
    //Enemies
    for (int i = 0; i < enemies.size(); i++) {
      enemies.get(i).paint(g);
    }
    
    //Explosions
    for (int i = 0; i < explosions.size(); i++) {
      explosions.get(i).paint(g);
      if (explosions.get(i).getCompleted()) {
        explosions.remove(i--);
      }
    }
    
    //Paint shots temporary
    for (int i = 0; i < playerProjectiles.size(); i++) {
      playerProjectiles.get(i).paint(g);
    }
    
    //Power-Ups
    for (int i = 0; i < powerUpPickups.size(); i++) {
      PowerUpPickup p = powerUpPickups.get(i);
      p.paint(g);
    }
    /*
     * HUD Overlay
     */
    
    //Vignette
    g.drawImage(img_vignette, 0, 0, null);
  }
  
  @Override
  public void keyPressed (KeyEvent e) {
    //Called when a key is pressed, sends info to keyInputHandler
    keyInputHandler.sendKeyPressed(e.getKeyCode());
  }
  
  @Override
  public void keyReleased (KeyEvent e) { 
    //Called when a key is released, sends info to keyInputHandler
    keyInputHandler.sendKeyReleased(e.getKeyCode());
  }
  
  @Override
  public void keyTyped (KeyEvent e) {
    //Currently unused
  }
  
  @Override
  public void mouseEntered (MouseEvent e) { 
    //Currently unused
  }
  
  @Override
  public void mouseExited (MouseEvent e) {
    //Currently unused
  }
  
  @Override
  public void mousePressed (MouseEvent e) {
    //Currently unuused
  }
  
  @Override 
  public void mouseReleased (MouseEvent e) {
    //Currently unused
  }
  
  @Override
  public void mouseClicked (MouseEvent e) {
    //Currently unused
  }
  
  //remove some of this stuff
  public boolean loadImages () {
    boolean noErrors = true;
    try {
      String workingDir = System.getProperty("user.dir"); 
      img_blueGlow = ImageIO.read(new File (workingDir + FileStore.FX_BLUE_GLOW));
      img_redGlow = ImageIO.read(new File (workingDir + FileStore.FX_RED_GLOW));
      img_spaceBG1 = ImageIO.read(new File (workingDir + FileStore.SPACE_BG_1));
      img_vignette = ImageIO.read(new File (workingDir + FileStore.FX_VIGNETTE));
    } catch (IOException e) {
      e.printStackTrace();
      noErrors = false;
    }
    return noErrors;
  }
  
  public void loadSound () {
    String workingDir = System.getProperty("user.dir");
    
    //TODO put in levels instead of here
    String[] clips = {workingDir + FileStore.BG_MUSIC_1};
    
    audioPlayer = new Sound(clips); 
  }
}
