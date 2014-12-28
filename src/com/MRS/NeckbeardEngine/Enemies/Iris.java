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
     //The idea is that the Iris has a cycle of 3 sec doing nothing, 2 sec of charge, and 3 sec where the laser is active
     //Initial cooldown represents the 3 sec of nothing and the 2 sec of charge
     public static int INITIAL_COOLDOWN = 300;
     private int eyeClock;
     public Iris (State state, int x, int y, double yVelocity, String projectileType, PowerUpPickup heldPowerUp, long timeLine) {
          super(state, x, y, 0, yVelocity, projectileType, heldPowerUp, timeLine);
          health = 2;
          hitBox = new HitBox (x, y, DEFAULT_HITBOX_WIDTH, DEFAULT_HITBOX_HEIGHT);
          shotCoolDown = INITIAL_COOLDOWN;
          eyeClock = 0;
     }
     
     public void animate () {
          if (y > 0)
               yVelocity = 0;
          else {
          }
          if(eyeClock>0) {
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
       if(shotCoolDown<=0) {
         eyeClock = 260;
               return true;
       }
          else
               return false;
     }
     
     public boolean onScreen(){
          return (x > 0-DEFAULT_HITBOX_WIDTH-100 && x < Main.WIDTH+100 && y > 0-DEFAULT_HITBOX_HEIGHT-100 && y < Main.HEIGHT+100);
     }
     
     @Override
     public void paint (Graphics2D g) {
          BufferedImage img = null;
          
          String workingDir = System.getProperty("user.dir");
          String path = "";
          
          if (state == State.RED) {
               if (eyeClock<=0)
                    path = workingDir + FileStore.IRIS_RED;
               else
                    path = workingDir + FileStore.IRIS_RED_ACTIVE;
          } else if (state == State.BLUE) {
               if (eyeClock<=0)
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
