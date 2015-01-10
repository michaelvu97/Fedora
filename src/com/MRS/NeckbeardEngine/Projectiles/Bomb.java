/* 
 * PROJECT:LodeStar
 * Source can be found at www.github.com/michaelvu97/LodeStar
 * Authors: Safwan Qazi (Project Manager), Roy Liu, Michael Vu
 * Date: 9/17/14
 *
 * The bomb spawns from the player's location and expands 
 * outward in a radial hitbox.
 */

package com.MRS.NeckbeardEngine.Projectiles;

import com.MRS.NeckbeardEngine.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class Bomb extends Projectile {
  
  //the current radius; used for animation
  private int radius;
  private int rate = 50;
  
  private double killTime;
  
  public static double DEFAULT_DURATION = 1000; 
  private BufferedImage baseImg = null;
  
  public Bomb (State state, int x, int y, double xVelocity, double yVelocity) {
    //Constructor
    
    //Projectile constructor
    super (state, x, y, xVelocity, yVelocity);
    
    //Sets up the radial hitbox
    this.radius = 10;
    this.hitBox = new RadialHitBox(x - radius, y - radius, radius *2);
    this.killTime = System.currentTimeMillis() + DEFAULT_DURATION;
    
    try {
      baseImg = ImageIO.read(new File (System.getProperty("user.dir") + FileStore.BOMB_IMG));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  @Override
  public void move () {
    //Translates the instance
    super.move();
    
    //Scales the instance
    radius += rate;
    hitBox.setWidth(radius * 2);
    hitBox.setHeight(radius * 2);
    
    //Scales hitbox from centre
    hitBox.setX(hitBox.getX() - radius);
    hitBox.setY(hitBox.getY() - radius);
  }
  
  public void setRate(int rate) {
    this.rate = rate;
  }
  
  @Override
  public void paint (Graphics2D g) {
    //transformations
    g.drawImage(baseImg, x - (radius), y - (radius) , radius * 2, radius * 2, null);
  }
  
  public void setKillTime(double d) {
    killTime = d;
  }
  
  public double getKillTime() {
    return killTime;
  }
}
