package com.MRS.NeckbeardEngine.Projectiles;

import com.MRS.NeckbeardEngine.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class Bomb extends Projectile {
  
  //the current radius; used for animation
  private int radius;
  private static int rate = 40;
  public static double DEFAULT_DURATION = 500; 
  
  public Bomb (State state, int x, int y, double xVelocity, double yVelocity, String imgPath, double duration) {
    super (state, x, y, xVelocity, yVelocity, imgPath, duration);
    this.radius = 10;
    this.hitBox = new RadialHitBox(x - radius, y - radius, radius *2);
    this.killTime = System.currentTimeMillis() + duration;
  }
  
  @Override
  public void move () {
    super.move();
    radius += rate;
    hitBox.setWidth(radius * 2);
    hitBox.setX(hitBox.getX() - radius);
    hitBox.setHeight(radius * 2);
    hitBox.setY(hitBox.getY() - radius);
  }
  
  @Override
  public void paint (Graphics2D g) {
    g.setColor(Color.YELLOW);
    g.drawOval(x - radius, y - radius, radius * 2, radius * 2);
  }
}
