/*
 * PROJECT: LodeStar
 * Source code can be found at www.github.com/michaelvu97/LodeStar
 * Authors: Safwan Qazi (Project Manager), Michael Vu, Roy Liu
 * Inception Date: 9/17/14
 *
 * A large ship that takes 2 shots to kill.
 * After appearing on screen, it stays in place and fires 
 * a vertical laser periodically. After 3 times, it leaves.
 */
package com.MRS.NeckbeardEngine.Enemies;

import com.MRS.NeckbeardEngine.*;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Iris extends Enemy {
     
     public static int DEFAULT_HITBOX_WIDTH = 64;
     public static int DEFAULT_HITBOX_HEIGHT = 128;
     
     /* The idea is that the Iris has a cycle of 3 sec doing nothing, 2 sec of charge, and 3 sec where the laser is active
      * Initial cooldown represents the 3 sec of nothing and the 2 sec of charge
      */
     public static int INITIAL_COOLDOWN = 300;
     
     //Controls when the Iris switches between an active and inactive model
     private int eyeClock;
     
     //Counts how many times the Iris has fired
     private int timesFired;
     
     public Iris (State state, int x, String projectileType) {
          super(state, x, -130, 0, 2, projectileType);
          health = 2;
          hitBox = new HitBox (x, y, DEFAULT_HITBOX_WIDTH, DEFAULT_HITBOX_HEIGHT);
          shotCoolDown = INITIAL_COOLDOWN;
          eyeClock = 0;
          timesFired = 0;
     }
     
     public void animate () {
          //Freezes the Iris in place while it is on screen
          if (y > 0 && timesFired != 3)
               yVelocity = 0;
          
          //Iris retreats after it fire 3 times
          else if (eyeClock <= 0 && timesFired >= 3){
            yVelocity = -2;
            eyeClock = 121;
          }
          
          //Controls when the Iris switches between an active and inactive model
          if(eyeClock > 0) {
               resetShotCoolDown();
               eyeClock--;
          }
     }
     
     public void move() {
          animate();
          y += yVelocity;
          hitBox.setY(y);
     }
     
     //After the first fire, shotCoolDown gets 3 sec to represent the time to wait for the laser to dissapate
     public void resetShotCoolDown(){
          shotCoolDown = INITIAL_COOLDOWN;
     }
     
     public boolean canShoot() {
          if(shotCoolDown <= 0 && timesFired != 3) {
               eyeClock = 300;
               timesFired++;
               return true;
          }
          else
               return false;
     }
     
     public boolean onScreen(){
          return (x > 0 - DEFAULT_HITBOX_WIDTH - 100 && x < Main.WIDTH + 100 && y > 0 - DEFAULT_HITBOX_HEIGHT - 100 && y < Main.HEIGHT + 100);
     }
     
     @Override
     public void paint (Graphics2D g) {
          BufferedImage img = null;
          
          String workingDir = System.getProperty("user.dir");
          String path = "";
          
          if (state == State.RED) {
               if (eyeClock <= 0)
                    path = workingDir + FileStore.IRIS_RED;
               else
                    path = workingDir + FileStore.IRIS_RED_ACTIVE;
          } else if (state == State.BLUE) {
               if (eyeClock <= 0)
                    path = workingDir + FileStore.IRIS_BLUE;
               else
                    path = workingDir + FileStore.IRIS_BLUE_ACTIVE;
          }
          
          try {
               img = ImageIO.read(new File(path));
          } catch (IOException e) {
               e.printStackTrace();
          }
          
          g.drawImage(img, x, y, null);
     }
}
