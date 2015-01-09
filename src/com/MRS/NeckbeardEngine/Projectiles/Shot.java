/*
 * PROJECT: LodeStar
 * Source code can be found at www.github.com/michaelvu97/LodeStar
 * Authors: Safwan Qazi (Project Manager), Michael Vu, Roy Liu
 * Inception Date: 9/17/14
 *
 * Used for the basic pip style projectiles.
 * Used in the basic, fast, rapid, and scatter shots.
 */

package com.MRS.NeckbeardEngine.Projectiles;

import com.MRS.NeckbeardEngine.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class Shot extends Projectile {
     
     //Constants
     public static int DEFAULT_HITBOX_WIDTH = 13;
     public static int DEFAULT_HITBOX_HEIGHT = 13;
     
     //personal image
     private BufferedImage img;
     
     public Shot (State state, int x, int y, double xVelocity, double yVelocity) {
          //Projectile class constructor
          super(state, x, y, xVelocity, yVelocity);
          
          //Hitbox instantiation
          hitBox = new HitBox(x,y,DEFAULT_HITBOX_WIDTH,DEFAULT_HITBOX_HEIGHT);
     }
     
     @Override
     public void paint(Graphics2D g) {
          /*
           * Paints the ship with the graphics object passed
           * by Game
           */
          this.loadImage();
          //g.drawImage(glow, x - 128, y - 128, null);
          g.drawImage(img,x,y,null);
     }
     
     public void loadImage() {
          
          String workingDir = System.getProperty("user.dir");
          String path = "";
          String pathGlow = "";
          
          if (state == State.RED) {
               if (yVelocity < 0) {
                    if (yVelocity == -1*Projectile.FastShotVelocity)
                         path = workingDir + FileStore.FAST_SHOT_RED;
                    else
                         path = workingDir + FileStore.SHOT_RED;
               } else {
                    if (yVelocity == Projectile.FastShotVelocity)
                         path = workingDir + FileStore.ENEMY_FAST_SHOT_RED;
                    else
                         path = workingDir + FileStore.ENEMY_SHOT_RED;
               }
               
          } else if (state == State.BLUE) {
               if (yVelocity < 0) {
                    if (yVelocity == -1*Projectile.FastShotVelocity)
                         path = workingDir + FileStore.FAST_SHOT_BLUE;
                    else
                         path = workingDir + FileStore.SHOT_BLUE;
               } else {
                   if (yVelocity == Projectile.FastShotVelocity)
                         path = workingDir + FileStore.ENEMY_FAST_SHOT_BLUE;
                    else
                         path = workingDir + FileStore.ENEMY_SHOT_BLUE;
               }
          }
          
          else
            path = workingDir + FileStore.ENEMY_SHOT_PURPLE;
          
          try {
               img = ImageIO.read(new File (path));
          } catch (IOException e) {
               e.printStackTrace();
          }
     }
}
