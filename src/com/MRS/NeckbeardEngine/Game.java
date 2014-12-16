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
import javax.swing.JFrame;

import com.MRS.NeckbeardEngine.Enemies.*;
import com.MRS.NeckbeardEngine.Projectiles.*;

public class Game extends JPanel implements KeyListener, MouseListener {
  
  //If the game has begun, this may become deprecated depending on how levels are handled
  public boolean started;
  public boolean paused;
  // for when player loses life
  public int deathClock;
  
  //Makes sure that changes are only made once per key press
  private boolean stateAlreadySwitched;
  private boolean bombAlreadyDeployed;
  
  public KeyInputHandler keyInputHandler; //contains booleans for all keys
  
  //On screen object lists
  public Player player;
  public ArrayList<Enemy> enemies = new ArrayList<Enemy>();
  
  private Sound audioPlayer;
  
  private Font font_bold, font_reg;
  
  private JFrame context;
  
  BufferedImage img_blueGlow = null, img_redGlow = null, 
                img_playerRed = null, img_playerBlue = null, 
                img_mookRed = null, img_mookBlue = null, 
                img_shotBlue = null, img_shotRed = null, 
                img_spaceBG1 = null, img_vignette = null, 
                img_redShield = null, img_blueShield = null;
  
  //More on screen object lists
  public ArrayList<Projectile> enemyProjectiles = new ArrayList<Projectile>();
  public ArrayList<Projectile> playerProjectiles = new ArrayList<Projectile>();
  public ArrayList<PowerUpPickup> powerUpPickups = new ArrayList<PowerUpPickup>();
  public ArrayList<Explosion> explosions = new ArrayList<Explosion>();
  
  public Level level;
  
  public Game (JFrame context) {
    //Class constructor
    this.context = context;
    keyInputHandler = new KeyInputHandler ();
    initialize();
    
  }
  
  public void initialize () {
    //Variable setup
    started = false;
    paused = false;
    
    deathClock = 0;
    
    stateAlreadySwitched = false;
    bombAlreadyDeployed = false;
    
    addKeyListener(this);
    addMouseListener(this);
    enemies.add(new Mook(State.RED, 0, 70, 1, 15, "Shot", null, 0, "stay"));
    enemies.add(new Mook(State.BLUE, 600, 0, 1, 10, "Shot", null, 0, "stay"));
    
    font_bold = new Font("Consolas", Font.BOLD, 32);
    font_reg = new Font("Consolas", Font.PLAIN, 32);
    
    powerUpPickups.add(new PowerUpPickup(150, 150, PowerUp.FAST_SHOT));
    powerUpPickups.add(new PowerUpPickup(300, 0, PowerUp.BOMB));
    powerUpPickups.add(new PowerUpPickup(200, 0, PowerUp.RAPID_FIRE));
    powerUpPickups.add(new PowerUpPickup(250, 0, PowerUp.SCATTER_SHOT));
    powerUpPickups.add(new PowerUpPickup(400, 0, PowerUp.SHIELD));
    powerUpPickups.add(new PowerUpPickup(450, 0, PowerUp.EXTRA_SHIP));
    
    //audioPlayer.testSound
    player = new Player(500, 500, 3, State.RED);
    player.setBombs(1);
    loadImages();
    loadSound();
    audioPlayer.play("Montage");
  }
  
