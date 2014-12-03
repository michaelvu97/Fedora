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
    explosions.add(new Explosion(50, 50, Explosion.EXPLOSION_TYPE_HIT));
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
          playerProjectiles.add((Projectile) new Shot(State.RED, player.getX() + 40, player.getY(), 0, -1 * Projectile.ShotVelocity, "swag", new HitBox(0,0,10,10), 30));
        } else {
          playerProjectiles.add((Projectile) new Shot(State.BLUE, player.getX() + 40, player.getY(), 0, -1 * Projectile.ShotVelocity, "swag", new HitBox(0,0,10,10), 30));
        }
        player.setShotCoolDown(Player.MAXSHOTCOOLDOWN);
        player.setCanShoot(false);
      }
    }
    
    //PlayerProjectiles Movement
    for (int i = 0; i < playerProjectiles.size(); i++) {
      Projectile p = playerProjectiles.get(i);
      p.setX((int)(p.getX() + (int)p.getXVelocity()));
      p.setY((int)(p.getY() + (int)p.getYVelocity()));
    }
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
  
  public void paintComponent(Graphics g1) {
    Graphics2D g = (Graphics2D) g1;
    //Paints all gui
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
    g.drawImage(img_spaceBG1, 0, 0, null);
    
    player.paint(g);
    
    //Enemies
    for (int i = 0; i < enemies.size(); i++) {
      Enemy e = enemies.get(i);
      if (e instanceof Mook) {
        if (e.getState()==State.RED) {
          g.drawImage(img_mookRed, e.getX(), e.getY(), null);
        } else {
          g.drawImage(img_mookBlue, e.getX(), e.getY(), null);
        }
      }
    }
    
    for (int i = 0; i < explosions.size(); i++) {
      explosions.get(i).paint(g);
      if (explosions.get(i).getCompleted()) {
        explosions.remove(i--);
      }
    }
    
    //Paint shots temporary
    for (int i = 0; i < playerProjectiles.size(); i++) {
      Projectile p = playerProjectiles.get(i);
      if (p.getState()==State.RED) {
        g.drawImage(img_redGlow, (int)p.getX() - 128 , (int)p.getY() - 128, null);
        g.drawImage(img_shotRed, (int)p.getX(), (int)p.getY(), null);
      } else {
        g.drawImage(img_blueGlow, (int)p.getX() - 128 , (int)p.getY() - 128, null);
        g.drawImage(img_shotBlue, (int)p.getX(), (int)p.getY(), null);
      }
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
      img_playerRed = ImageIO.read(new File (workingDir + FileStore.PLAYER_RED));
      img_playerBlue = ImageIO.read(new File (workingDir + FileStore.PLAYER_BLUE));
      img_mookRed = ImageIO.read(new File (workingDir + FileStore.MOOK_RED));
      img_mookBlue = ImageIO.read(new File (workingDir + FileStore.MOOK_BLUE));
      img_shotBlue = ImageIO.read(new File (workingDir + FileStore.SHOT_BLUE));
      img_shotRed = ImageIO.read(new File (workingDir + FileStore.SHOT_RED));
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
