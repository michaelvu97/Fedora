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
  
  public static final String EXPLOSION_TYPE_HIT = "EXPLOSION_TYPE_HIT";
  
  public Explosion (int x, int y, String explosionType) {
    x = x;
    y = y;
    if (explosionType.equals(EXPLOSION_TYPE_HIT)) {
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
      g.drawImage(frames.get(animationPosition++), x, y, null);
    }
  }
  
  public boolean getCompleted() {
    return animationCompleted;
  }
}