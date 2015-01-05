package com.MRS.NeckbeardEngine;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class PowerUpPickup {
     private int x;
     private int y;
     private PowerUp heldPowerUp;
     public HitBox hitBox;
     
     public static int DEFAULT_HITBOX_WIDTH = 30;
     public static int DEFAULT_HITBOX_HEIGHT = 30;
     
     private static int yVelocity = 1;
     
     public PowerUpPickup(int x, int y, PowerUp heldPowerUp) {
          this.x = x;
          this.y = y;
          this.heldPowerUp = heldPowerUp;
          hitBox = new HitBox(x, y, DEFAULT_HITBOX_WIDTH, DEFAULT_HITBOX_HEIGHT);
     }
     
     //Accessors
     public HitBox getHitBox() {
          return hitBox;
     }
     
     public PowerUp getHeldPowerUp() {
          return heldPowerUp;
     }
     
     //Moves Power Up Icon down the screen
     public void move() {
          y += yVelocity;
          hitBox.setY(y + yVelocity);
     }
     
     //Paints the power up
     public void paint(Graphics2D g) {
          BufferedImage img = null;
          
          try {
            if (heldPowerUp == PowerUp.FAST_SHOT) 
               img = ImageIO.read(new File(System.getProperty("user.dir") + FileStore.POWERUP_FAST));
            else if (heldPowerUp == PowerUp.BOMB) 
               img = ImageIO.read(new File(System.getProperty("user.dir") + FileStore.POWERUP_BOMB));
            else if (heldPowerUp == PowerUp.SCATTER_SHOT)
               img = ImageIO.read(new File(System.getProperty("user.dir") + FileStore.POWERUP_SCATTER_SHOT));
            else if (heldPowerUp == PowerUp.RAPID_FIRE)
               img = ImageIO.read(new File(System.getProperty("user.dir") + FileStore.POWERUP_RAPID_FIRE));
            else if (heldPowerUp == PowerUp.EXTRA_SHIP)
               img = ImageIO.read(new File(System.getProperty("user.dir") + FileStore.POWERUP_EXTRA_SHIP));
            else if (heldPowerUp == PowerUp.SPEED_BOOST)
               img = ImageIO.read(new File(System.getProperty("user.dir") + FileStore.POWERUP_SPEED_BOOST));
            else if (heldPowerUp == PowerUp.SHIELD)
               img = ImageIO.read(new File(System.getProperty("user.dir") + FileStore.POWERUP_SHIELD)); 
          } catch (IOException e) {}
          
          g.drawImage(img, x, y, null);
     }
}
