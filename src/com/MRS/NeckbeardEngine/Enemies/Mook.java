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
     public Mook (State state, int x, int y, double xVelocity, double yVelocity, String projectileType, PowerUpPickup heldPowerUp, long timeLine, boolean canShoot) {
          super(state, x, y, xVelocity, yVelocity, projectileType, heldPowerUp, timeLine, canShoot);
          health = 1;          
          hitBox = new HitBox (x, y, DEFAULT_HITBOX_WIDTH, DEFAULT_HITBOX_HEIGHT);
     }
     
     //@Override
     public void animate (boolean collide) {
      if(collide) {
           switchDirections();
      }
     }
     public void move () {
          x+=xVelocity;
          y+=yVelocity;
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
