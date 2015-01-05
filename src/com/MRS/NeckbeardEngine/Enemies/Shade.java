package com.MRS.NeckbeardEngine.Enemies;

import com.MRS.NeckbeardEngine.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.*;

public class Shade extends Enemy {
  
  public static int DEFAULT_HITBOX_WIDTH = 96;
  public static int DEFAULT_HITBOX_HEIGHT = 96;
  
  private boolean active;
  private boolean maxSpeedX;
  
  public static int MAXSHOTCOOLDOWN = 20;
  public static int SWITCH_DIRECTION = 90;
  public static int SWITCH_SHOT = 900;
  public static int SWITCH_STATE = 120;
  
  
  private int pathTime;
  private int shiftTime;
  private int randShiftTime;
  private int shotTime;
  
  public ArrayList<Projectile> playerProjectiles;
    public Shade (State state, int x, int y, double xVelocity, double yVelocity, String projectileType, ArrayList<Projectile> playerProjectiles) {
    super(state, x, y, xVelocity, yVelocity, projectileType);
    this.playerProjectiles = playerProjectiles;
    health = 70;          
    hitBox = new HitBox (x, y, DEFAULT_HITBOX_WIDTH, DEFAULT_HITBOX_HEIGHT);
    xVelocity = 5;
    yVelocity = 5;
    pathTime = 0;
    shiftTime = SWITCH_STATE;
    shotCoolDown = MAXSHOTCOOLDOWN;
    active = false;
    maxSpeedX = true;
  }
  public void animate() {
    if ((x >= 0 && x <= Main.WIDTH - DEFAULT_HITBOX_WIDTH) && y >= 0 && !active)
      active = true;
    
    if (active) {
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
    
    if(shiftTime <= 0 && playerProjectiles.size() > 0){
      Projectile p = playerProjectiles.get(0);
      if(p.getState() == State.RED)
        state = State.BLUE;      
      else
        state = State.RED;      
      
      shiftTime = SWITCH_STATE;
    }
    
    if(randShiftTime <= 0) {
      if(state == State.RED)
        state = State.BLUE;      
      else
        state = State.RED;
      
      randShiftTime = (int)(180*Math.random() +120);
    }
    
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
    if (shotCoolDown <= 0 && active)
      return true;
    else
      return false;
  }
  
  @Override
  public void paint (Graphics2D g) {
    BufferedImage img = null;
    BufferedImage icon = null;
    
    String workingDir = System.getProperty("user.dir");
    String path = "";
    String iconPath = "";
    
    if (state == State.RED) {
      path = workingDir + FileStore.SHADE_RED;
    } else if (state == State.BLUE) {
      path = workingDir + FileStore.SHADE_BLUE;
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
    
    try {
      if(!iconPath.equals(""))
        icon = ImageIO.read(new File(iconPath));
      img = ImageIO.read(new File(path));
    } catch (IOException e) {
      e.printStackTrace();
    }
    if(icon != null)
      g.drawImage(icon,50,55,null);
    g.drawImage(img, x, y, null);
    
    g.setColor(Color.WHITE);
    g.setFont(new Font("Consolas", Font.BOLD, 20));
    g.drawString("Shade",115,70);
    
    g.setColor(Color.RED);
    
    g.fillRect(115,80,7*health,5);
    
  }
  
}
