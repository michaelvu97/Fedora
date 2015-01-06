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
import java.io.InputStream;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JFrame;

import com.MRS.NeckbeardEngine.Projectiles.*;
import com.MRS.NeckbeardEngine.Enemies.*;

public class Game extends JPanel implements KeyListener, MouseListener {
  
  //If the game has begun, this may become deprecated depending on how levels are handled
  public boolean started;
  public boolean paused;
  // for when player loses life
  public int deathClock;
  public int speedBoostClock;
  //Makes sure that changes are only made once per key press
  private boolean stateAlreadySwitched;
  private boolean bombAlreadyDeployed;
  
  //Powerup Drop Chances
  int dropChance,scatterShot,fastShot,rapidShot,bomb,extraShip,speedBoost,shield;
  
  //hit sound cycling
  private int lastPlayedHit = 1;
  
  public KeyInputHandler keyInputHandler; //contains booleans for all keys
  
  //On screen object lists
  public Player player;
  public ArrayList<Enemy> enemies = new ArrayList<Enemy>();
  
  private Sound audioPlayer;
  
  private String currentBGMTag = ""; 
  
  private Font font_bold, font_reg, hauser;
  
  private JFrame context;
  
  BufferedImage img_playerRed = null, img_playerBlue = null, 
    img_mookRed = null, img_mookBlue = null, 
    img_shotBlue = null, img_shotRed = null, 
    img_spaceBG1 = null, img_vignette = null, 
    img_redShield = null, img_blueShield = null,
    img_bombCounter = null;
  
  
  BufferedImage[] img_gg = new BufferedImage[233];
  private int currentGGFrame = 0;
  
  //More on screen object lists
  public ArrayList<Projectile> enemyProjectiles = new ArrayList<Projectile>();
  public ArrayList<Projectile> playerProjectiles = new ArrayList<Projectile>();
  public ArrayList<PowerUpPickup> powerUpPickups = new ArrayList<PowerUpPickup>();
  public ArrayList<Explosion> explosions = new ArrayList<Explosion>();
  public ArrayList<Background> backgrounds = new ArrayList<Background>();
  
  public Level level;
  
  public Game (JFrame context) {
    //Class constructor
    this.context = context;
    keyInputHandler = new KeyInputHandler ();
    initialize();
    
  }
  
