package com.MRS.NeckbeardEngine;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Explosion {
  private int x;
  private int y;
  private int animationPosition = 0;
  private boolean animationCompleted = false;
  
  ArrayList<BufferedImage> frames = new ArrayList<BufferedImage>();
  
  public static final String EXPLOSIONTYPE_HIT = "EXPLOSIONTYPE_HIT";
  
  //Used to center the explosion properly
  public static final int HIT_OFFSET_X = -17;
  public static final int HIT_OFFSET_Y = -70;
  
  public Explosion (int x, int y, String explosionType) {
    this.x = x;
    this.y = y;
    if (explosionType.equals(EXPLOSIONTYPE_HIT)) {
      System.out.println("offset");
      this.x += HIT_OFFSET_X;
      this.y += HIT_OFFSET_Y;
      String workingDir = System.getProperty("user.dir"); 
      for (int i = 0; i < FileStore.HITEXPLOSIONONE.length; i++) {
        try {
          frames.add(frames.size(), ImageIO.read(new File (workingDir + FileStore.HITEXPLOSIONONE[i])));
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
  
  public void paint(Graphics2D g) {
    if (animationPosition >= frames.size()) {
      animationCompleted = true;
    } else {
      System.out.println(x + " " + y);
      g.drawImage(frames.get(animationPosition++), x, y, null);
    }
  }
  
  public boolean getCompleted() {
    return animationCompleted;
  }
}