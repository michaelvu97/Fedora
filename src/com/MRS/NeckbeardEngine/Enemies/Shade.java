/* 
 * PROJECT:LodeStar
 * Source can be found at www.github.com/michaelvu97/LodeStar
 * Authors: Safwan Qazi (Project Manager), Roy Liu, Michael Vu
 * Date: 9/17/14
 * 
 * The final boss of LodeStar. A challenging enemy that can take
 * 40 shots to kill. It cycles its weapons frequently and flys 
 * both quickly and erratically. It gains a shield that can absorb
 * 5 hits when it kills the player. It shifts its state at random intervals.
 */
package com.MRS.NeckbeardEngine.Enemies;

import com.MRS.NeckbeardEngine.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Shade extends Enemy {
  
  public static int DEFAULT_HITBOX_WIDTH = 96;
  public static int DEFAULT_HITBOX_HEIGHT = 96;
  
  //Checks to see when the Shade begins it's behaviour
  private boolean active;
  
  //Used to allow the Shade to have more erratic patterns
  private boolean maxSpeedX;
  
  //Used in the Shade's sweeping laser attack
  private boolean laserMove;
  private boolean laserShoot;
  private boolean laserWipe;
  private boolean laserRight;
  
  public static int MAXSHOTCOOLDOWN = 10;
  public static int SWITCH_DIRECTION = 90;
  
  public static int SWITCH_SHOT = 5*60;
  public static int SWITCH_STATE = 900;
  
  //Time until the Shade's movement is switched
  private int pathTime;
  
  private int shiftTime;
  private int randShiftTime;
  
  //Time until the Shade swaps weapons
  private int shotTime;
  
  public Game g;
  public int shieldHealth;
  public boolean playSwitchSound = false;
  public boolean playStateSound = false;
  public boolean montagePlaying = false;
  
  public Shade (State state, int x, int y, double xVelocity, double yVelocity, String projectileType, Game g) {
    super(state, x, y, xVelocity, yVelocity, projectileType);
    health = 40;          
    hitBox = new HitBox (x, y, DEFAULT_HITBOX_WIDTH, DEFAULT_HITBOX_HEIGHT);
    pathTime = 0;
    shiftTime = SWITCH_STATE;
    shotCoolDown = MAXSHOTCOOLDOWN;
    shotTime = SWITCH_SHOT;
    active = false;
    maxSpeedX = true;
    laserMove = false;
    laserShoot = false;
    laserWipe = false;
    laserRight = false;
    this.g = g;
    shieldHealth = 0;
  }
  
  public void animate() {
    if ((x >= 0 && x <= Main.WIDTH - DEFAULT_HITBOX_WIDTH) && y >= 200 && !active){
      active = true;
      xVelocity = 5;
      yVelocity = 5;
    }    
    
    //Prevents the Shade from flying off screen
    else if (active && !laserMove && !laserShoot && !laserWipe) {
      if (x < 0 || x > Main.WIDTH - DEFAULT_HITBOX_WIDTH)
        xVelocity *= -1;
      if (y < 0 || y > (Main.HEIGHT / 2) - DEFAULT_HITBOX_HEIGHT)
        yVelocity *= -1;
      
      //Randomizes the Shade's movement patterns
      if (pathTime <= 0) {
        if (maxSpeedX)
          xVelocity = (5 * randomizer());
        else
          xVelocity = (Math.random() * 5 * randomizer());
        if (!maxSpeedX)
          yVelocity = (5 * randomizer());
        else
          yVelocity = (Math.random() * 5 * randomizer());
        pathTime = SWITCH_DIRECTION;
        maxSpeedX = !maxSpeedX;
      }
    }
    
    //How does this work?
    else if (laserMove){
      if(x > Main.WIDTH/2-DEFAULT_HITBOX_WIDTH/2){
        
        if(x < Main.WIDTH-DEFAULT_HITBOX_WIDTH) {
          xVelocity = 5;
        }
        else {
          xVelocity = 0;
        }
        
      }
      else {
        
        if(x > 0) {
          xVelocity = -5;
        }
        else {
          xVelocity = 0;
        }
        
      }
      if(y>85){
        if(y > 90) {
          yVelocity = -5;
        }
        else {
          yVelocity = 0;
        }
      }
      else if(y < 90) {
        if(y < 85) {
          yVelocity = 5;
        }
        else {
          yVelocity = 0;
        }
      }
      if(xVelocity == 0 && yVelocity == 0) {
        laserMove = false;
        laserWipe = true;
        laserRight = (x > Main.WIDTH/2);
      }        
    }
    
    //What is this for?
    else if(laserWipe) {
      
      if(laserRight) {
        xVelocity  = -3.46667;
        if(x > Main.WIDTH-DEFAULT_HITBOX_WIDTH - 4)
          laserShoot = true;
        if(x < 0) {
          xVelocity = 0;
          laserWipe = false;
          shotTime = 0;
          shiftTime = SWITCH_STATE;
          active = true;
          xVelocity = 5;
          yVelocity = 5;
          randShiftTime = 0;
        }
      }
      else {
        xVelocity  = 3.46667;
        if(x < 4)
          laserShoot = true;
        if(x > Main.WIDTH-DEFAULT_HITBOX_HEIGHT){
          xVelocity = 0;
          laserWipe = false;
          shotTime = 0;
          shiftTime = SWITCH_STATE;
          active = true;
          xVelocity = 5;
          yVelocity = 5;
        }
      }
      
    }
    
    //Are we still using this?
    if(shiftTime <= 0 && g.playerProjectiles.size() > 0){
      playStateSound = true;
      Projectile p = g.playerProjectiles.get(0);
      if(p.getState() == State.RED)
        state = State.BLUE;      
      else
        state = State.RED;      
      shiftTime = SWITCH_STATE;
    }
    
    //Shifts states when randShiftTime hits 0 unless it is firing a laser
    if(randShiftTime <= 0) {
      playStateSound = true;
      if(state == State.RED && !projectileType.equals("laser"))
        state = State.BLUE;      
      else if(!projectileType.equals("laser"))
        state = State.RED;
      randShiftTime = (int)(180*Math.random()+120);
    }
    
    //Swaps the weapon randomly when shotTime reaches 0
    if(shotTime <= 0) {
      int type = (int) (4*Math.random());
      shotTime = SWITCH_SHOT;
      playSwitchSound = true;
      if (type == 0 && !projectileType.equals("fastShot"))
        projectileType = "fastShot";
      else if (type == 1 && !projectileType.equals("rapidFire"))
        projectileType = "rapidFire";
      else if (type == 2 && !projectileType.equals("scatterShot"))
        projectileType = "scatterShot";
      else if (type == 3){
        projectileType = "laser";
        laserMove = true;
        shiftTime = randShiftTime = shotTime = 1000;
      }
      else
        shotTime = 0;
    }
    
    //What is this for?
    if(g.deathClock == 119)
      shieldHealth  += 5;
    
    pathTime--;
    shiftTime--;
    randShiftTime--;
    shotTime--;    
  }
  
  public void move() {
    animate();
    x += xVelocity;
    y += yVelocity;
    hitBox.setX(x);
    hitBox.setY(y);
  }
  
  //Used in randomizing the Shade's movements
  public int randomizer() {
    int i = (int) (Math.random() * 10);
    if (i <= 4)
      return -1;
    else
      return 1;
  }
  
  public boolean onScreen(){
    return (x > 0 - DEFAULT_HITBOX_WIDTH - 100 && x < Main.WIDTH + 100 && y > 0 - DEFAULT_HITBOX_HEIGHT - 100 && y < Main.HEIGHT + 100);
  }
  
  //Resets shot cooldown differently based on weapon type
  public void resetShotCoolDown() {
    if(projectileType.equalsIgnoreCase("rapidFire"))
      shotCoolDown = MAXSHOTCOOLDOWN - 5;
    else if( projectileType.equalsIgnoreCase("scatterShot")){
      shotCoolDown = 20;
    }
    else
      shotCoolDown = MAXSHOTCOOLDOWN;
  }
  
  //Comment on how laser checks if it can fire
  public boolean canShoot() {
    if (shotCoolDown <= 0 && active && !projectileType.equalsIgnoreCase("laser")){
      return true;
    }
    else if (projectileType.equalsIgnoreCase("laser") && laserShoot) {
      laserShoot = false;
      return true;
    }
    else
      return false;
  }
  
  @Override
  public void paint (Graphics2D g) {
    BufferedImage img = null;
    BufferedImage icon = null;
    BufferedImage shield = null;
    
    String workingDir = System.getProperty("user.dir");
    String path = "";
    String iconPath = "";
    String shieldPath = "";
    
    //Shade Model Images
    if (state == State.RED) {
      path = workingDir + FileStore.SHADE_RED;
      if(shieldHealth > 0)
        shieldPath = workingDir + FileStore.RED_SHIELD;
    } else if (state == State.BLUE) {
      path = workingDir + FileStore.SHADE_BLUE;
      if(shieldHealth > 0)
        shieldPath = workingDir + FileStore.BLUE_SHIELD;
    }
    
    //Shade HUD Images
    if(projectileType.equalsIgnoreCase("fastShot")) {
      iconPath = workingDir + FileStore.SHADE_FAST_SHOT;
    }
    else if(projectileType.equalsIgnoreCase("rapidFire")) {
      iconPath = workingDir + FileStore.SHADE_RAPID_FIRE;
    }
    else if(projectileType.equalsIgnoreCase("scatterShot")) {
      iconPath = workingDir + FileStore.SHADE_SCATTER_SHOT;
    }
    else if(projectileType.equalsIgnoreCase("laser")) {
      iconPath = workingDir + FileStore.SHADE_BOMB;
    }
    
    try {
      
      //Determines what Shade weapon icon is painted
      if(!iconPath.equals(""))
        icon = ImageIO.read(new File(iconPath));
      
      //Will paint a shield if the Shade has one
      if(shieldHealth > 0)
        shield = ImageIO.read(new File(shieldPath));
      
      //Draws the Shade
      img = ImageIO.read(new File(path));
      g.drawImage(img, x, y, null);
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    //Draws the shield and icon
    if(icon != null)
      g.drawImage(icon,50,55,null);
    if(shieldHealth > 0)
      g.drawImage(shield,x - 11, y - 11, null);
    
    //Draws the Shade's health bar
    g.setColor(Color.WHITE);
    g.setFont(new Font("Consolas", Font.BOLD, 20));
    g.drawString("Shade",115,70);
    g.setColor(Color.RED);
    g.fillRect(115,80,12*health,5);
  }
}
