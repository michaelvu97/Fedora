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
  public void animate (boolean collide) {
    
    //Todo: add shooting to animate.
    
    if(version.equalsIgnoreCase("stay")){
      if(y<300+startY&&startX<Main.WIDTH/2&&x<=shootPos){
        xVelocity = 3;
        yVelocity -= (yVelocity/20);        
      }
      else if(startX>Main.WIDTH/2&&y<300&&x>=shootPos){
        xVelocity = -3;
        yVelocity -= (yVelocity/20);        
      }
      else{
        xVelocity = 0;
        yVelocity = 0;
      }
      
    }
    if(version.equalsIgnoreCase("leave")){
      if(startX<Main.WIDTH/2){
        xVelocity = 3;
        yVelocity -= (yVelocity/20);        
      }
      else if(startX>Main.WIDTH/2){
        xVelocity = -3;
        yVelocity -= (yVelocity/20);        
      }
      
    }
    if(collide) {
      switchDirections();
    }
    if(!version.equalsIgnoreCase("leave")){
      if(x<0||x>(Main.WIDTH-DEFAULT_HITBOX_WIDTH))
        xVelocity*=-1;
      if(y<0||y>(Main.HEIGHT-DEFAULT_HITBOX_HEIGHT))
        yVelocity*=-1;
    }
  }
  
  public void move (boolean collide) {
    animate(collide);
    x+=xVelocity;
    y+=yVelocity;
    hitBox.setX(x);
    hitBox.setY(y);
  }
  
  public void switchDirections () {
    xVelocity*=-1;
    yVelocity*=-1;
  }
  public boolean onScreen(){
    return (x > 0-DEFAULT_HITBOX_WIDTH && x < Main.WIDTH && y > 0-DEFAULT_HITBOX_HEIGHT && y < Main.HEIGHT);
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
