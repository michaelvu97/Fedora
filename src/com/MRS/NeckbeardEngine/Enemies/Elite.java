package com.MRS.NeckbeardEngine.Enemies;

import com.MRS.NeckbeardEngine.*;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Elite extends Enemy {
  
  private Player target;
  private boolean active;
  private boolean maxSpeedX;
  private int pathTime;
  
  public static int DEFAULT_HITBOX_WIDTH = 96;
  public static int DEFAULT_HITBOX_HEIGHT = 96;
  public static int MAXSHOTCOOLDOWN = 60;
  public static int SWITCH_DIRECTION = 90;
  
  public Elite (State state, int x, int y, double xVelocity, double yVelocity, String projectileType, PowerUpPickup heldPowerUp, long timeLine, Player target) {
    super(state, x, y, xVelocity, yVelocity, projectileType, heldPowerUp, timeLine);
    health = 3;
    hitBox = new HitBox(x, y, DEFAULT_HITBOX_WIDTH, DEFAULT_HITBOX_HEIGHT);
    shotCoolDown = MAXSHOTCOOLDOWN;
    pathTime = 0;
    this.target = target;
    active = false;
    maxSpeedX = true;
  }
  
  public void animate() {
    if ((x >= 0 && x <= Main.WIDTH - DEFAULT_HITBOX_WIDTH) && y >= 0)
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
    
    pathTime--;
  }
  
  public boolean onScreen(){
    return (x > 0 - DEFAULT_HITBOX_WIDTH - 100 && x < Main.WIDTH + 100 && y > 0 - DEFAULT_HITBOX_HEIGHT - 100 && y < Main.HEIGHT + 100);
  }
  
  public void resetShotCoolDown() {
    shotCoolDown = MAXSHOTCOOLDOWN;
  }
  
  public boolean canShoot() {
    if (shotCoolDown <= 0 && (x > target.getX() - 10 && x < target.getX() + 10))
      return true;
    else
      return false;
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
  
  //TEMPORARY. WAITING ON ELITE ASSETS
  @Override
  public void paint (Graphics2D g) {
    BufferedImage img = null;
    
    String workingDir = System.getProperty("user.dir");
    String path = "";
    
    if (state == State.RED) {
      path = workingDir + FileStore.SHIFTER_RED;
    } else if (state == State.BLUE) {
      path = workingDir + FileStore.SHIFTER_BLUE;
    }
    
    try {
      img = ImageIO.read(new File(path));
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    g.drawImage(img, x, y, null);
  }
}
