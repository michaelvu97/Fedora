package com.MRS.NeckbeardEngine.Enemies;

import com.MRS.NeckbeardEngine.*;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Mook extends Enemy {
  
  public static int DEFAULT_HITBOX_WIDTH = 60;
  public static int DEFAULT_HITBOX_HEIGHT = 60;
  private String version;
  public static int MAXSHOTCOOLDOWN = 120;
  public int shootPos; // where you want it to shoot/stop
  
  public Mook (State state, int x, int y, double xVelocity, double yVelocity, String projectileType, PowerUpPickup heldPowerUp, long timeLine, String version, int shootPos) {
    super(state, x, y, xVelocity, yVelocity, projectileType, heldPowerUp, timeLine);
    health = 1;          
    hitBox = new HitBox (x, y, DEFAULT_HITBOX_WIDTH, DEFAULT_HITBOX_HEIGHT);
    this.version = version;
    shotCoolDown = MAXSHOTCOOLDOWN;
    this.shootPos = shootPos;    
  }
  
  //@Override
  public void animate () {
    
    //Todo: add shooting to animate.
    
    if(version.equalsIgnoreCase("stay")){
      if(xVelocity>0&&x<=shootPos){
        xVelocity = xSpeed;
        yVelocity -= (yVelocity/20);        
      }
      else if(xVelocity<0&&x>=shootPos){
        xVelocity = -1*xSpeed;
        yVelocity -= (yVelocity/20);        
      }
      else{
        xVelocity = 0;
        yVelocity = 0;
      }
      
    }
    if(version.equalsIgnoreCase("leave")){
      if(xVelocity>0){
        xVelocity = xSpeed;
        yVelocity -= (yVelocity/20);        
      }
      else if(xVelocity<0){
        xVelocity = -1*xSpeed;
        yVelocity -= (yVelocity/20);        
      }
      
    }
  }
  
  public void move () {
    animate();
    x+=xVelocity;
    y+=yVelocity;
    hitBox.setX(x);
    hitBox.setY(y);
  }
  
  public boolean onScreen(){
    return (x > 0-DEFAULT_HITBOX_WIDTH-100 && x < Main.WIDTH+100 && y > 0-DEFAULT_HITBOX_HEIGHT-100 && y < Main.HEIGHT+100);
  }
  public boolean canShoot() {
    if(version.equalsIgnoreCase("stay")&&shotCoolDown<=0&&xVelocity==0)
      return true;
    else if(version.equalsIgnoreCase("leave")&& (x==shootPos))
      return true;
    else
      return false;
  }
  public void resetShotCoolDown() {
    shotCoolDown = MAXSHOTCOOLDOWN;
  }
  
  @Override
  public void paint (Graphics2D g) {
    BufferedImage img = null;
    
    String workingDir = System.getProperty("user.dir");
    String path = "";
    
    if (state == State.RED) {
      path = workingDir + FileStore.MOOK_RED;
    } else if (state == State.BLUE) {
      path = workingDir + FileStore.MOOK_BLUE;
    }
    
    try {
      img = ImageIO.read(new File(path));
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    g.drawImage(img, x, y, null);
  }
}
