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
  
  private boolean active;
  private boolean maxSpeedX;
  private boolean laserMove;
  private boolean laserShoot;
  private boolean laserWipe;
  private boolean laserRight;
  
  public static int MAXSHOTCOOLDOWN = 20;
  public static int SWITCH_DIRECTION = 90;

  public static int SWITCH_SHOT = 5*60;
  public static int SWITCH_STATE = 900;
  
  private int pathTime;
  private int shiftTime;
  private int randShiftTime;
  private int shotTime;
  
  public Game g;
  public int shieldHealth;
  public boolean playSwitchSound = false;
  public boolean montagePlaying = false;
  
  public Shade (State state, int x, int y, double xVelocity, double yVelocity, String projectileType, Game g) {
    super(state, x, y, xVelocity, yVelocity, projectileType);
    health = 70;          
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
    else if (active && !laserMove && !laserShoot && !laserWipe) {
      if (x < 0 || x > Main.WIDTH - DEFAULT_HITBOX_WIDTH)
        xVelocity *= -1;
      if (y < 0 || y > (Main.HEIGHT / 2) - DEFAULT_HITBOX_HEIGHT)
        yVelocity *= -1;
      
      if (pathTime <= 0) {
        if (maxSpeedX)
          xVelocity = (3 * randomizer());
        else
          xVelocity = (Math.random() * 3 * randomizer());
        if (!maxSpeedX)
          yVelocity = (3 * randomizer());
        else
          yVelocity = (Math.random() * 3 * randomizer());
        pathTime = SWITCH_DIRECTION;
        maxSpeedX = !maxSpeedX;
      }
    }
    else if (laserMove){
      if(x > Main.WIDTH/2-DEFAULT_HITBOX_WIDTH/2){
        
        if(x < Main.WIDTH-DEFAULT_HITBOX_WIDTH) {
          xVelocity = 4;
        }
        else {
          xVelocity = 0;
        }
        
      }
      else {
        
        if(x > 0) {
          xVelocity = -4;
        }
        else {
          xVelocity = 0;
        }
        
      }
      if(y>85){
        if(y > 90) {
          yVelocity = -4;
        }
        else {
          yVelocity = 0;
        }
      }
      else if(y < 90) {
        if(y < 85) {
          yVelocity = 4;
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
    
    if(shiftTime <= 0 && g.playerProjectiles.size() > 0){
      Projectile p = g.playerProjectiles.get(0);
      if(p.getState() == State.RED)
        state = State.BLUE;      
      else
        state = State.RED;      
      
      shiftTime = SWITCH_STATE;
    }
    
    if(randShiftTime <= 0) {
      if(state == State.RED && !projectileType.equals("laser"))
        state = State.BLUE;      
      else if(!projectileType.equals("laser"))
        state = State.RED;
      randShiftTime = (int)(180*Math.random()+120);
    }
    
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
  
  public void resetShotCoolDown() {
    if(projectileType.equalsIgnoreCase("rapidFire"))
      shotCoolDown = MAXSHOTCOOLDOWN-10;
    else if( projectileType.equalsIgnoreCase("scatterShot")){
      shotCoolDown = 50;
    }
    else
      shotCoolDown = MAXSHOTCOOLDOWN;
  }
  
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
    
    if (state == State.RED) {
      path = workingDir + FileStore.SHADE_RED;
      if(shieldHealth > 0)
        shieldPath = workingDir + FileStore.RED_SHIELD;
    } else if (state == State.BLUE) {
      path = workingDir + FileStore.SHADE_BLUE;
      if(shieldHealth > 0)
        shieldPath = workingDir + FileStore.BLUE_SHIELD;
    }
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
      if(!iconPath.equals(""))
        icon = ImageIO.read(new File(iconPath));
      if(shieldHealth > 0)
        shield = ImageIO.read(new File(shieldPath));
      img = ImageIO.read(new File(path));
    } catch (IOException e) {
      e.printStackTrace();
    }
    g.drawImage(img, x, y, null);
    if(icon != null)
      g.drawImage(icon,50,55,null);
    if(shieldHealth > 0)
      g.drawImage(shield,x - 11, y - 11, null);
    
    
    g.setColor(Color.WHITE);
    g.setFont(new Font("Consolas", Font.BOLD, 20));
    g.drawString("Shade",115,70);
    
    g.setColor(Color.RED);
    
    g.fillRect(115,80,7*health,5);
    
  }
  
}