  public void step () {
    /*
     * Called by main every time interval
     */
    
    //Escape break
    if (keyInputHandler.escape && !paused) {
      paused = true;
      exitGame();
    } else if (paused && keyInputHandler.escape) {
      paused = false;
    }
    
    if (!paused && player.getLives()>0) {
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
      //Player Shooting
      if (!player.canShoot()) {
        player.setShotCoolDown(player.getShotCoolDown() - 1);
        
        if (player.getShotCoolDown() <= 0) {
          player.setCanShoot(true);
        }
      }
      if (player.canShoot()) {
        if (keyInputHandler.shoot && player.getShotCoolDown() <= 0) {
          audioPlayer.play("LASER_SHOT_1");
          if (player.getState()==State.RED) {
            if (player.getOffensePowerUp() == PowerUp.FAST_SHOT)
              playerProjectiles.add((Projectile) new Shot(State.RED, player.getX() + 40, player.getY(), 0, -1 * Projectile.FastShotVelocity, "swag", 2000));
            else if (player.getOffensePowerUp() == PowerUp.RAPID_FIRE) 
              playerProjectiles.add((Projectile) new Shot(State.RED, player.getX() + 40, player.getY(), 0, -1 * Projectile.ShotVelocity, "swag", 2000));
            else if (player.getOffensePowerUp() == PowerUp.SCATTER_SHOT) {
              playerProjectiles.add((Projectile) new Shot(State.RED, player.getX() + 40, player.getY(), 0, -1 * Projectile.ShotVelocity, "swag", 2000));
              playerProjectiles.add((Projectile) new Shot(State.RED, player.getX() + 40, player.getY(), 3, -1 * Projectile.ShotVelocity, "swag", 2000));
              playerProjectiles.add((Projectile) new Shot(State.RED, player.getX() + 40, player.getY(), -3, -1 * Projectile.ShotVelocity, "swag", 2000));
            }
            else
                 playerProjectiles.add((Projectile) new Shot(State.RED, player.getX() + 40, player.getY(), 0, -1 * Projectile.ShotVelocity, "swag", 2000));
            } 
          else {
            if (player.getOffensePowerUp() == PowerUp.FAST_SHOT)
              playerProjectiles.add((Projectile) new Shot(State.BLUE, player.getX() + 40, player.getY(), 0, -1 * Projectile.FastShotVelocity, "swag", 2000));
            else if (player.getOffensePowerUp() == PowerUp.RAPID_FIRE) 
              playerProjectiles.add((Projectile) new Shot(State.BLUE, player.getX() + 40, player.getY(), 0, -1 * Projectile.ShotVelocity, "swag", 2000));
            else if (player.getOffensePowerUp() == PowerUp.SCATTER_SHOT) {
              playerProjectiles.add((Projectile) new Shot(State.BLUE, player.getX() + 40, player.getY(), 0, -1 * Projectile.ShotVelocity, "swag", 2000));
              playerProjectiles.add((Projectile) new Shot(State.BLUE, player.getX() + 40, player.getY(), 3, -1 * Projectile.ShotVelocity, "swag", 2000));
              playerProjectiles.add((Projectile) new Shot(State.BLUE, player.getX() + 40, player.getY(), -3, -1 * Projectile.ShotVelocity, "swag", 2000));
            }
            else
              playerProjectiles.add((Projectile) new Shot(State.BLUE, player.getX() + 40, player.getY(), 0, -1 * Projectile.ShotVelocity, "swag", 2000));
          }
          if (player.getOffensePowerUp() == PowerUp.RAPID_FIRE)
            player.setShotCoolDown(Player.MAXSHOTCOOLDOWN - 10);
          else
            player.setShotCoolDown(Player.MAXSHOTCOOLDOWN);
          player.setCanShoot(false);
        }
      }
      
      //Enemy Shooting
      for(int i = 0; i<enemies.size(); i++) {
        Enemy e = enemies.get(i);
        if(e.canShoot()) {
          audioPlayer.play("LASER_SHOT_1");
          if(e.getProjectileType().equalsIgnoreCase("shot")) {
          if(e.getState() == State.RED) 
            enemyProjectiles.add((Projectile) new Shot(State.RED,e.getX()+(e.getHitBox().getWidth()/2)-(Shot.DEFAULT_HITBOX_WIDTH/2),e.getY()+e.getHitBox().getHeight(),0,Projectile.ShotVelocity,"swag",2000));
          
          else if(e.getState() == State.BLUE)
            enemyProjectiles.add((Projectile) new Shot(State.BLUE,e.getX()+(e.getHitBox().getWidth()/2),e.getY()+e.getHitBox().getHeight()-(Shot.DEFAULT_HITBOX_WIDTH/2),0,Projectile.ShotVelocity,"swag",2000));
          
          }
          e.resetShotCoolDown();
        }
        else if(e.getShotCoolDown()>0) 
          e.setShotCoolDown(e.getShotCoolDown() - 1);        
      }
      
      //Bombs
      if (player.getBombs() > 0 && keyInputHandler.bomb && !bombAlreadyDeployed) {
        playerProjectiles.add((Projectile) new Bomb(State.BOTH, player.getX(), player.getY(), 0, 0, "", Bomb.DEFAULT_DURATION));
        player.setBombs(player.getBombs() - 1);
        audioPlayer.play("Bomb");
        bombAlreadyDeployed = true;
      }
      
      //Bomb key resetting
      if (!keyInputHandler.bomb && bombAlreadyDeployed) {
        bombAlreadyDeployed = false; 
      }
      
      //PlayerProjectiles Movement
      for (int i = 0; i < playerProjectiles.size(); i++) {
        Projectile p = playerProjectiles.get(i);
        p.move();
        if (p.getKillTime() < System.currentTimeMillis()) {
          playerProjectiles.remove(p);
        }
        if (p.getY() < -100 && p.getClass().getSimpleName().equals("Shot")) {
          playerProjectiles.remove(p);
        }
      }
      //EnemyProjectiles Movement
      for(int i = 0; i < enemyProjectiles.size(); i++) {
        Projectile p = enemyProjectiles.get(i);
        p.move();
        if (p.getY() > Main.HEIGHT + 100 && p.getClass().getSimpleName().equals("Shot")) {
          enemyProjectiles.remove(i);
        }
        if (p.getClass().getSimpleName().equals("Shot") && State.compare(player.getState(), p.getState())&&deathClock<=0) { //added deathClock for time on invincibility
          HitBox he = p.getHitBox();
          HitBox hp = player.getHitBox();
          if (HitBox.checkCollisionRectRect(he, hp)) {
            explosions.add(new Explosion ((int) p.getX(), (int) p.getY(), Explosion.EXPLOSIONTYPE_HITFLIPPED));
            enemyProjectiles.remove(i); 
            for(int j = 0; j<player.getDefensePowerUps().size();j++) {
              PowerUp d = player.getDefensePowerUps().get(j);
              if(d == PowerUp.SHIELD){
                player.removeDefensePowerUp(PowerUp.SHIELD);                    // if player has a shield it removes that shield and adds one life
                player.setLives(player.getLives() + 1);                         // so when it takes away life it return to normal, as if it didn't get hit
              }
            }
            player.setLives(player.getLives() - 1);
            deathClock = 120;
          }
        }
      }
      for(int i = 0; i<player.getDefensePowerUps().size();i++) {
        PowerUp p = player.getDefensePowerUp().get(i);
        if(p == PowerUp.EXTRA_SHIP) {
          player.setLife(player.getLive() + 1);
          player.removeDefensePowerUp(PowerUp.EXTRA_SHIP);
        }
        if(p == PowerUp.SPEED_BOOST) {
          player.setXVelocity(2);
          player.setYVelocity(2);
        }
      }
      if(deathClock>0)
        deathClock--;
      //enemy movement
      for (int i = 0; i < enemies.size(); i++) {
        Enemy e = enemies.get(i);
        for(int j = i+1; j < enemies.size();j++) {
          Enemy f = enemies.get(j);
          boolean collide = HitBox.checkCollisionRectRect(e.hitBox, f.hitBox);          
          e.move(collide);
          f.move(collide);          
        }
        if(enemies.size()==1)
          e.move(false);
        if(!e.onScreen())
          enemies.remove(i);
        for (int j = 0; j < playerProjectiles.size(); j++) {
          Projectile p1 = playerProjectiles.get(j);
        //shoot  
          //if bomb
          if (p1.getClass().getSimpleName().equals("Bomb")) {
            RadialHitBox pHitBox = (RadialHitBox) p1.getHitBox();
            HitBox eHitBox = e.getHitBox();
            if (HitBox.checkCollisionRectRadial(eHitBox, pHitBox)  && State.compare(e.getState(), p1.getState())) {
              e.setHealth(e.getHealth() - 1);
              if (e.getHealth() <= 0) {
                explosions.add(new Explosion((int)e.getX(), (int)e.getY(), Explosion.EXPLOSIONTYPE_DEATHMEDIUM));
                enemies.remove(e);
                
              }
            }
          }
          
         //normal shoot 
          if (p1.getClass().getSimpleName().equals("Shot")) {
            //MAKE SURE TO CHANGE CHECK FIRST ONCE RADIAL HITBOXES ARE ADDED
            HitBox eHitBox = e.getHitBox();
            HitBox pHitBox = p1.getHitBox();
            if (HitBox.checkCollisionRectRect(eHitBox,pHitBox)  && State.compare(e.getState(), p1.getState())) {
              e.setHealth(e.getHealth() - 1);
              explosions.add(new Explosion((int)p1.getX(), (int)p1.getY(), Explosion.EXPLOSIONTYPE_HIT));
              //play hit sound
              playerProjectiles.remove(p1);
              if (e.getHealth() <= 0) {
                explosions.add(new Explosion((int)e.getX(), (int)e.getY(), Explosion.EXPLOSIONTYPE_DEATHMEDIUM));
                audioPlayer.play("Explosion1");
                enemies.remove(e);
                
              }
            }
          }
        }
      }
      
      //player movement
      player.move();
      
      if (player.getX() < 0)
        player.setX(0);
      else if (player.getX() > (Main.WIDTH - player.DEFAULT_HITBOX_WIDTH))
        player.setX(Main.WIDTH - player.DEFAULT_HITBOX_WIDTH);
      if (player.getY() < 0)
        player.setY(0);
      else if (player.getY() > (Main.HEIGHT - player.DEFAULT_HITBOX_HEIGHT))
        player.setY(Main.HEIGHT - player.DEFAULT_HITBOX_HEIGHT);
      //Power Up Movement (Probably needs improvement)
      for (int i = 0; i < powerUpPickups.size(); i++) {
        PowerUpPickup p = powerUpPickups.get(i);
        if (HitBox.checkCollisionRectRect(player.getHitBox(), p.getHitBox())) {
          if (p.getHeldPowerUp() == PowerUp.BOMB) {
            player.setBombs(player.getBombs() + 1);
          } else {
            if (p.getHeldPowerUp().getOffensive()) {
              player.setOffensePowerUp(p.getHeldPowerUp());
            } else {
              player.addDefensePowerUp(p.getHeldPowerUp());
            }
          }
          powerUpPickups.remove(i);
        }
        p.move();
      }        
      
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
    }
    else if(player.getLives()==0){
      for(int i = 0; i<20; i++){
        explosions.add(new Explosion((int)(Main.WIDTH*Math.random()), (int)(Main.HEIGHT*Math.random()), Explosion.EXPLOSIONTYPE_DEATHMEDIUM));
        audioPlayer.play("Explosion1");        
      }
       for(int i = 0; i<20; i++){
        explosions.add(new Explosion((int)(Main.WIDTH*Math.random()), (int)(Main.HEIGHT*Math.random()), Explosion.EXPLOSIONTYPE_HIT));
        audioPlayer.play("Explosion1");        
      }
        for(int i = 0; i<20; i++){
        explosions.add(new Explosion((int)(Main.WIDTH*Math.random()), (int)(Main.HEIGHT*Math.random()), Explosion.EXPLOSIONTYPE_HITFLIPPED));
        audioPlayer.play("Explosion1");        
      }
      player.setLives(player.getLives()-1);
    }
    repaint();
  }
  
