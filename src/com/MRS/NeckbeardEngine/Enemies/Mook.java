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
  public String version;
  
  public Mook (State state, int x, int y, double xVelocity, double yVelocity, String projectileType, PowerUpPickup heldPowerUp, long timeLine, boolean canShoot,String version) {
    super(state, x, y, xVelocity, yVelocity, projectileType, heldPowerUp, timeLine, canShoot);
    health = 1;          
    hitBox = new HitBox (x, y, DEFAULT_HITBOX_WIDTH, DEFAULT_HITBOX_HEIGHT);
    this.version = version;
  }
  
  //@Override
  public void animate (boolean collide) {
    
    //Todo: add shooting to animate.
    
    if(version.equalsIgnoreCase("stay")){
      if(x<Main.WIDTH/2-DEFAULT_HITBOX_WIDTH){
        xVelocity = 3;
        yVelocity -= (yVelocity/20);        
      }
      else if(x>Main.WIDTH/2){
        xVelocity = -3;
        yVelocity -= (yVelocity/20);        
      }
      else{
        xVelocity = 0;
        yVelocity = 0;
      }
      
    }
    if(version.equalsIgnoreCase("non")){
      
    }
    if(collide) {
      switchDirections();
    }
    if(x<0||x>(Main.WIDTH-DEFAULT_HITBOX_WIDTH))
      xVelocity*=-1;
    if(y<0||y>(Main.HEIGHT-DEFAULT_HITBOX_HEIGHT))
      yVelocity*=-1;
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
