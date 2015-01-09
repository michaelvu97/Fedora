/* 
 * PROJECT:LodeStar
 * Source can be found at www.github.com/michaelvu97/LodeStar
 * Authors: Qazi Qazi (Project Manager), Roy Liu, Michael Vu
 * Date: 9/17/14
 *
 * Laser spawns from a given point: the
 * centre of the laser will be at the given
 * coordinates and will extend based on its
 * orientation
 */
package com.MRS.NeckbeardEngine.Projectiles;

import com.MRS.NeckbeardEngine.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class Laser extends Projectile {
  private BufferedImage imgCharge, imgActive;
  
  private boolean charging = true;
  private int chargingClock = 0;
  private Enemy parent;
  
  private double killTime;
  
  public static int CHARGING_TIME = 120; 
  public static int ACTIVE_TIME = 180;
  
  public Laser (State state, int x, int y, double xVelocity, double yVelocity, Enemy parent) {
   super(state, x, y, xVelocity, yVelocity); 
   
   /* frames are converted to a time value
    * if the frame rate is ever changed,
    * this needs to be adjusted/dynamic
    */
   killTime = (CHARGING_TIME + ACTIVE_TIME) * 16.6667 + System.currentTimeMillis();
   //so it doesn't hit anything while charging
   hitBox = new HitBox(x,y,0,0);
   //the parent object needs to be binded to the laser, in case the parent dies
   this.parent = parent;
   
   //preloading images
   imgCharge = null;
   imgActive = null;
   try {
     String workingDir = System.getProperty("user.dir");
     if (state == State.RED) {
       imgCharge = ImageIO.read(new File (workingDir + FileStore.LASER_RED_VERTICAL_CHARGE));
       imgActive = ImageIO.read(new File (workingDir + FileStore.LASER_RED_VERTICAL_ACTIVE));
     } else {
       imgCharge = ImageIO.read(new File (workingDir + FileStore.LASER_BLUE_VERTICAL_CHARGE));
       imgActive = ImageIO.read(new File (workingDir + FileStore.LASER_BLUE_VERTICAL_ACTIVE));
     }
   } catch (IOException e) {
     e.printStackTrace();
   }
  }
  
  @Override
  public void paint (Graphics2D g) {
    if (charging) {
      g.drawImage(imgCharge, x+2, y, null);
    } else {
      g.drawImage(imgActive, x, y, null);
    }
  }
  
  //Instance Method
  @Override 
  public void move () {
    super.move();
    chargingClock++;
    if (chargingClock >= CHARGING_TIME) {
      charging = false;
    }
    if(!charging){
       hitBox = new HitBox (x, y, 30, 980);
    }
  }
  
  //Accessors
  public Enemy getParent() {
    return parent;
  }
  
  public boolean getCharging() {
    return charging;
  }
  
  public int getChargingClock() {
    return chargingClock; 
  }
  
  public double getKillTime() {
    return killTime;
  }
  
  //Mutators
  public void setChargingClock(int time) {
    chargingClock = time;
  }
  
  public void setKillTime(double d) {
    killTime = d;
  }
}