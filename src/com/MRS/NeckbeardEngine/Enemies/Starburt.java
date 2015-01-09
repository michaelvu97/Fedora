/* 
 * PROJECT:LodeStar
 * Source can be found at www.github.com/michaelvu97/LodeStar
 * Authors: Qazi Qazi (Project Manager), Roy Liu, Michael Vu
 * Date: 9/17/14
 *
 * A large enemy that takes 2 shots to kill. Moves side to side
 * and fires projectiles that travel downward until the reach the player's 
 * y-axis. The projectile then stops and fires a horizontal laser 
 * after a short delay. Starburt leaves after firing 5 times.
 */
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
  public static int MAXSHOTCOOLDOWN = 300;
  private boolean offScreen;
  
  //Counts how many times the Starburt has fired
  private int timesFired;
  
  public Starburt (State state, int x, int y, double xVelocity) {
    super(state, x, y, xVelocity, 0, "starburtShot");
    health = 2;          
    hitBox = new HitBox (x, y, DEFAULT_HITBOX_WIDTH, DEFAULT_HITBOX_HEIGHT);
    shotCoolDown = 300;
    offScreen = true;
    timesFired = 0;
  }
  
  public void animate () {
    
    if(offScreen){
    }
    else if (timesFired >= 5) {
    }
    
    //Stops the Starburt from moving offscreen
    else{
      if(x < 0)
        xVelocity = xSpeed;
      if(x > Main.WIDTH-DEFAULT_HITBOX_WIDTH)
        xVelocity = -1*xSpeed;
    }
    
    if(x > 0 && x < Main.WIDTH-DEFAULT_HITBOX_WIDTH)
      offScreen = false;
  }
  
  public void move () {
    animate();
    x += xVelocity;
    y += yVelocity;
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
    if(shotCoolDown <= 0 && timesFired != 5) {
      timesFired++;
      return true;
    }
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