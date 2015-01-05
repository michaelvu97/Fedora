package com.MRS.NeckbeardEngine.Projectiles;

import com.MRS.NeckbeardEngine.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class StarburtShot extends Projectile {
  //Constants
  public static int DEFAULT_HITBOX_WIDTH = 60;
  public static int DEFAULT_HITBOX_HEIGHT = 30;
  
  //personal image
  private BufferedImage img;
  private BufferedImage laser;
  
  //Player position
  private Player player;
  
  //lazor firing countdown
  private int coolDown;
  
  //boolean if firing
  private boolean active;
  private boolean playSound;
  
  public StarburtShot (State state, int x, int y, double xVelocity, double yVelocity, double duration, Player player) {
    //Projectile class constructor. StarburtShot yVelocity = 3    
    super(state, x, y, xVelocity, yVelocity, duration);
    
    this.xVelocity = 0;
    
    //Hitbox instantiation
    hitBox = new HitBox(x,y,DEFAULT_HITBOX_WIDTH,DEFAULT_HITBOX_HEIGHT);
    
    //Kill timing
    this.killTime = duration + System.currentTimeMillis();
    
    //Player
    this.player = player;
    
    //CoolDown Setup
    coolDown = 1;
    
    // not active
    active = false;
    playSound = false;
    
  }
  
  public void animate() {
    if(y > player.getY() + 20 && y < (player.getY() + Player.DEFAULT_HITBOX_HEIGHT - 20) && !active) {
      yVelocity = 0;
      coolDown = 120;
      active = true;
      playSound = true;
    }
    if(active && coolDown < 0)
    {
      hitBox.setX(0);
      hitBox.setWidth(800);
    }
  }
  
  @Override
  
    public void move() {
    animate();
    if(active){
      coolDown--;
    }
    x += xVelocity;
    y += yVelocity;
    hitBox.setY(y);
    if(coolDown == -180)
      killTime = System.currentTimeMillis()+1;
  }
  
  public void paint(Graphics2D g) {
    /*
     * Paints the ship with the graphics object passed
     * by Game
     */
    this.loadImage();
    if(active){
      g.drawImage(laser,0,y,null);
    }
    g.drawImage(img,x,y,null);
  }
  
  public void loadImage() {
    
    String workingDir = System.getProperty("user.dir");
    String path = "";
    if(active){
      String file = "";
      
      if(state == State.RED){
        if(coolDown > 1)
          file = workingDir + FileStore.LASER_RED_HORIZONTAL_CHARGE;
        else
          file = workingDir + FileStore.LASER_RED_HORIZONTAL_ACTIVE;
      }
      else if (state == State.BLUE){
        if(coolDown > 1)
          file = workingDir + FileStore.LASER_BLUE_HORIZONTAL_CHARGE;
        else
          file = workingDir + FileStore.LASER_BLUE_HORIZONTAL_ACTIVE;
      }
      
      try {
        laser = ImageIO.read(new File (file));
      } 
      catch (IOException e) {
        e.printStackTrace();
      }
    }
      
    
    if (state == State.RED) {
      if(active)
        path = workingDir + FileStore.ENEMY_STARBURT_SHOT_RED_ACTIVE;
      else
        path = workingDir + FileStore.ENEMY_STARBURT_SHOT_RED;
    }
    else if (state == State.BLUE) {
      if(active)
        path = workingDir + FileStore.ENEMY_STARBURT_SHOT_BLUE_ACTIVE;
      else
        path = workingDir + FileStore.ENEMY_STARBURT_SHOT_BLUE;
    }
    try {
      img = ImageIO.read(new File (path));
    } 
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public boolean getActive() {
    return active;
  }
  public boolean getPlaySound() {
    return playSound;
  }
  
  public void setPlaySound(boolean b) {
    playSound = b;
  }
}