  public void initialize () {
    
    player = new Player(360, 800, 3, State.RED);
    level = new Level (this, player);
    
    //Variable setup
    started = false;
    paused = false;
    
    deathClock = 0;
    speedBoostClock = 0;
    
    //chance setup
    
    dropChance = 5;
    fastShot = 15;
    rapidShot = 15;
    scatterShot = 15;
    bomb = 15;
    extraShip = 15;
    speedBoost = 15;
    shield = 15;
    
    stateAlreadySwitched = false;
    bombAlreadyDeployed = false;
    
    addKeyListener(this);
    addMouseListener(this);
    
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    
//    try{
//      InputStream fStream = Game.class.getClassLoader().getResourceAsStream("Hauser.ttf");
//      Font base = Font.createFont(Font.TRUETYPE_FONT, fStream);
//      Font hauser = base.deriveFont(Font.PLAIN, 24);
//      ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Hauser.ttf")));
//    }
//    catch (IOException|FontFormatException e){
//    }
    
    try {
      ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Hauser.ttf")));
    } 
    catch(IOException|FontFormatException e) {
    }
    
//    try {
//      hauser = Font.createFont(Font.TRUETYPE_FONT, new File("Hauser.ttf"));
//      hauser = hauser.deriveFont(32F);
//    }
//    catch (IOException|FontFormatException e){
//    }
    
    hauser = new Font("Hauser", Font.PLAIN, 36);
    
    font_bold = new Font("Consolas", Font.BOLD, 36);
    font_reg = new Font("Consolas", Font.PLAIN, 36);
    
    //base image, order from back to front
    backgrounds.add(new Background(FileStore.BASE_BG,0));
    backgrounds.add(new Background(FileStore.PARTICLE_LAYER_1, 0.03));
    backgrounds.add(new Background(FileStore.PARTICLE_LAYER_2, 0.07));
    backgrounds.add(new Background(FileStore.TEST_MIDGROUND,0.2));
    backgrounds.add(new Background(FileStore.TEST_FOREGROUND,0.4));
    
    //audioPlayer.testSound
    loadImages();
    loadSound();
    currentBGMTag = "BGM1";
    audioPlayer.loop(currentBGMTag, -1);
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
      
      //checks level and wave
      level.check();
      
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
      if (!keyInputHandler.up && !keyInputHandler.down) {
        player.setYVelocity(0);
      }
      if (!keyInputHandler.right && !keyInputHandler.left) {
        player.setXVelocity(0);
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
              playerProjectiles.add((Projectile) new Shot(State.RED, player.getX() + 42, player.getY(), 0, -1 * Projectile.FastShotVelocity, 2000));
            else if (player.getOffensePowerUp() == PowerUp.RAPID_FIRE) 
              playerProjectiles.add((Projectile) new Shot(State.RED, player.getX() + 42, player.getY(), 0, -1 * Projectile.ShotVelocity, 2000));
            else if (player.getOffensePowerUp() == PowerUp.SCATTER_SHOT) {
              playerProjectiles.add((Projectile) new Shot(State.RED, player.getX() + 42, player.getY(), 0, -1 * Projectile.ShotVelocity, 2000));
              playerProjectiles.add((Projectile) new Shot(State.RED, player.getX() + 42, player.getY(), Projectile.ScatterShotXVelocity, -1 * Projectile.ShotVelocity,   2000));
              playerProjectiles.add((Projectile) new Shot(State.RED, player.getX() + 42, player.getY(), -1*Projectile.ScatterShotXVelocity, -1 * Projectile.ShotVelocity,   2000));
            }
            else
              playerProjectiles.add((Projectile) new Shot(State.RED, player.getX() + 42, player.getY(), 0, -1 * Projectile.ShotVelocity,   2000));
          } 
          else {
            if (player.getOffensePowerUp() == PowerUp.FAST_SHOT)
              playerProjectiles.add((Projectile) new Shot(State.BLUE, player.getX() + 42, player.getY(), 0, -1 * Projectile.FastShotVelocity,   2000));
            else if (player.getOffensePowerUp() == PowerUp.RAPID_FIRE) 
              playerProjectiles.add((Projectile) new Shot(State.BLUE, player.getX() + 42, player.getY(), 0, -1 * Projectile.ShotVelocity,   2000));
            else if (player.getOffensePowerUp() == PowerUp.SCATTER_SHOT) {
              playerProjectiles.add((Projectile) new Shot(State.BLUE, player.getX() + 42, player.getY(), 0, -1 * Projectile.ShotVelocity,   2000));
              playerProjectiles.add((Projectile) new Shot(State.BLUE, player.getX() + 42, player.getY(), Projectile.ScatterShotXVelocity, -1 * Projectile.ShotVelocity,   2000));
              playerProjectiles.add((Projectile) new Shot(State.BLUE, player.getX() + 42, player.getY(), -1*Projectile.ScatterShotXVelocity, -1 * Projectile.ShotVelocity,   2000));
            }
            else
              playerProjectiles.add((Projectile) new Shot(State.BLUE, player.getX() + 43, player.getY(), 0, -1 * Projectile.ShotVelocity,   2000));
          }
          if (player.getOffensePowerUp() == PowerUp.RAPID_FIRE)
            player.setShotCoolDown(Player.MAXSHOTCOOLDOWN - 10);
          else
            player.setShotCoolDown(Player.MAXSHOTCOOLDOWN);
          player.setCanShoot(false);
        }
      }
      
      //Enemy Shooting
      //get rid of ifs and replace with getState()s
      for(int i = 0; i < enemies.size(); i++) {
        Enemy e = enemies.get(i);
        if(e.canShoot()) {
          if(e.getProjectileType().equalsIgnoreCase("shot") || e.getProjectileType().equalsIgnoreCase("rapidFire")) {
            
            audioPlayer.play("LASER_SHOT_1");
            
            if(e.getState() == State.RED) 
              enemyProjectiles.add((Projectile) new Shot(State.RED,e.getX()+(e.getHitBox().getWidth()/2)-(Shot.DEFAULT_HITBOX_WIDTH/2),e.getY()+e.getHitBox().getHeight(),0,Projectile.ShotVelocity, 2000));
            
            else if(e.getState() == State.BLUE)
              enemyProjectiles.add((Projectile) new Shot(State.BLUE,e.getX()+(e.getHitBox().getWidth()/2),e.getY()+e.getHitBox().getHeight()-(Shot.DEFAULT_HITBOX_WIDTH/2),0,Projectile.ShotVelocity, 2000));
            
          }
          else if(e.getProjectileType().equalsIgnoreCase("starburtShot")) {
            audioPlayer.play("STARBURT_SHOT");
            
            if(e.getState() == State.RED) {
              enemyProjectiles.add((Projectile) new StarburtShot(State.RED,e.getX()+(e.getHitBox().getWidth()/2)-(Shot.DEFAULT_HITBOX_WIDTH/2),e.getY()+e.getHitBox().getHeight(),0,3, 20000, player));
            } else if(e.getState() == State.BLUE) {
              enemyProjectiles.add((Projectile) new StarburtShot(State.BLUE,e.getX()+(e.getHitBox().getWidth()/2)-(Shot.DEFAULT_HITBOX_WIDTH/2),e.getY()+e.getHitBox().getHeight(),0,3, 20000, player));
            }
          }
          else if(e.getProjectileType().equalsIgnoreCase("laser")) {
            audioPlayer.play("LASERBEAM");
            if(e.getState() == State.RED) {
              if(e.getClass().getSimpleName().equals("Shade"))
                enemyProjectiles.add((Projectile) new Laser(State.RED,e.getX()+(e.getHitBox().getWidth()/2)-15,e.getY()+e.getHitBox().getHeight()-20,e.getXVelocity(),e.getYVelocity(),Direction.DOWN, e));
              else
                enemyProjectiles.add((Projectile) new Laser(State.RED,e.getX()+(e.getHitBox().getWidth()/2)-15,e.getY()+e.getHitBox().getHeight()-20,0,0,Direction.DOWN, e));
            } 
            else if(e.getState() == State.BLUE) {
              if(e.getClass().getSimpleName().equals("Shade"))
                enemyProjectiles.add((Projectile) new Laser(State.BLUE,e.getX()+(e.getHitBox().getWidth()/2)-15,e.getY()+e.getHitBox().getHeight()-20,e.getXVelocity(),e.getYVelocity(),Direction.DOWN, e));
              else
                enemyProjectiles.add((Projectile) new Laser(State.BLUE,e.getX()+(e.getHitBox().getWidth()/2)-15,e.getY()+e.getHitBox().getHeight()-20,0,0,Direction.DOWN, e));
            }
          }
          else if(e.getProjectileType().equalsIgnoreCase("fastShot")) {
            
            audioPlayer.play("LASER_SHOT_1");
            
            if(e.getState() == State.RED) {
              enemyProjectiles.add((Projectile) new Shot(State.RED, e.getX()+(e.getHitBox().getWidth()/2)-(Shot.DEFAULT_HITBOX_WIDTH/2),e.getY()+e.getHitBox().getHeight(), 0, Projectile.FastShotVelocity,   2000));
            } else if(e.getState() == State.BLUE) {
              enemyProjectiles.add((Projectile) new Shot(State.BLUE, e.getX()+(e.getHitBox().getWidth()/2)-(Shot.DEFAULT_HITBOX_WIDTH/2),e.getY()+e.getHitBox().getHeight(), 0, Projectile.FastShotVelocity,   2000));
            }
          }
          else if (e.getProjectileType().equalsIgnoreCase("scatterShot")) {
            
            audioPlayer.play("LASER_SHOT_1");
            
            if(e.getState() == State.RED) {
              enemyProjectiles.add((Projectile) new Shot(State.RED, e.getX()+(e.getHitBox().getWidth()/2)-(Shot.DEFAULT_HITBOX_WIDTH/2),e.getY()+e.getHitBox().getHeight(), 0, Projectile.ShotVelocity,   2000));
              enemyProjectiles.add((Projectile) new Shot(State.RED, e.getX()+(e.getHitBox().getWidth()/2)-(Shot.DEFAULT_HITBOX_WIDTH/2),e.getY()+e.getHitBox().getHeight(), Projectile.ScatterShotXVelocity, Projectile.ShotVelocity,   2000));
              enemyProjectiles.add((Projectile) new Shot(State.RED, e.getX()+(e.getHitBox().getWidth()/2)-(Shot.DEFAULT_HITBOX_WIDTH/2),e.getY()+e.getHitBox().getHeight(), -1*Projectile.ScatterShotXVelocity, Projectile.ShotVelocity,   2000));
            } else if(e.getState() == State.BLUE) {
              enemyProjectiles.add((Projectile) new Shot(State.BLUE, e.getX()+(e.getHitBox().getWidth()/2)-(Shot.DEFAULT_HITBOX_WIDTH/2),e.getY()+e.getHitBox().getHeight(), 0, Projectile.ShotVelocity,   2000));
              enemyProjectiles.add((Projectile) new Shot(State.BLUE, e.getX()+(e.getHitBox().getWidth()/2)-(Shot.DEFAULT_HITBOX_WIDTH/2),e.getY()+e.getHitBox().getHeight(), Projectile.ScatterShotXVelocity, Projectile.ShotVelocity,   2000));
              enemyProjectiles.add((Projectile) new Shot(State.BLUE, e.getX()+(e.getHitBox().getWidth()/2)-(Shot.DEFAULT_HITBOX_WIDTH/2),e.getY()+e.getHitBox().getHeight(), -1*Projectile.ScatterShotXVelocity, Projectile.ShotVelocity,   2000));
            }
          }
          
          e.resetShotCoolDown();
        } else if(e.getShotCoolDown()>0) {
          e.setShotCoolDown(e.getShotCoolDown() - 1); 
        }
      }
      
      //Bombs
      if (player.getBombs() > 0 && keyInputHandler.bomb && !bombAlreadyDeployed) {
        playerProjectiles.add((Projectile) new Bomb(State.BOTH, player.getX() + player.getHitBox().getWidth()/2, player.getY() + player.getHitBox().getHeight()/2, 0, 0, Bomb.DEFAULT_DURATION));
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
        if (p.getClass().getSimpleName().equals("StarburtShot")) {
          StarburtShot s = (StarburtShot) p;
          if (s.getActive() && s.getPlaySound()) {
            s.setPlaySound(false);
            audioPlayer.play("LASERBEAM");
          }
        }
        
        //Remove lasers if parent is kill
        if (p.getClass().getSimpleName().equals("Laser")) {
          Laser laser = (Laser) p;
          Enemy parent = laser.getParent();
          boolean found = false;
          for (int j = 0; j < enemies.size(); j++) {
            if (enemies.get(j) == parent) {
              found = true;
              j = enemies.size();
            }
          }
          
          if (!found) {
            enemyProjectiles.remove(p);
          }
        }
        
        if (p.getY() > Main.HEIGHT + 100 && (p.getClass().getSimpleName().equals("Shot") || p.getClass().getSimpleName().equals("fastShot") || p.getClass().getSimpleName().equals("scatterShot") || p.getClass().getSimpleName().equals("rapidShot"))) {
          enemyProjectiles.remove(p);
        }
        if (p.getKillTime() < System.currentTimeMillis()) {
          if (p.getClass().getSimpleName().equals("StarburtShot")) {
            explosions.add(new Explosion((int)p.getX(), (int)p.getY(), Explosion.EXPLOSIONTYPE_DEATHMEDIUM));
            audioPlayer.play("Explosion1");
          }
          enemyProjectiles.remove(p);
        }
        if ((p.getClass().getSimpleName().equalsIgnoreCase("Shot")&& State.compare(player.getState(), p.getState())&&deathClock<=0)) { //added deathClock for time on invincibility
          HitBox he = p.getHitBox();
          HitBox hp = player.getHitBox();
          if (HitBox.checkCollisionRectRect(he, hp)) {
            explosions.add(new Explosion ((int)player.getX()+Player.DEFAULT_HITBOX_WIDTH/2, (int) player.getY(), Explosion.EXPLOSIONTYPE_HITFLIPPED));
            playRandomHit();
            
            enemyProjectiles.remove(i);
            player.setLives(player.getLives() - 1);
            player.removeOffensePowerUp();
            audioPlayer.play("Explosion1");
            deathClock = 120;
            for(int j = 0; j<player.getDefensePowerUps().size();j++) {
              PowerUp d = player.getDefensePowerUps().get(j);
              if(d == PowerUp.SHIELD){
                player.removeDefensePowerUp(PowerUp.SHIELD);                    // if player has a shield it removes that shield and adds one life
                player.setLives(player.getLives() + 1); 
                deathClock = 0;                                                 // so when it takes away life it return to normal, as if it didn't get hit
              }
            }
          }
        }
        if ((p.getClass().getSimpleName().equalsIgnoreCase("StarburtShot")&& State.compare(player.getState(), p.getState())&&deathClock<=0)) { //added deathClock for time on invincibility
          HitBox he = p.getHitBox();
          HitBox hp = player.getHitBox();
          if (HitBox.checkCollisionRectRect(he, hp)) {
            explosions.add(new Explosion ((int)player.getX()+Player.DEFAULT_HITBOX_WIDTH/2, (int) player.getY(), Explosion.EXPLOSIONTYPE_HITFLIPPED));
            playRandomHit();
            
            player.setLives(player.getLives() - 1);
            player.removeOffensePowerUp();
            audioPlayer.play("Explosion1");
            deathClock = 120;
            for(int j = 0; j<player.getDefensePowerUps().size();j++) {
              PowerUp d = player.getDefensePowerUps().get(j);
              if(d == PowerUp.SHIELD){
                player.removeDefensePowerUp(PowerUp.SHIELD);                    // if player has a shield it removes that shield and adds one life
                player.setLives(player.getLives() + 1); 
                deathClock = 0;                                                 // so when it takes away life it return to normal, as if it didn't get hit
              }
            }
          }
        }
        if ((p.getClass().getSimpleName().equalsIgnoreCase("Laser")&& State.compare(player.getState(), p.getState())&&deathClock<=0)) { //added deathClock for time on invincibility
          HitBox he = p.getHitBox();
          HitBox hp = player.getHitBox();
          if (HitBox.checkCollisionRectRect(he, hp)) {
            explosions.add(new Explosion ((int)player.getX()+Player.DEFAULT_HITBOX_WIDTH/2, (int) player.getY(), Explosion.EXPLOSIONTYPE_HITFLIPPED));
            playRandomHit();
            
            player.setLives(player.getLives() - 1);
            player.removeOffensePowerUp();
            audioPlayer.play("Explosion1");
            deathClock = 120;
            for(int j = 0; j<player.getDefensePowerUps().size();j++) {
              PowerUp d = player.getDefensePowerUps().get(j);
              if(d == PowerUp.SHIELD){
                player.removeDefensePowerUp(PowerUp.SHIELD);                    // if player has a shield it removes that shield and adds one life
                player.setLives(player.getLives() + 1); 
                deathClock = 0;                                                 // so when it takes away life it return to normal, as if it didn't get hit
              }
            }
          }
        }
      }
      for(int i = 0; i<player.getDefensePowerUps().size();i++) {
        PowerUp p = player.getDefensePowerUps().get(i);
        if(p == PowerUp.EXTRA_SHIP) {
          player.setLives(player.getLives() + 1);
          player.removeDefensePowerUp(PowerUp.EXTRA_SHIP);
        }
        if(p == PowerUp.SPEED_BOOST && speedBoostClock<=0) {
          player.MAX_VELOCITY=10;
          speedBoostClock = 1800;
          player.removeDefensePowerUp(PowerUp.SPEED_BOOST);
        }
      }
      
      //speedBoost Timing
      if(speedBoostClock<=0) 
        player.MAX_VELOCITY=5;
      else
        speedBoostClock--;
      
      //deathClock timing
      if(deathClock>0)
        deathClock--;
      
      //enemy movement
      for (int i = 0; i < enemies.size(); i++) {
        Enemy e = enemies.get(i);
//        for(int j = i+1; j < enemies.size();j++) {
//          Enemy f = enemies.get(j);
//          boolean collide = HitBox.checkCollisionRectRect(e.hitBox, f.hitBox);
//          if(collide)
//            Enemy.switchDirections(e,f);
//        }
        if (e.getClass().getSimpleName().equals("Shifter")) {
          com.MRS.NeckbeardEngine.Enemies.Shifter s = (com.MRS.NeckbeardEngine.Enemies.Shifter) e;
          if (s.playSound) {
            audioPlayer.play("SWITCH_STATE");
            s.playSound = false;
          }
        }
        if (e.getClass().getSimpleName().equals("Shade")) {
          Shade shade = (Shade) e;
          if (shade.playSwitchSound) {
            audioPlayer.play("SHADE_SWITCH");
            shade.playSwitchSound = false;
          }
        }
        //collision between Player and Enemy
        if(HitBox.checkCollisionRectRect(e.hitBox,player.getHitBox())&& State.compare(e.state, player.getState()) && deathClock <= 0) {
          if(!e.getClass().getSimpleName().equals("Shade")) {
            enemies.remove(e);
            String explosionType = Explosion.getExplosionTypeByClass(e.getClass().getSimpleName());
            explosions.add(new Explosion((int)e.getX(), (int)e.getY(), explosionType));
            audioPlayer.play("Explosion2");
          }
          else {
            audioPlayer.play("Explosion2");
          }
          explosions.add(new Explosion ((int) player.getX() + Player.DEFAULT_HITBOX_WIDTH/2, (int) player.getY()+Player.DEFAULT_HITBOX_HEIGHT/2, Explosion.EXPLOSIONTYPE_DEATHMEDIUM));
          player.setLives(player.getLives() - 1);
          player.removeOffensePowerUp();
          deathClock = 120;
          for(int j = 0; j < player.getDefensePowerUps().size();j++) {
            PowerUp d = player.getDefensePowerUps().get(j);
            if(d == PowerUp.SHIELD){
              player.removeDefensePowerUp(PowerUp.SHIELD);                    // if player has a shield it removes that shield and adds one life
              player.setLives(player.getLives() + 1); 
              deathClock = 0;                                                 // so when it takes away life it return to normal, as if it didn't get hit
            }
          }
        }
        
        e.move();
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
              
              if(e.getClass().getSimpleName().equals("Shade")){
                com.MRS.NeckbeardEngine.Enemies.Shade s = (com.MRS.NeckbeardEngine.Enemies.Shade) e;
                if(s.shieldHealth <= 10) {
                  e.setHealth(e.getHealth()-(10-s.shieldHealth));
                  s.shieldHealth = 0;
                }
                else
                  s.shieldHealth -=10;
                playerProjectiles.remove(p1);
              }
              
              if (e.getHealth() <= 0) {
                String explosionType = Explosion.getExplosionTypeByClass(e.getClass().getSimpleName());
                explosions.add(new Explosion((int)e.getX(), (int)e.getY(), explosionType));
                PowerUp p = PowerUp.getPowerUp(dropChance,scatterShot,fastShot,rapidShot,bomb,extraShip,speedBoost,shield);
                if(p!=null)
                  powerUpPickups.add(new PowerUpPickup(e.getX(),e.getY(),p));
                String className = e.getClass().getSimpleName();
                if (className.equals(FileStore.ELITE) || className.equals(FileStore.IRIS) || className.equals(FileStore.STARBURT))
                  audioPlayer.play("Explosion2");
                else
                  audioPlayer.play("Explosion1");
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
              
              if(e.getClass().getSimpleName().equals("Shade")){
                com.MRS.NeckbeardEngine.Enemies.Shade s = (com.MRS.NeckbeardEngine.Enemies.Shade) e;
                if(s.shieldHealth > 0){
                  s.setHealth(s.getHealth() + 1);
                  s.shieldHealth--;
                }
              }
              
              explosions.add(new Explosion((int)p1.getX(), (int)p1.getY(), Explosion.EXPLOSIONTYPE_HIT));
              playRandomHit();
              playerProjectiles.remove(p1);
              if (e.getHealth() <= 0) {
                String explosionType = Explosion.getExplosionTypeByClass(e.getClass().getSimpleName());
                explosions.add(new Explosion((int)e.getX(), (int)e.getY(), explosionType));
                PowerUp p = PowerUp.getPowerUp(dropChance,scatterShot,fastShot,rapidShot,bomb,extraShip,speedBoost,shield);
                if(p!=null)
                  powerUpPickups.add(new PowerUpPickup(e.getX(),e.getY(),p));
                
                //Stops Iris lasers for continuing to fire if they are destroyed
                if(e.getProjectileType().equalsIgnoreCase("laser")){
                  for(int k = 0; k < enemyProjectiles.size(); k++) {
                    Projectile r = enemyProjectiles.get(k);
                    
                    if(State.compare(r.getState(),e.getState()) && (e.getX() == (r.getX() + 15 - (e.getHitBox().getWidth()/2))) && (e.getY() == (r.getY() + 20 - e.getHitBox().getHeight())))
                      enemyProjectiles.remove(k);
                  }
                }
                enemies.remove(e);   
                audioPlayer.play("Explosion1");
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
          if (p.getHeldPowerUp() == PowerUp.BOMB && player.getBombs() < 3) {
            player.setBombs(player.getBombs() + 1);
          } else {
            if (p.getHeldPowerUp().getOffensive() && p.getHeldPowerUp() != PowerUp.BOMB) {
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
            audioPlayer.play("SWITCH_STATE");
            stateAlreadySwitched = true;
          } else if (player.getState() == State.BLUE) {
            player.setState(State.RED);
            audioPlayer.play("SWITCH_STATE");
            stateAlreadySwitched = true;
          }
        }
      } else if (stateAlreadySwitched) {
        stateAlreadySwitched = false; 
      }
      
      for(int i = 0; i<backgrounds.size();i++)
        backgrounds.get(i).move();
    }
    else if(player.getLives()==0){
      //The game is over, random explosions must appear
      audioPlayer.stopAllExcept("Explosion1");
//      audioPlayer.stopAll();
//      for(int i = 0; i<30; i++){
//        explosions.add(new Explosion((int)(Main.WIDTH*Math.random()), (int)(Main.HEIGHT*Math.random()), Explosion.EXPLOSIONTYPE_DEATHMEDIUM));
//        
//      }
//      
//      for(int i = 0; i<30; i++){
//        explosions.add(new Explosion((int)(Main.WIDTH*Math.random()), (int)(Main.HEIGHT*Math.random()), Explosion.EXPLOSIONTYPE_HIT));    
//      }
//      for(int i = 0; i<30; i++){
//        explosions.add(new Explosion((int)(Main.WIDTH*Math.random()), (int)(Main.HEIGHT*Math.random()), Explosion.EXPLOSIONTYPE_HITFLIPPED));      
//      }
      //playerProjectiles.add((Projectile) new Bomb(State.BOTH, Main.WIDTH/2, Main.HEIGHT/2, 0, 0, "", Bomb.DEFAULT_DURATION));
      explosions.add(new Explosion (0,0, Explosion.EXPLOSIONTYPE_GAMEOVER));
      explosions.add(new Explosion (player.getX(), player.getY(), Explosion.EXPLOSIONTYPE_DEATHLARGE));
      player.setY(-5000);
      for (int i = 0; i < enemies.size(); i++) {
        Enemy e = enemies.get(i);
        String explosionType = Explosion.getExplosionTypeByClass(e.getClass().getSimpleName());
        explosions.add(new Explosion((int)e.getX(), (int)e.getY(), explosionType));
        enemies.remove(e);
        i--;
      }
      audioPlayer.play("Explosion1");
      audioPlayer.play("Explosion2");
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
    g.setColor(Color.RED);
    g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
    for(int i = 0; i<backgrounds.size();i++)
      backgrounds.get(i).paint(g);        
    
    //Enemies
    for (int i = 0; i < enemies.size(); i++) {
      enemies.get(i).paint(g);
    }
    
    //Enemy shots
    for (int i = 0; i<enemyProjectiles.size(); i++) {
      enemyProjectiles.get(i).paint(g);
    }    
    
    //Paint shots temporary
    for (int i = 0; i < playerProjectiles.size(); i++) {
      playerProjectiles.get(i).paint(g);
    }
    
    //Power-Ups
    for (int i = 0; i < powerUpPickups.size(); i++) {
      powerUpPickups.get(i).paint(g);
    }
    
    
    //flashing when life loss
    if(deathClock<=0 ||(deathClock>0&&deathClock%6==0)){
      player.paint(g);
    }
    
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
    
    //Explosions
    for (int i = 0; i < explosions.size(); i++) {
      explosions.get(i).paint(g);
      if (explosions.get(i).getCompleted()) {
        explosions.remove(i--);
      }
    }
    /*
     * HUD Overlay
     */
    if (player.getLives() >= 0) {
      g.setColor(Color.black);
      g.setFont(font_bold);
      g.drawString("Bombs: " + player.getBombs(), 0, 890);
      g.setFont(font_reg);
      if (player.getState() == State.RED) 
        g.setColor(Color.RED);
      else
        g.setColor(Color.BLUE);
      g.drawString("Bombs: " + player.getBombs(), 0, 890);
      
      switch(player.getBombs()) {
        case 1:
          g.drawImage(img_bombCounter, 0, 900, null);
          break;
        case 2:
          g.drawImage(img_bombCounter, 0, 900, null);
          g.drawImage(img_bombCounter, 64, 900, null);
          break;
        case 3:
          g.drawImage(img_bombCounter, 0, 900, null);
          g.drawImage(img_bombCounter, 64, 900, null);
          g.drawImage(img_bombCounter, 128, 900, null);
          break;
        default:
      }
      
      g.setColor(Color.black);
      g.setFont(hauser);
      g.drawString("Lives: " + player.getLives(), 0, 850);
      if (player.getState() == State.RED) 
        g.setColor(Color.RED);
      else
        g.setColor(Color.BLUE);
      g.setFont(hauser);
      g.drawString("Lives: " + player.getLives(), 0, 850);
      
      g.setColor(Color.black);
      g.setFont(font_reg);
      g.drawString("Current", 580, 850);
      g.drawString("Weapon", 588, 880);
      
      if (player.getState() == State.RED) 
        g.setColor(Color.RED);
      else
        g.setColor(Color.BLUE);
      g.setFont(font_bold);
      g.drawString("Current", 580, 850);
      g.drawString("Weapon", 588, 880);
      g.setColor(Color.WHITE);
      if (player.getOffensePowerUp() == PowerUp.FAST_SHOT) {
        g.drawString("Fast", 605, 910);
        g.drawString("Shot", 605, 940);
      }
      else if (player.getOffensePowerUp() == PowerUp.RAPID_FIRE) {
        g.drawString("Rapid", 599, 910);
        g.drawString("Fire", 605, 940);
      }
      else if (player.getOffensePowerUp() == PowerUp.SCATTER_SHOT) {
        g.drawString("Scatter", 580, 910);
        g.drawString("Shot", 605, 940);
      }
      else {
        g.drawString("Basic", 599, 910);
        g.drawString("Shot", 605, 940);
      }
    }
    //Game Over Animation
    if(player.getLives()<=0){
//      g.setColor(Color.cyan);
//      g.setFont(new Font("Impact", Font.BOLD, 72));
//      g.drawString("GAME OVER",Main.WIDTH/2-150,Main.HEIGHT/2);
      if (currentGGFrame < 121) {
        g.drawImage(img_gg[currentGGFrame], 0, 0, null);
        currentGGFrame++;
      } else {
        g.drawImage(img_gg[currentGGFrame - 1], 0, 0, null);
      }
    }
    //Vignette
    g.drawImage(img_vignette, 0, 0, null);
    
  } 
  
  
  public void playRandomHit() {
    switch (lastPlayedHit) {
      case 1:
        lastPlayedHit = 2;
        break;
      case 2:
        lastPlayedHit = 3;
      case 3:
        lastPlayedHit = 1;
        
    }
    String clip = "METAL_HIT_" + lastPlayedHit;
    audioPlayer.play(clip);
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
      img_vignette = ImageIO.read(new File (workingDir + FileStore.FX_VIGNETTE));
      img_blueShield = ImageIO.read(new File (workingDir + FileStore.BLUE_SHIELD));
      img_redShield = ImageIO.read(new File (workingDir + FileStore.RED_SHIELD));
      img_bombCounter = ImageIO.read(new File (workingDir + FileStore.BOMB_COUNTER));
      String[] ggList = FileStore.GameOverSequence();
      for (int i = 0; i < ggList.length; i++) {
        img_gg[i] = ImageIO.read(new File (workingDir + ggList[i]));
      }
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
      {workingDir + FileStore.LASERBEAM, "LASERBEAM"},
      {workingDir + FileStore.STARBURT_SHOT, "STARBURT_SHOT"},
      {workingDir + FileStore.EXPLOSION_1, "Explosion1"},
      {workingDir + FileStore.EXPLOSION_2, "Explosion2"},
      {workingDir + FileStore.BOMB, "Bomb"},
      {workingDir + FileStore.METAL_HIT_1, "METAL_HIT_1"},
      {workingDir + FileStore.METAL_HIT_2, "METAL_HIT_2"},
      {workingDir + FileStore.METAL_HIT_2, "METAL_HIT_2"},
      {workingDir + FileStore.SWITCH_STATE, "SWITCH_STATE"},
      {workingDir + FileStore.SHADE_SWITCH, "SHADE_SWITCH"}
    };
    
    audioPlayer = new Sound(clips); 
    
    //setting volume
    audioPlayer.setVolume("METAL_HIT_1",-13F);
    audioPlayer.setVolume("METAL_HIT_2",-13F);
    audioPlayer.setVolume("METAL_HIT_2",-13F);
    audioPlayer.setVolume("LASER_SHOT_1", 2F);
    audioPlayer.setVolume("LASERBEAM",  -8F);
    audioPlayer.setVolume("BGM1", -5F);
  }
  
  public void exitGame() {
    audioPlayer.stopAll();
    context.dispatchEvent(new WindowEvent(context, WindowEvent.WINDOW_CLOSING));
  }
}
