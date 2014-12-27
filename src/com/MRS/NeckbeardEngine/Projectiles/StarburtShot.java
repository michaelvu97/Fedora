package com.MRS.NeckbeardEngine.Projectiles;

import com.MRS.NeckbeardEngine.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class StarburtShot extends Projectile {
  //Constants
  public static int DEFAULT_HITBOX_WIDTH = 13;
  public static int DEFAULT_HITBOX_HEIGHT = 13;
  
  //personal image
  private BufferedImage img;
  
  //Player position
  private Player player;
  
  //lazor firing countdown
  private int coolDown;
  
  public StarburtShot (State state, int x, int y, double xVelocity, double yVelocity, String imgPath, double duration, Player player) {
    //Projectile class constructor    
    super(state, x, y, xVelocity, yVelocity, imgPath, duration);
    
    this.xVelocity = 0;
    
    //Hitbox instantiation
    hitBox = new HitBox(x,y,DEFAULT_HITBOX_WIDTH,DEFAULT_HITBOX_HEIGHT);
    
    //Kill timing
    this.killTime = duration + System.currentTimeMillis();
    
    //Player
    this.player = player;
    
    //CoolDown Setup
    coolDown = 0;
    
  }
  
  public void animate() {
    if(y == (player.getY()+(Player.DEFAULT_HITBOX_HEIGHT/2))) {
      yVelocity = 0;
      coolDown = 120;
    }
    if(coolDown<0)
    {
      hitBox.setX(0);
      hitBox.setWidth(800);
    }
  }
  
  @Override
  
    public void move() {
    animate();
    if(yVelocity == 0)
      coolDown--;
    x += xVelocity;
    y += yVelocity;
    hitBox.setY(y);
  }
  
  public void paint(Graphics2D g) {
    /*
     * Paints the ship with the graphics object passed
     * by Game
     */
    this.loadImage();
    g.drawImage(img,x,y,null);
  }
  
  public void loadImage() {
    
    String workingDir = System.getProperty("user.dir");
    String path = "";
    
    if (state == State.RED) {
      path = workingDir + FileStore.ENEMY_STARBURT_SHOT_RED;
    }
    else if (state == State.BLUE) {
      path = workingDir + FileStore.ENEMY_STARBURT_SHOT_BLUE;
    }
    try {
      img = ImageIO.read(new File (path));
      System.out.println("ho");
    } 
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  
}