package com.MRS.NeckbeardEngine.Enemies;

import com.MRS.NeckbeardEngine.*;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Starburt extends Enemy {
  
  public static int DEFAULT_HITBOX_WIDTH = 128;
  public static int DEFAULT_HITBOX_HEIGHT = 128;
  public static int MAXSHOTCOOLDOWN = 180;
  private boolean offScreen;
  
  
  public Starburt (State state, int x, int y, double xVelocity, double yVelocity, String projectileType, PowerUpPickup heldPowerUp, long timeLine, String version) {
    super(state, x, y, xVelocity, yVelocity, projectileType, heldPowerUp, timeLine);
    this.yVelocity = 0;
    health = 2;          
    hitBox = new HitBox (x, y, DEFAULT_HITBOX_WIDTH, DEFAULT_HITBOX_HEIGHT);
    shotCoolDown = MAXSHOTCOOLDOWN;
    offScreen = true;
  }
  
  //@Override
  public void animate () {
    
    if(offScreen){
    }
    
    else{
      if(x < 0)
        xVelocity = xSpeed;
      if(x>Main.WIDTH-DEFAULT_HITBOX_WIDTH)
        xVelocity = -1*xSpeed;
    }
    
    if(x>0&&x<Main.WIDTH-DEFAULT_HITBOX_WIDTH)
      offScreen = false;
  }
  
  public void move () {
    animate();
    x+=xVelocity;
    y+=yVelocity;
    hitBox.setX(x);
    hitBox.setY(y);
  }
  
  public void resetShotCoolDown(){
    shotCoolDown = MAXSHOTCOOLDOWN;
  }
  public boolean onScreen(){
    return (x > 0-DEFAULT_HITBOX_WIDTH-100 && x < Main.WIDTH+100 && y > 0-DEFAULT_HITBOX_HEIGHT-100 && y < Main.HEIGHT+100);
  }
  public boolean canShoot() {
    if(shotCoolDown<=0)
      return true;
    else
      return false;
  }
  @Override
  public void paint (Graphics2D g) {
    BufferedImage img = null;
    
    String workingDir = System.getProperty("user.dir");
    String path = "";
    
    if (state == State.RED) {
      path = workingDir + FileStore.STARBURT_RED;
    } else if (state == State.BLUE) {
      path = workingDir + FileStore.STARBURT_BLUE;
    }
    
    try {
      img = ImageIO.read(new File(path));
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    g.drawImage(img, x, y, null);
  }
}