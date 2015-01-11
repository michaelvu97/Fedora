/* 
 * PROJECT:LodeStar
 * Source can be found at www.github.com/michaelvu97/LodeStar
 * Authors: Safwan Qazi (Project Manager), Roy Liu, Michael Vu
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

public class Game extends JPanel implements KeyListener {
  
  //If the game has begun, this may become deprecated depending on how levels are handled
  public boolean paused;
  public boolean inMenu;
  
  // for when player loses life
  public int deathClock; //Represents how long before the player can be hit again after getting hit
  public int speedBoostClock;
  
  //Makes sure that changes are only made once per key press
  private boolean stateAlreadySwitched;
  private boolean bombAlreadyDeployed;
  private boolean gameAlreadyPaused;
  
  //Powerup Drop Chances
  int dropChance,scatterShot,fastShot,rapidShot,bomb,extraShip,speedBoost,shield;
  
  //hit sound cycling
  private int lastPlayedHit = 1;
  
  public KeyInputHandler keyInputHandler; //contains booleans for all keys
  
  //On screen object lists
  public Player player;
  public ArrayList<Enemy> enemies = new ArrayList<Enemy>();
  public ArrayList<Projectile> enemyProjectiles = new ArrayList<Projectile>();
  public ArrayList<Projectile> playerProjectiles = new ArrayList<Projectile>();
  public ArrayList<PowerUpPickup> powerUpPickups = new ArrayList<PowerUpPickup>();
  public ArrayList<Explosion> explosions = new ArrayList<Explosion>();
  public ArrayList<Background> backgrounds = new ArrayList<Background>();
  
  public Sound audioPlayer;
  
  //For indicating the current background music
  private String currentBGMTag = ""; 
  
  private Font hauser;
  private Font pauseFont;
  
  private JFrame context;
  
  //Image variables for creating backgrounds and HUD elements
  BufferedImage
    img_spaceBG1 = null, img_vignette = null, 
    img_redShield = null, img_blueShield = null,
    img_bombCounter = null, img_ShotHUDBlue = null,
    img_ShotHUDRed = null, img_FShotHUDBlue = null,
    img_FShotHUDRed = null, img_RFireHUDBlue = null,
    img_RFireHUDRed = null, img_ScatterHUDBlue = null,
    img_ScatterHUDRed = null;
  
  //Represents the current frame in the Game Over Screen
  private int currentGGFrame = 0;
  
  //Represents the current frame in the Start Menu Screen
  private int currentMenuFrame = 0;
  
  public Level level;
  
  //Class constructor
  public Game (JFrame context) {
    this.context = context;
    keyInputHandler = new KeyInputHandler ();
    initialize();   
  }
  
  public void initialize () {
    
    player = new Player(360, 800, 3, State.BLUE, this); //has to be blue because it switches before the game starts and becomes red
    level = new Level (this, player);                   //(caused by the menu space to start)
    
    //Variable setup
    paused = false;
    
    deathClock = 0;
    speedBoostClock = 0;
    
    //Power-up drop chance setup
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
    gameAlreadyPaused = false;
    
    addKeyListener(this);

    //loading fonts
    try {
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      String workingDir = System.getProperty("user.dir");
      ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(workingDir + "\\com\\MRS\\NeckbeardEngine\\Hauser.ttf")));
    } catch (IOException e) {
      e.printStackTrace();
    } catch (FontFormatException e) {
      e.printStackTrace();
    }
    
    hauser = new Font("Hauser", Font.PLAIN, 36);
    pauseFont = new Font("Hauser", Font.PLAIN, 72);
    
    //base image, order from back to front
    backgrounds.add(new Background(FileStore.BASE_BG,0));
    backgrounds.add(new Background(FileStore.PARTICLE_LAYER_1, 0.03));
    backgrounds.add(new Background(FileStore.PARTICLE_LAYER_2, 0.07));
    backgrounds.add(new Background(FileStore.TEST_MIDGROUND,0.2));
    backgrounds.add(new Background(FileStore.TEST_FOREGROUND,0.4));
    
    //audioPlayer.testSound
    loadImages();
    loadSound();
    
    inMenu = true;
    paused = true;
    audioPlayer.loop("MENU_FX", -1);
    currentBGMTag = "BGM1";
  }
  
  public void start () {
    audioPlayer.loop(currentBGMTag, -1);
    inMenu = false;
    paused = false;
    audioPlayer.stopBackground("MENU_FX");
  }
  
  public void step () {
    /*
     * Called by main every time interval
     */
    
    //Escape break
    if (keyInputHandler.escape) {
      exitGame();
    }
    if(keyInputHandler.enter){
      if(!gameAlreadyPaused){
        if(!paused){
          paused = true;
          gameAlreadyPaused = true;
        }
        else{
          paused = false;
          gameAlreadyPaused = true;
        }        
      }
    }
    else if(!keyInputHandler.enter){
      gameAlreadyPaused = false;
    }
    
    
    //start game from menu
    if (inMenu && keyInputHandler.switchState) {
      start();
    }
    
    //main game running
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
      
      //Creates projectiles and a sound when the player shoots. Projectiles differ based on the current Power-up and state
      if (player.canShoot()) {
        if (keyInputHandler.shoot && player.getShotCoolDown() <= 0) {
          audioPlayer.play("LASER_SHOT_1");
          if (player.getState()==State.RED) {
            if (player.getOffensePowerUp() == PowerUp.FAST_SHOT)
              playerProjectiles.add((Projectile) new Shot(State.RED, player.getX() + 42, player.getY(), 0, -1 * Projectile.FastShotVelocity));
            else if (player.getOffensePowerUp() == PowerUp.RAPID_FIRE) 
              playerProjectiles.add((Projectile) new Shot(State.RED, player.getX() + 42, player.getY(), 0, -1 * Projectile.ShotVelocity));
            else if (player.getOffensePowerUp() == PowerUp.SCATTER_SHOT) {
              playerProjectiles.add((Projectile) new Shot(State.RED, player.getX() + 42, player.getY(), 0, -1 * Projectile.ShotVelocity));
              playerProjectiles.add((Projectile) new Shot(State.RED, player.getX() + 42, player.getY(), Projectile.ScatterShotXVelocity, -1 * Projectile.ShotVelocity));
              playerProjectiles.add((Projectile) new Shot(State.RED, player.getX() + 42, player.getY(), -1*Projectile.ScatterShotXVelocity, -1 * Projectile.ShotVelocity));
            }
            else
              playerProjectiles.add((Projectile) new Shot(State.RED, player.getX() + 42, player.getY(), 0, -1 * Projectile.ShotVelocity));
          } 
          else {
            if (player.getOffensePowerUp() == PowerUp.FAST_SHOT)
              playerProjectiles.add((Projectile) new Shot(State.BLUE, player.getX() + 42, player.getY(), 0, -1 * Projectile.FastShotVelocity));
            else if (player.getOffensePowerUp() == PowerUp.RAPID_FIRE) 
              playerProjectiles.add((Projectile) new Shot(State.BLUE, player.getX() + 42, player.getY(), 0, -1 * Projectile.ShotVelocity));
            else if (player.getOffensePowerUp() == PowerUp.SCATTER_SHOT) {
              playerProjectiles.add((Projectile) new Shot(State.BLUE, player.getX() + 42, player.getY(), 0, -1 * Projectile.ShotVelocity));
              playerProjectiles.add((Projectile) new Shot(State.BLUE, player.getX() + 42, player.getY(), Projectile.ScatterShotXVelocity, -1 * Projectile.ShotVelocity));
              playerProjectiles.add((Projectile) new Shot(State.BLUE, player.getX() + 42, player.getY(), -1*Projectile.ScatterShotXVelocity, -1 * Projectile.ShotVelocity));
            }
            else
              playerProjectiles.add((Projectile) new Shot(State.BLUE, player.getX() + 43, player.getY(), 0, -1 * Projectile.ShotVelocity));
          }
          
          //Resets the Player's shot cooldown timer
          if (player.getOffensePowerUp() == PowerUp.RAPID_FIRE)
            player.setShotCoolDown(Player.MAXSHOTCOOLDOWN - 15);
          else if (player.getOffensePowerUp() == PowerUp.SCATTER_SHOT)
            player .setShotCoolDown(Player.MAXSHOTCOOLDOWN + 35);
          else
            player.setShotCoolDown(Player.MAXSHOTCOOLDOWN);
          player.setCanShoot(false);
        }
      }
      
      //Enemy Shooting
      for(int i = 0; i < enemies.size(); i++) {
        Enemy e = enemies.get(i);
        
        //Creates projectiles and a sound when the enemy shoots. Projectiles differ based on the type of enemy and state
        if(e.canShoot()) {
          if(e.getProjectileType().equalsIgnoreCase("shot") || e.getProjectileType().equalsIgnoreCase("rapidFire")) {
            audioPlayer.play("LASER_SHOT_1");
            enemyProjectiles.add((Projectile) new Shot(e.getState(),e.getX()+(e.getHitBox().getWidth()/2)-(Shot.DEFAULT_HITBOX_WIDTH/2),e.getY()+e.getHitBox().getHeight(),0,Projectile.ShotVelocity));
          }
          else if(e.getProjectileType().equalsIgnoreCase("starburtShot")) {
            audioPlayer.play("STARBURT_SHOT");
            enemyProjectiles.add((Projectile) new StarburtShot(e.getState(),e.getX()+(e.getHitBox().getWidth()/2)-(Shot.DEFAULT_HITBOX_WIDTH/2),e.getY()+e.getHitBox().getHeight(),0,3, 20000, player));
          }
          else if(e.getProjectileType().equalsIgnoreCase("fastShot")) {
            audioPlayer.play("LASER_SHOT_1");
            enemyProjectiles.add((Projectile) new Shot(e.getState(), e.getX()+(e.getHitBox().getWidth()/2)-(Shot.DEFAULT_HITBOX_WIDTH/2),e.getY()+e.getHitBox().getHeight(), 0, Projectile.FastShotVelocity));
          }
          else if (e.getProjectileType().equalsIgnoreCase("scatterShot")) {
            audioPlayer.play("LASER_SHOT_1");
            State s = e.getState();
            enemyProjectiles.add((Projectile) new Shot(s, e.getX()+(e.getHitBox().getWidth()/2)-(Shot.DEFAULT_HITBOX_WIDTH/2),e.getY()+e.getHitBox().getHeight(), 0, Projectile.ShotVelocity));
            enemyProjectiles.add((Projectile) new Shot(s, e.getX()+(e.getHitBox().getWidth()/2)-(Shot.DEFAULT_HITBOX_WIDTH/2),e.getY()+e.getHitBox().getHeight(), Projectile.ScatterShotXVelocity, Projectile.ShotVelocity));
            enemyProjectiles.add((Projectile) new Shot(s, e.getX()+(e.getHitBox().getWidth()/2)-(Shot.DEFAULT_HITBOX_WIDTH/2),e.getY()+e.getHitBox().getHeight(), -1*Projectile.ScatterShotXVelocity, Projectile.ShotVelocity));
          }
          
          //Lasers act differently based on which enemy is firing
          else if(e.getProjectileType().equalsIgnoreCase("laser")) {
            if(e.getState() == State.RED) {
              if(e.getClass().getSimpleName().equals("Shade")){
                Laser l = new Laser(State.RED,e.getX()+(e.getHitBox().getWidth()/2)-15,e.getY()+e.getHitBox().getHeight()-7,e.getXVelocity(),e.getYVelocity(), e);
                l.setChargingClock(Laser.CHARGING_TIME-1);
                enemyProjectiles.add((Projectile) l );
                audioPlayer.play("LASERSHADE");
              }
              else{
                enemyProjectiles.add((Projectile) new Laser(State.RED,e.getX()+(e.getHitBox().getWidth()/2)-15,e.getY()+e.getHitBox().getHeight()-20,0,0, e));
                audioPlayer.play("LASERBEAM");
              }
            } 
            else if(e.getState() == State.BLUE) {
              if(e.getClass().getSimpleName().equals("Shade")){
                Laser l = new Laser(State.BLUE,e.getX()+(e.getHitBox().getWidth()/2)-15,e.getY()+e.getHitBox().getHeight()-7,e.getXVelocity(),e.getYVelocity(), e);
                l.setChargingClock(Laser.CHARGING_TIME-1);
                enemyProjectiles.add((Projectile) l );
                audioPlayer.play("LASERSHADE");
              }
              else{
                enemyProjectiles.add((Projectile) new Laser(State.BLUE,e.getX()+(e.getHitBox().getWidth()/2)-15,e.getY()+e.getHitBox().getHeight()-20,0,0, e));
                audioPlayer.play("LASERBEAM");
              }
            }
          }
         
          e.resetShotCoolDown();
        } else if(e.getShotCoolDown()>0) {
          e.setShotCoolDown(e.getShotCoolDown() - 1); 
        }
      }
      
      //Releases a bomb if the player inputs
      if (player.getBombs() > 0 && keyInputHandler.bomb && !bombAlreadyDeployed) {
        playerProjectiles.add((Projectile) new Bomb(State.BOTH, player.getX() + player.getHitBox().getWidth()/2, player.getY() + player.getHitBox().getHeight()/2, 0, 0));
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
        if (p.getY() < 0 && p.getClass().getSimpleName().equals("Shot")) {
          playerProjectiles.remove(p);
        }
        if (p.getClass().getSimpleName().equals("Bomb")) {
          Bomb b = (Bomb) p;
          if (b.getKillTime() < System.currentTimeMillis()) {
            playerProjectiles.remove(p);
          }
        }
      }

      //EnemyProjectiles Movement (Handles behaviour of projectiles when they are offscreen or when they hit a player)
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
                
        //Remove lasers if the source is killed
        if (p.getClass().getSimpleName().equalsIgnoreCase("laser")) {
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
          
          if(p.xVelocity < 0 && p.x <= Shade.DEFAULT_HITBOX_WIDTH/2-15){
            enemyProjectiles.remove(p);
          } else if (p.xVelocity > 0 && p.x >= Main.WIDTH - Shade.DEFAULT_HITBOX_WIDTH/2 -15) {
            enemyProjectiles.remove(p);
            }
        }
        
        //Remove enemy projectiles when off screen
        if (p.getY() > Main.HEIGHT && (p.getClass().getSimpleName().equals("Shot") || p.getClass().getSimpleName().equals("fastShot") || p.getClass().getSimpleName().equals("scatterShot") || p.getClass().getSimpleName().equals("rapidShot"))) {
          enemyProjectiles.remove(p);
        }
        if (p.getClass().getSimpleName().equals("StarburtShot")) {
          StarburtShot sbs = (StarburtShot) p;
          if (sbs.getKillTime() < System.currentTimeMillis()) {
            explosions.add(new Explosion((int)p.getX(), (int)p.getY(), Explosion.EXPLOSIONTYPE_DEATHMEDIUM));
            enemyProjectiles.remove(p);
            audioPlayer.play("Explosion1");
          }
        }
        if (p.getClass().getSimpleName().equals("Laser")) {
          Laser l = (Laser) p;
          if (l.getKillTime() < System.currentTimeMillis()) {
            enemyProjectiles.remove(p);
          }
        }
        
        //Checks enemy projectiles to see if they have hit the player
        if ((p.getClass().getSimpleName().equalsIgnoreCase("Shot")&& State.compare(player.getState(), p.getState())&&deathClock<=0)) { //added deathClock for time on invincibility
          HitBox he = p.getHitBox();
          HitBox hp = player.getHitBox();
          PowerUp a = player.getOffensePowerUp();
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
                player.setOffensePowerUp(a);
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
          PowerUp a = player.getOffensePowerUp();
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
                player.setOffensePowerUp(a);
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
          PowerUp a = player.getOffensePowerUp();
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
                player.setOffensePowerUp(a);
                player.removeDefensePowerUp(PowerUp.SHIELD);                    // if player has a shield it removes that shield and adds one life
                player.setLives(player.getLives() + 1); 
                deathClock = 0;                                                 // so when it takes away life it return to normal, as if it didn't get hit
              }
            }
          }
        }
      }
      
      //Controls the picking up of the Speed Boost and Extra Ship defensive power-ups
      for(int i = 0; i<player.getDefensePowerUps().size();i++) {
        PowerUp p = player.getDefensePowerUps().get(i);
        if(p == PowerUp.EXTRA_SHIP) {
          player.setLives(player.getLives() + 1);
          player.removeDefensePowerUp(PowerUp.EXTRA_SHIP);
        }
        if(p == PowerUp.SPEED_BOOST && speedBoostClock<=0) {
          player.MAX_VELOCITY=7;
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
        if (e.getClass().getSimpleName().equals("Shifter")) {
          com.MRS.NeckbeardEngine.Enemies.Shifter s = (com.MRS.NeckbeardEngine.Enemies.Shifter) e;
          if (s.playSound) {
            audioPlayer.play("ENEMY_SWITCH_STATE");
            s.playSound = false;
          }
        }
        if (e.getClass().getSimpleName().equals("Shade")) {
          Shade shade = (Shade) e;
          if (shade.playSwitchSound) {
            audioPlayer.play("SHADE_SWITCH");
            shade.playSwitchSound = false;
          }
          if (shade.playStateSound) {
            shade.playStateSound = false;
            audioPlayer.play("ENEMY_SWITCH_STATE");
          }
          if (shade.getHealth() < 10 && shade.getHealth() > 0 && !shade.montagePlaying) {
            audioPlayer.loop("MONTAGE_BUILD", -1);
            audioPlayer.stopBackground("BGMS");
            shade.montagePlaying = true;
          }
          if (shade.montagePlaying) {
            audioPlayer.setBackgroundVolume("MONTAGE_BUILD", (float) (-1 * shade.getHealth()));
          }
        }
        
        //collision between Player and Enemy (Plays an explosion and removes both characters unless the enemy is Shade)
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
        
        //Removes enemies not on screen
        if(!e.onScreen())
          enemies.remove(i);
        
        //Controls player projectile behaviour when it hits an enemy
        for (int j = 0; j < playerProjectiles.size(); j++) {
          Projectile p1 = playerProjectiles.get(j);
          
          //Behaviour when the projectile is a bomb
          if (p1.getClass().getSimpleName().equals("Bomb")) {
            RadialHitBox pHitBox = (RadialHitBox) p1.getHitBox();
            HitBox eHitBox = e.getHitBox();
            if (HitBox.checkCollisionRectRadial(eHitBox, pHitBox)  && State.compare(e.getState(), p1.getState())) {
              e.setHealth(e.getHealth() - 1);
              
              //Bombs do not immediately destroy Shade
              if(e.getClass().getSimpleName().equals("Shade")){
               com.MRS.NeckbeardEngine.Enemies.Shade s = (com.MRS.NeckbeardEngine.Enemies.Shade) e;
               // If the health passes a power up drop threshold it drops here instead
               if(s.shieldHealth <= 10) {
                 int tempHealth = e.getHealth();
                 e.setHealth(e.getHealth()-(9-s.shieldHealth));
                 s.shieldHealth = 0;
                 if(tempHealth > 30 && e.getHealth() < 30){
                   PowerUp p = PowerUp.getPowerUp(100,25,25,25,25,0,0,0);
                   powerUpPickups.add(new PowerUpPickup(e.getX(), e.getY(), p));
                 }
                 if(tempHealth > 20 && e.getHealth() < 20){
                   PowerUp p = PowerUp.getPowerUp(100,0,0,0,0,100,0,0);
                   powerUpPickups.add(new PowerUpPickup(e.getX(), e.getY(), p));
                 }
                 if(tempHealth > 10 && e.getHealth() < 10){
                   PowerUp p = PowerUp.getPowerUp(100,15,15,15,0,15,15,20);
                   powerUpPickups.add(new PowerUpPickup(e.getX(), e.getY(), p));
                 }
               }
               else
                 s.shieldHealth -=10;
               playerProjectiles.remove(p1);
              }
              
              //When an enemy dies
              if (e.getHealth() <= 0) {
                
                //Plays silly music when Shade is destroyed
                if (e.getClass().getSimpleName().equals("Shade")) {
                  audioPlayer.stopBackground("MONTAGE_BUILD");
                  audioPlayer.playBackground("MONTAGE_DROP");
                }
                
                //Randomly drops a power up from a destroyed enemy
                PowerUp p = PowerUp.getPowerUp(dropChance,scatterShot,fastShot,rapidShot,bomb,extraShip,speedBoost,shield);
                if(p!=null)
                  powerUpPickups.add(new PowerUpPickup(e.getX(),e.getY(),p));
                
                //Plays an explosion at where the enemy was destroyed
                String explosionType = Explosion.getExplosionTypeByClass(e.getClass().getSimpleName());
                explosions.add(new Explosion((int)e.getX(), (int)e.getY(), explosionType));
                String className = e.getClass().getSimpleName();
                if (className.equals(FileStore.ELITE) || className.equals(FileStore.IRIS) || className.equals(FileStore.STARBURT))
                  audioPlayer.play("Explosion2");
                else
                  audioPlayer.play("Explosion1");
                enemies.remove(e);
              }
            }
          }
          
          //Behaviour when the projectile is a Player shot
          if (p1.getClass().getSimpleName().equals("Shot")) {
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
              
              //Creates a small explosion to indicate a shot has landed
              explosions.add(new Explosion((int)p1.getX(), (int)p1.getY(), Explosion.EXPLOSIONTYPE_HIT));
              playRandomHit();
              playerProjectiles.remove(p1);
              
              //When an enemy dies
              if (e.getHealth() <= 0) {
                
                //Plays silly music when Shade is destroyed
                if (e.getClass().getSimpleName().equals("Shade")) {
                  audioPlayer.stopBackground("MONTAGE_BUILD");
                  audioPlayer.playBackground("MONTAGE_DROP");
                }
                
                //Randomly drops a power up from a destroyed enemy
                PowerUp p = PowerUp.getPowerUp(dropChance,scatterShot,fastShot,rapidShot,bomb,extraShip,speedBoost,shield);
                if(p!=null)
                  powerUpPickups.add(new PowerUpPickup(e.getX(),e.getY(),p));
                
                //Plays an explosion at where the enemy was destroyed
                String explosionType = Explosion.getExplosionTypeByClass(e.getClass().getSimpleName());
                explosions.add(new Explosion((int)e.getX(), (int)e.getY(), explosionType));
                enemies.remove(e);   
                audioPlayer.play("Explosion1");
              }
            }
          }
        }
      }
      
      //player movement
      player.move();
      
      //Prevents the player from flying off the screen
      if (player.getX() < 0)
        player.setX(0);
      else if (player.getX() > (Main.WIDTH - player.DEFAULT_HITBOX_WIDTH))
        player.setX(Main.WIDTH - player.DEFAULT_HITBOX_WIDTH);
      if (player.getY() < 0)
        player.setY(0);
      else if (player.getY() > (Main.HEIGHT - player.DEFAULT_HITBOX_HEIGHT))
        player.setY(Main.HEIGHT - player.DEFAULT_HITBOX_HEIGHT);
      
      //Power Up Behaviour
      for (int i = 0; i < powerUpPickups.size(); i++) {
        PowerUpPickup p = powerUpPickups.get(i);
        
        //Gives the player the corresponding power up when they fly over the icon
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
      
      //Switching States
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
    //Behaviour when player lives are 0
    else if(!paused && player.getLives()==0){
      audioPlayer.stopAllExcept("Explosion1");
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
    //Paints all GUI
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
    
    //Player shots
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
      
      //Sets color based on current player state
      if (player.getState() == State.RED) 
        g.setColor(new Color(220, 42, 42, 170));
      else
        g.setColor(new Color(56, 128, 207, 170));
      g.setFont(hauser);
      
      //Shows remaining bombs with corresponding icons
      g.drawString("Bombs: " + player.getBombs(), 5, 890);
      
      switch(player.getBombs()) {
        case 1:
          g.drawImage(img_bombCounter, 0, 890, null);
          break;
        case 2:
          g.drawImage(img_bombCounter, 0, 885, null);
          g.drawImage(img_bombCounter, 64, 885, null);
          break;
        case 3:
          g.drawImage(img_bombCounter, 0, 890, null);
          g.drawImage(img_bombCounter, 64, 890, null);
          g.drawImage(img_bombCounter, 128, 890, null);
          break;
        default:
      }
      
      //Displays how many lives the player has remaining
      g.drawString("Lives: " + player.getLives(), 5, 850);
      
      //Displays the player's current weapon
      g.drawString("Current", 575, 850);
      g.drawString("Weapon", 575, 880);
      
      //Displays the icon and name for the player's current weapon
      g.setColor(new Color(255, 255, 255, 170));
      if (player.getOffensePowerUp() == PowerUp.FAST_SHOT) {
        if (player.getState() == State.RED) 
          g.drawImage(img_FShotHUDRed, 510, 880, null);
        else
          g.drawImage(img_FShotHUDBlue, 510, 880, null);
        g.drawString("Fast", 605, 910);
        g.drawString("Shot", 605, 940);
      }
      else if (player.getOffensePowerUp() == PowerUp.RAPID_FIRE) {
        if (player.getState() == State.RED) 
          g.drawImage(img_RFireHUDRed, 510, 880, null);
        else
          g.drawImage(img_RFireHUDBlue, 510, 880, null);
        g.drawString("Rapid", 600, 910);
        g.drawString("Fire", 610, 940);
      }
      else if (player.getOffensePowerUp() == PowerUp.SCATTER_SHOT) {
        if (player.getState() == State.RED) 
          g.drawImage(img_ScatterHUDRed, 510, 880, null);
        else
          g.drawImage(img_ScatterHUDBlue, 510, 880, null);
        g.drawString("Scatter", 580, 910);
        g.drawString("Shot", 610, 940);
      }
      else {
        if (player.getState() == State.RED) 
          g.drawImage(img_ShotHUDRed, 510, 880, null);
        else
          g.drawImage(img_ShotHUDBlue, 510, 880, null);
        g.drawString("Basic", 600, 910);
        g.drawString("Shot", 605, 940);
      }
    }
    
    //Game Over Animation
    if(player.getLives()<=0){
      try {
        String workingDir = System.getProperty("user.dir");
        if (currentGGFrame < 120) {
          g.drawImage(ImageIO.read(new File(workingDir + FileStore.GameOverSequence(currentGGFrame++))), 0, 0, null);
        } else {
          g.drawImage(ImageIO.read(new File(workingDir + FileStore.GameOverSequence(currentGGFrame))), 0, 0, null);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    
    //Vignette
    g.drawImage(img_vignette, 0, 0, null);
    
    //Start Menu Screen
    if (inMenu) {
      try {
        BufferedImage menuFrame = ImageIO.read(new File (System.getProperty("user.dir") + FileStore.MenuSequence(currentMenuFrame++)));
        g.drawImage(menuFrame, 0, 0, null);
        g.setColor(Color.DARK_GRAY);
        g.drawString("Arrow Keys to Move", 160, 700);        
        g.drawString("Space to use S.H.I.F.T.",160,750);
        g.drawString("Z to Shoot", 50, 800);        
        g.drawString("X to use Bomb", 400, 800);
        g.drawString("Esc to Quit", 50, 850);
        g.drawString("Enter to Pause",395,850);
        
        
        g.setColor(Color.GRAY);
        g.drawString("Press Space to Start", 150, 920);
      } catch (IOException e) {
        e.printStackTrace();
      }
      if (currentMenuFrame >= 604) {
        currentMenuFrame = 0;
      }
    }
    if(paused && !inMenu){
      g.setFont(pauseFont);
      g.drawImage(img_vignette, 0, 0, null);
      g.drawImage(img_vignette, 0, 0, null);
      g.drawImage(img_vignette, 0, 0, null);
      if(player.getState() == State.RED)
        g.setColor(new Color(220, 42, 42, 170));
      else
        g.setColor(new Color(56, 128, 207, 170));
      g.drawString("PAUSED", 250, 460);
    }
  } 
  
  //Plays a random hit sund
  public void playRandomHit() {
    switch (lastPlayedHit) {
      case 1:
        lastPlayedHit = 2;
        break;
      case 2:
        lastPlayedHit = 3;
        break;
      case 3:
        lastPlayedHit = 1;
        break;
      default:
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
    //Unused
  }
  
  //Pre-loads some GUI images
  public boolean loadImages () {
    boolean noErrors = true;
    try {
      String workingDir = System.getProperty("user.dir"); 
      img_vignette = ImageIO.read(new File (workingDir + FileStore.FX_VIGNETTE));
      img_blueShield = ImageIO.read(new File (workingDir + FileStore.BLUE_SHIELD));
      img_redShield = ImageIO.read(new File (workingDir + FileStore.RED_SHIELD));
      img_bombCounter = ImageIO.read(new File (workingDir + FileStore.BOMB_COUNTER));
      img_ShotHUDBlue = ImageIO.read(new File (workingDir + FileStore.SHOT_INDI_BLUE));
      img_ShotHUDRed = ImageIO.read(new File (workingDir + FileStore.SHOT_INDI_RED));
      img_FShotHUDBlue = ImageIO.read(new File (workingDir + FileStore.FSHOT_INDI_BLUE));
      img_FShotHUDRed = ImageIO.read(new File (workingDir + FileStore.FSHOT_INDI_RED));
      img_RFireHUDBlue = ImageIO.read(new File (workingDir + FileStore.RFIRE_INDI_BLUE));
      img_RFireHUDRed = ImageIO.read(new File (workingDir + FileStore.RFIRE_INDI_RED));
      img_ScatterHUDBlue = ImageIO.read(new File (workingDir + FileStore.SCATTER_INDI_BLUE));
      img_ScatterHUDRed = ImageIO.read(new File (workingDir + FileStore.SCATTER_INDI_RED));
    } catch (IOException e) {
      e.printStackTrace();
      noErrors = false;
    }
    return noErrors;
  }
  
  //Pre-loads sound effects
  public void loadSound () {
    String workingDir = System.getProperty("user.dir");
    String[][] clips = {
      {workingDir + FileStore.LASER_SHOT_1, "LASER_SHOT_1"},
      {workingDir + FileStore.LASERBEAM, "LASERBEAM"},
      {workingDir + FileStore.LASERSHADE, "LASERSHADE"},
      {workingDir + FileStore.STARBURT_SHOT, "STARBURT_SHOT"},
      {workingDir + FileStore.EXPLOSION_1, "Explosion1"},
      {workingDir + FileStore.EXPLOSION_2, "Explosion2"},
      {workingDir + FileStore.BOMB, "Bomb"},
      {workingDir + FileStore.METAL_HIT_1, "METAL_HIT_1"},
      {workingDir + FileStore.METAL_HIT_2, "METAL_HIT_2"},
      {workingDir + FileStore.METAL_HIT_2, "METAL_HIT_2"},
      {workingDir + FileStore.SWITCH_STATE, "SWITCH_STATE"},
      {workingDir + FileStore.ENEMY_SWITCH_STATE, "ENEMY_SWITCH_STATE"},
      {workingDir + FileStore.SHADE_SWITCH, "SHADE_SWITCH"},
      {workingDir + FileStore.POWERUP_GAINED, "POWERUP_GAINED"}
    };
    
    //Background music
    audioPlayer = new Sound(clips); 
    audioPlayer.addSound(workingDir + FileStore.BG_MUSIC_1, "BGM1");
    audioPlayer.addSound(workingDir + FileStore.MONTAGE, "Montage");
    audioPlayer.addSound(workingDir + FileStore.MENU_FX, "MENU_FX");
    audioPlayer.addSound(workingDir + FileStore.MONTAGE_BUILD, "MONTAGE_BUILD");
    audioPlayer.addSound(workingDir + FileStore.MONTAGE_DROP, "MONTAGE_DROP");
    
    
    /*
     * Once all sounds are implemented, adjust these using the
     * master settings
     */
    audioPlayer.setVolume("BOMB", -1F);
    audioPlayer.setVolume("Explosion1", 0F);
    audioPlayer.setVolume("Explosion2", 0F);
    audioPlayer.setVolume("LASER_SHOT_1", 2.1F);
    audioPlayer.setVolume("LASERBEAM",  -1.3F);
    audioPlayer.setVolume("LASERSHADE", -1.3F);
    audioPlayer.setVolume("METAL_HIT_1", -8.1F);
    audioPlayer.setVolume("METAL_HIT_2", -8.1F);
    audioPlayer.setVolume("METAL_HIT_2", -8.1F);
    audioPlayer.setVolume("SHADE_SWITCH", 5.6F);
    audioPlayer.setVolume("STARBURT_SHOT", 0F);
    audioPlayer.setVolume("SWITCH_STATE", 0F);
    audioPlayer.setVolume("ENEMY_SWITCH_STATE", 6F);
    audioPlayer.setVolume("POWERUP_GAINED", 0F);
   
    audioPlayer.setBackgroundVolume("BGM1", -5F);
    audioPlayer.setBackgroundVolume("MONTAGE_BUILD", -10F);
    audioPlayer.setBackgroundVolume("MONTAGE_DROP", 6.0206F);
  }
  
  //Closes the game
  public void exitGame() {
    audioPlayer.stopAll();
    context.dispatchEvent(new WindowEvent(context, WindowEvent.WINDOW_CLOSING));
  }
}
