package com.MRS.NeckbeardEngine.Projectiles;

import com.MRS.NeckbeardEngine.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.geom.AffineTransform;
import java.io.*;

public class Bomb extends Projectile {
  
  //the current radius; used for animation
  private int radius;
  private static int rate = 40;
  public static double DEFAULT_DURATION = 500; 
  private BufferedImage baseImg = null;
  
  public Bomb (State state, int x, int y, double xVelocity, double yVelocity, String imgPath, double duration) {
    //Constructor
    
    //Projectile constructor
    super (state, x, y, xVelocity, yVelocity, imgPath, duration);
    
    //Sets up the radial hitbox
    this.radius = 10;
    this.hitBox = new RadialHitBox(x - radius, y - radius, radius *2);
    this.killTime = System.currentTimeMillis() + duration;
    
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
  
  @Override
  public void paint (Graphics2D g) {
    //transformations
    double baseRadius = 490;
    
    BufferedImage after = new BufferedImage((int)radius, (int)radius, BufferedImage.TYPE_INT_ARGB);
    AffineTransform at = new AffineTransform();
    double scale = radius * 2 / baseRadius;
    at.scale(scale, scale);
    
    AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
    after = scaleOp.filter(baseImg, after);
    //Draws the instance
    g.drawImage(after, x - radius, y - radius, null);
  }
}