  /*
   * Controls all painting
   */
  public void paintComponent(Graphics g1) {
    Graphics2D g = (Graphics2D) g1;
    
    //Rendering hint
    RenderingHints rh = new RenderingHints(
                                           RenderingHints.KEY_TEXT_ANTIALIASING,
                                           RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    g.setRenderingHints(rh);
    
    //Paints all gui
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
    g.drawImage(img_spaceBG1, 0, 0, null);
    
   //Shield
    for(int i = 0; i<player.getDefensePowerUps().size();i++){
        PowerUp p = player.getDefensePowerUps().get(i);
        if(p==PowerUp.SHIELD){
          if(player.getState()==State.RED)
            g.drawImage(img_redShield, player.getX()-11,player.getY()-7,null);
          else if(player.getState()==State.BLUE)
            g.drawImage(img_blueShield, player.getX()-11,player.getY()-7,null);
        }
          
      }
     //flashing when life loss
    if(deathClock<=0 ||(deathClock>0&&deathClock%6==0)){
      player.paint(g);
    }
    
    //Enemies
    for (int i = 0; i < enemies.size(); i++) {
      enemies.get(i).paint(g);
    }
    
    //Enemy shots
    for (int i = 0; i<enemyProjectiles.size(); i++) {
      enemyProjectiles.get(i).paint(g);
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
      powerUpPickups.get(i).paint(g);
    }
    
    /*
     * HUD Overlay
     */
    g.setColor(Color.black);
    g.setFont(font_bold);
    g.drawString("Bombs: " + player.getBombs(), 0, 500);
    g.setFont(font_reg);
    if (player.getState() == State.RED) 
      g.setColor(Color.RED);
    else
      g.setColor(Color.BLUE);
    g.drawString("Bombs: " + player.getBombs(), 0, 500);
    
    g.setColor(Color.black);
    g.setFont(font_bold);
    g.drawString("Lives: " + player.getLives(), 0, 600);
    if (player.getState() == State.RED) 
      g.setColor(Color.RED);
    else
      g.setColor(Color.BLUE);
    g.setFont(font_reg);
    g.drawString("Lives: " + player.getLives(), 0, 600);
    
    //Game Over Animation
    if(player.getLives()<=0){
    g.setColor(Color.cyan);
    g.setFont(new Font("Impact", Font.BOLD, 72));
    g.drawString("GAME OVER",Main.WIDTH/2-150,Main.HEIGHT/2);
    }
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
      img_blueShield = ImageIO.read(new File (workingDir + FileStore.BLUE_SHIELD));
      img_redShield = ImageIO.read(new File (workingDir + FileStore.RED_SHIELD));
    } catch (IOException e) {
      e.printStackTrace();
      noErrors = false;
    }
    return noErrors;
  }
  
  public void loadSound () {
    String workingDir = System.getProperty("user.dir");
    
    //TODO put in levels instead of here
    String[][] clips = {
      {workingDir + FileStore.BG_MUSIC_1, "BGM1"},
      {workingDir + FileStore.MONTAGE, "Montage"},
      {workingDir + FileStore.LASER_SHOT_1, "LASER_SHOT_1"},
      {workingDir + FileStore.EXPLOSION_1, "Explosion1"},
      {workingDir + FileStore.BOMB, "Bomb"}
    };
    
    audioPlayer = new Sound(clips); 
  }
  
  public void exitGame() {
    audioPlayer.stopAll();
    context.dispatchEvent(new WindowEvent(context, WindowEvent.WINDOW_CLOSING));
  }
}
