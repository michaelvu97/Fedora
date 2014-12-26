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
     public static int INITIAL_COOLDOWN = 300;
     
     public Iris (State state, int x, int y, double yVelocity, String projectileType, PowerUpPickup heldPowerUp, long timeLine) {
          super(state, x, y, 0, yVelocity, projectileType, heldPowerUp, timeLine);
          health = 2;
          hitBox = new HitBox (x, y, DEFAULT_HITBOX_WIDTH, DEFAULT_HITBOX_HEIGHT);
          shotCoolDown = INITIAL_COOLDOWN;
     }
     
     public void animate () {
          if (y > 0)
               yVelocity = 0;
          else {
          }
     }
     
     public void move() {
          animate();
          y += yVelocity;
          hitBox.setY(y);
     }
     
     public void resetShotCoolDown(){
          shotCoolDown = INITIAL_COOLDOWN + 180;
     }
     
     public boolean canShoot() {
          if(shotCoolDown<=0)
               return true;
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
               if (shotCoolDown <= INITIAL_COOLDOWN && shotCoolDown > INITIAL_COOLDOWN - 180)
                    path = workingDir + FileStore.IRIS_RED;
               else
                    path = workingDir + FileStore.IRIS_RED_ACTIVE;
          } else if (state == State.BLUE) {
               if (shotCoolDown <= INITIAL_COOLDOWN && shotCoolDown > INITIAL_COOLDOWN - 180)
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
