/*
 * PROJECT: LodeStar
 * Source code can be found at www.github.com/michaelvu97/LodeStar
 * Authors: Safwan Qazi (Project Manager), Michael Vu, Roy Liu
 * Inception Date: 9/17/14
 *
 * A tough, medium sized enemy that moves erratically, 
 * takes 3 shots to kill, and fires when directly above the player
 */

package com.MRS.NeckbeardEngine.Enemies;

import com.MRS.NeckbeardEngine.*;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Elite extends Enemy {
  
  //Used to determine when the Elite can fire
  private Player target;
  
  //Checks to see when the Elite begins it's behaviour
  private boolean active;
  
  //Used to allow the Elite to have more erratic patterns
  private boolean maxSpeedX;
  
  //Time until the Elite's movement is switched
  private int pathTime;
  
  public static int DEFAULT_HITBOX_WIDTH = 96;
  public static int DEFAULT_HITBOX_HEIGHT = 96;
  public static int MAXSHOTCOOLDOWN = 60;
  public static int SWITCH_DIRECTION = 90;
  
  public Elite (State state, int x, int y, double xVelocity, double yVelocity, String projectileType, Player target) {
    super(state, x, y, xVelocity, yVelocity, projectileType);
    health = 3;
    hitBox = new HitBox(x, y, DEFAULT_HITBOX_WIDTH, DEFAULT_HITBOX_HEIGHT);
    shotCoolDown = MAXSHOTCOOLDOWN;
    pathTime = 0;
    this.target = target;
    active = false;
    maxSpeedX = true;
  }
  
  //Elite Speed magnitude = 4
  public void animate() {
    
    //Prevents the Elite's behaviour until on screen
    if ((x >= 0 && x <= Main.WIDTH - DEFAULT_HITBOX_WIDTH) && y >= 0 && !active)
      active = true;
    
    //Prevents the Elite from flying off screen
    if (active) {
      if (x < 0 || x > Main.WIDTH - DEFAULT_HITBOX_WIDTH)
        xVelocity *= -1;
      if (y < 0 || y > (Main.HEIGHT / 2) - DEFAULT_HITBOX_HEIGHT)
        yVelocity *= -1;
      
      //Randomizes the Elite's movement patterns
      if (pathTime <= 0) {
        if (maxSpeedX)
          xVelocity = (4 * randomizer());
        else
          xVelocity = (Math.random() * 4 * randomizer());
        if (!maxSpeedX)
          yVelocity = (4 * randomizer());
        else
          yVelocity = (Math.random() * 4 * randomizer());
        pathTime = SWITCH_DIRECTION;
        maxSpeedX = !maxSpeedX;
      }
    }    
    pathTime--;
  }
  
  public boolean onScreen(){
    return (x > 0 - DEFAULT_HITBOX_WIDTH - 100 && x < Main.WIDTH + 100 && y > 0 - DEFAULT_HITBOX_HEIGHT - 100 && y < Main.HEIGHT + 100);
  }
  
  public void resetShotCoolDown() {
    shotCoolDown = MAXSHOTCOOLDOWN;
  }
  
  //Allows Elite to shoot when it is above the player
  public boolean canShoot() {
    if (shotCoolDown <= 0 && (x > target.getX() - 10 && x < target.getX() + 10))
      return true;
    else
      return false;
  }
 
  public void move() {
    animate();
    x += xVelocity;
    y += yVelocity;
    hitBox.setX(x);
    hitBox.setY(y);
  }
  
  //Used in randomizing the Elite's movements
  public int randomizer() {
    int i = (int) (Math.random() * 10);
    if (i <= 4)
      return -1;
    else
      return 1;
  }
  
  @Override
  public void paint (Graphics2D g) {
    BufferedImage img = null;
    
    String workingDir = System.getProperty("user.dir");
    String path = "";
    
    if (state == State.RED) {
      path = workingDir + FileStore.ELITE_RED;
    } else if (state == State.BLUE) {
      path = workingDir + FileStore.ELITE_BLUE;
    } else if(state == State.BOTH){
      path = workingDir + FileStore.ELITE_BOTH;
    }
    
    try {
      img = ImageIO.read(new File(path));
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    g.drawImage(img, x, y, null);
  }
}
