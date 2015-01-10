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
  
  //Constants for various clocks
  public static int MAXSHOTCOOLDOWN = 10;
  public static int SWITCH_DIRECTION = 90;  
  public static int SWITCH_SHOT = 5*60;
  public static int SWITCH_STATE = 900;
  
  //Time until the Shade's movement is switched
  private int pathTime;
  
  //Time until Shade checks projectiles and switches state accordingly
  private int shiftTime;
  
  //Random time until shade switches to opposite state
  private int randShiftTime;
    
  //Time until the Shade swaps weapons
  private int shotTime;
  
  //Uses variables from game so it must be inherited
  public Game g;
  
  //Shield can take up to 5 shots, and stacks, so the health of shield is this
  public int shieldHealth;
  
  //booleans for playing the proper sound
  public boolean playSwitchSound = false;
  public boolean playStateSound = false;
  public boolean montagePlaying = false;
  
  public Shade (State state, int x, int y, double xVelocity, double yVelocity, String projectileType, Game g) {
    super(state, x, y, xVelocity, yVelocity, projectileType);
    health = 40;          
    hitBox = new HitBox (x, y, DEFAULT_HITBOX_WIDTH, DEFAULT_HITBOX_HEIGHT);
    //not erratic yet
    pathTime = 0;
    
    //before it is active, the shade shows it basic abilities by switching projectiles and state a few times
    shiftTime = 60;
    shotTime = 60;
    randShiftTime = 85;
    
    //time until it switches    
    shotCoolDown = MAXSHOTCOOLDOWN;
    
    //not active yet
    active = false;
    
    //X has max speed
    maxSpeedX = true;
    
    // not doing lasers yet
    laserMove = false;
    laserShoot = false;
    laserWipe = false;
    laserRight = false;
    
    //game is game
    this.g = g;
    
    //shield is 0
    shieldHealth = 0;
  }
  
  public void animate() {
    //until it is active and not in the middle of the screen it just moves normally
    if (!active){
      //if it passes middle of screen
      if(y > 400){
        
        //velocities are max 5
      xVelocity = 5;
      yVelocity = -5;
      
      //hitBox goes back to representing boss
      hitBox.setX(x);
      hitBox.setY(y);
      hitBox.setWidth(DEFAULT_HITBOX_WIDTH);
      hitBox.setHeight(DEFAULT_HITBOX_HEIGHT);
      
      //it is now active
      active = true;
      }
      //the hitbox is off screen and small so that player cannot damage shade while it is entering
      else{
        hitBox.setX(-1000);
        hitBox.setY(-1000);
        hitBox.setWidth(0);
        hitBox.setHeight(0);
      }
    }
    
    //Prevents the Shade from flying off screen
    else if (active && !laserMove && !laserShoot && !laserWipe) {
      //if it goes off screen it bounces
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
    
    // if it is active and it has to move towards a corner of the screen to start sweeping
    else if (active && laserMove){
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
      //has to be a range because shade MIGHT be above the health bar
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
      // once it reaches the corner
      if(xVelocity == 0 && yVelocity == 0) {
        //no longer moving to corner
        laserMove = false;
        
        // now moving to other side
        laserWipe = true;
        
        //see if it is on the left or right side of screen
        laserRight = (x > Main.WIDTH/2);
      }        
    }
    
    //if it is active and is wiping across screen for laser
    else if(active && laserWipe) {
      //if it is on the right it moves left
      if(laserRight) {
        xVelocity  = -3.46667;
        if(x > Main.WIDTH-DEFAULT_HITBOX_WIDTH - 4)
          //it is shooting a laser
          laserShoot = true;
        //if it reaches edge it stops, and resets everything to normal to continue game
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
      // same stuff just from left to right
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
    
    //every interval of time it checks the players projectile that is furthest up the screen and switches to the opposite state
    if(shiftTime <= 0 && g.playerProjectiles.size() > 0){
      //plays
      playStateSound = true;
      Projectile p = g.playerProjectiles.get(0);
      if(p.getState() == State.RED)
        state = State.BLUE;      
      else
        state = State.RED;
      //if it active it has the normal wait time
      if(active)
        shiftTime = SWITCH_STATE;
      // if it is entering it has a shorter wait time to show off its abilities
      else
        shiftTime = 60;
    }
    
    //Shifts states when randShiftTime hits 0 unless it is firing a laser
    if(randShiftTime <= 0) {
      // plays sound
      playStateSound = true;
      if(state == State.RED && !projectileType.equals("laser"))
        state = State.BLUE;      
      else if(!projectileType.equals("laser"))
        state = State.RED;
      // random if active
      if(active)
        randShiftTime = (int)(180*Math.random()+120); 
      //shorter if not
      else
        randShiftTime = 85;
    }
    
    //Swaps the weapon randomly when shotTime reaches 0
    if(shotTime <= 0) {
      // generates a random number out of 4
      int type = (int) (4*Math.random());
      // is it's active it waits longer before switching
      if(active)
        shotTime = SWITCH_SHOT;
      // if not it switches fast to show off
      else
        shotTime = 60;
      // plays sound to switch
      playSwitchSound = true;
      
      // if the number is right AND it is not already in that mode it switches
      if (type == 0 && !projectileType.equals("fastShot"))
        projectileType = "fastShot";
      else if (type == 1 && !projectileType.equals("rapidFire"))
        projectileType = "rapidFire";
      else if (type == 2 && !projectileType.equals("scatterShot"))
        projectileType = "scatterShot";
      // it has to be active to use laser, to prevent it from stopping switching while showing off
      else if (type == 3 && active){
        projectileType = "laser";
        // has to move to a corner of the screen
        laserMove = true;
        // shoot and shift times are elongated so it doesnt shoot or switch state while it is lasering
        shiftTime = randShiftTime = shotTime = 1000;
      }
      // if the number generated is already in use it sets time to 0 so it cand generate again
      else
        shotTime = 0;
    }
    
    // if the player's deathClock is at 119 the shield is activate
    // it has to be 119 because in game it subtracts it before it calls for enemy logic, and this way it won't continue
    // adding more than five after player gets hit
    if(g.deathClock == 119)
      shieldHealth  += 5;
    
    // all times go down
    pathTime--;
    shiftTime--;
    randShiftTime--;
    shotTime--;    
  }
  // basic call to animate and then movement
  public void move() {
    animate();
    x += xVelocity;
    y += yVelocity;
    // is it is active the hitBox moves with the enemy, or else it is far away so it cannot be shot at while entering
    if(active){
      hitBox.setX(x);
      hitBox.setY(y);
    }
  }
  
  //Used in randomizing the Shade's movements
  public int randomizer() {
    int i = (int) (Math.random() * 10);
    if (i <= 4)
      return -1;
    else
      return 1;
  }
  // abstract on screen method
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
  
  //checks if it is allowed to shoot
  public boolean canShoot() {
    // when the cooldown is 0 and it is active and it is not shooting a laser it can shoot
    if (shotCoolDown <= 0 && active && !projectileType.equalsIgnoreCase("laser")){
      return true;
    }
    // if it shooting a laser it sets laser shoot to false and return true so that it only shoots one laser and not 
    // millions of them across the screen
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
