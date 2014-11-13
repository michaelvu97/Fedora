package com.MRS.NeckbeardEngine.Projectiles;

import com.MRS.NeckbeardEngine.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class Shot extends Projectile {
  BufferedImage img;
  public Shot (State state, int x, int y, double xVelocity, double yVelocity, String imgPath, HitBox hitBox, double duration) {
    super(state, x, y, xVelocity, yVelocity, imgPath, hitBox, duration);
      img = null;
    try {
      img = ImageIO.read(new File(imgPath));
    } catch(IOException ioe){}
  }
  
  public void move() {
    x+=(int)xVelocity;
    y+=(int)yVelocity;
  }
  
  @Override
  public void paint(Graphics2D g2d) {
    g2d.drawImage(img,x,y,null);
  }
}
