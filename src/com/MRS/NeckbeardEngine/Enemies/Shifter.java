/* 
 * PROJECT:LodeStar
 * Source can be found at www.github.com/michaelvu97/LodeStar
 * Authors: Qazi Qazi (Project Manager), Roy Liu, Michael Vu
 * Date: 9/17/14
 *
 * A small enemy that takes on shot to kill. It shares behaviour patterns with mook and
 * can shift it's state while it is active.
 */

package com.MRS.NeckbeardEngine.Enemies;

import com.MRS.NeckbeardEngine.*;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Shifter extends Enemy {
  
  public static int DEFAULT_HITBOX_WIDTH = 60;
  public static int DEFAULT_HITBOX_HEIGHT = 60;
  public static int MAXSHOTCOOLDOWN = 60;
  public static int SHIFT_COOLDOWN = 120;
  
  //Indicates what kind of behaviour the Shifter will exhibit
  private String version;
  
  //where you want it to shoot/stop
  private int shootPos;
  
  //How long until Shifter switches states
  private int shiftTimer;
  
  //Sound for when it shifts
  public boolean playSound = false;
  
  //Used to check if Shifter is in the game space
  private boolean active = false;
  
  public Shifter (State state, int x, int y, double xVelocity, double yVelocity, String projectileType, String version, Player target, int shootPos) {
    super(state, x, y, xVelocity, yVelocity, projectileType);
    health = 1;          
    hitBox = new HitBox (x, y, DEFAULT_HITBOX_WIDTH, DEFAULT_HITBOX_HEIGHT);
    this.version = version;
    this.shootPos = shootPos;
    shiftTimer = SHIFT_COOLDOWN;
    shotCoolDown = MAXSHOTCOOLDOWN;
  }

//Used to alter Shifter behaviours outside of the basic linear movement
public void animate () {
  
    // The Shifter stops in place after reaching its shooting position and stays until destroyed
    if(version.equalsIgnoreCase("stay")){
      if((xVelocity > 0 && x >= shootPos) || (xVelocity < 0 && x <= shootPos)){
        xVelocity = 0;
        yVelocity = 0;
      }
      
    }
    
    //V-shaped move formation, fires a bottom point of V. 3 xVelocity, 4 yVelocity
    if(version.equalsIgnoreCase("form")) {
      if (xVelocity > 0) {
        if (x < Main.WIDTH/4) {
          xVelocity = xSpeed;
          yVelocity = ySpeed;
        }
        else {
          xVelocity = xSpeed;
          yVelocity = -1 * ySpeed;
        }
      }
      else if (xVelocity < 0) {
        if (x > (Main.WIDTH - Main.WIDTH/4)) {
          xVelocity = -1 * xSpeed;
          yVelocity = ySpeed;
        }
        else {
          xVelocity = xSpeed * -1;
          yVelocity = ySpeed * -1;
        }
      }
    }
    
    //bounces around 4 velocity
    if(version.equalsIgnoreCase("patrol")){
      if(!active && ((yVelocity >= 0 && y > 0 && ((xVelocity >= 0 && x > 0) || (xVelocity < 0 && x < Main.WIDTH - DEFAULT_HITBOX_HEIGHT))) || (yVelocity < 0 && y < Main.HEIGHT-DEFAULT_HITBOX_HEIGHT && ((xVelocity >= 0 && x > 0) || (xVelocity < 0 && x < Main.WIDTH - DEFAULT_HITBOX_HEIGHT))))){
        active = true;
      }
      else if(active){
        if (x < 0 || x > Main.WIDTH - DEFAULT_HITBOX_WIDTH)
          xVelocity *= -1;
        if (y < 0 || y > Main.HEIGHT  - DEFAULT_HITBOX_HEIGHT)
          yVelocity *= -1;
      }
    }
  
    //Changes Shifter state when cooldown is done and resets cooldown timer
    shiftTimer--;
    if (shiftTimer == 0) {
      playSound = true;
      if (state == State.RED)
        state = State.BLUE;
      else
        state = State.RED;
      shiftTimer = SHIFT_COOLDOWN;
    }
  }

  
  public void move () {
    animate();
    x += xVelocity;
    y += yVelocity;
    hitBox.setX(x);
    hitBox.setY(y);
  }
  
  public boolean onScreen(){
    return (x > 0 - DEFAULT_HITBOX_WIDTH - 100 && x < Main.WIDTH + 100 && y > 0 - DEFAULT_HITBOX_HEIGHT - 100 && y < Main.HEIGHT + 100);
  }
  
  public boolean canShoot() {
    if( version.equalsIgnoreCase("stay") && shotCoolDown <= 0 && xVelocity == 0)
      return true;
    else if((version.equalsIgnoreCase("leave") || version.equalsIgnoreCase("form"))&& (x > shootPos - Math.abs(xVelocity) && x < shootPos))
      return true;
    else if(version.equalsIgnoreCase("patrol") && shotCoolDown <= 0)
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
