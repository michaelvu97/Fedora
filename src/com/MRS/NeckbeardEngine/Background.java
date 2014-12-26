package com.MRS.NeckbeardEngine;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Background {
  private double y1, y2;
  private double yVelocity;
  private BufferedImage img = null;
  
  public Background (String file, double yVelocity){
    y1 = 0;
    this.yVelocity = yVelocity;
    String workingDir = System.getProperty("user.dir");
    String path = workingDir + file;
    
   try {
      img = ImageIO.read(new File(path));
    } catch (IOException e) {
      e.printStackTrace();
    }
    y2 = -1 * img.getHeight(); 
  }
  public void move(){
    y1+=yVelocity;
    y2+=yVelocity;
    
    if(y1>=img.getHeight()){
      y1 = y2;
      y2 = -1 * img.getHeight();
    }
  }
  public void paint (Graphics2D g) {
    g.drawImage(img,0,(int)y1,null);
    g.drawImage(img,0,(int)y2,null);
  }
    
}