package com.MRS.NeckbeardEngine.Projectiles;

import com.MRS.NeckbeardEngine.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class Shot extends Projectile {
  
  public static int DEFAULT_HITBOX_WIDTH = 13;
  public static int DEFAULT_HITBOX_HEIGHT = 13;
     
  BufferedImage img;
  public Shot (State state, int x, int y, double xVelocity, double yVelocity, String imgPath, HitBox hitBox, double duration) {
    super(state, x, y, xVelocity, yVelocity, imgPath, duration);
    hitBox = new HitBox(x,y,DEFAULT_HITBOX_WIDTH,DEFAULT_HITBOX_HEIGHT);
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
  public void paint(Graphics2D g) {
    String workingDir = System.getProperty("user.dir");
    String path = "";
    if (state == State.RED) {
      if (yVelocity < 0) {
        path = workingDir + FileStore.SHOT_RED;
      } else {
        //enemy shot image
      }
    } else if (state == State.BLUE) {
      if (yVelocity < 0) {
        path = workingDir + FileStore.SHOT_BLUE;
      } else {
        //enemy shot image
      }
    }
    try {
      img = ImageIO.read(new File (path));
    } catch (IOException e) {
      e.printStackTrace();
    }
    g.drawImage(img,x,y,null);
  }
}
