package com.MRS.NeckbeardEngine.Projectiles;

import com.MRS.NeckbeardEngine.*;
import java.awt.*;

public class Shot extends Projectile {
  public Shot (State state, int x, int y, double xVelocity, double yVelocity, String imgPath, HitBox hitBox, double duration) {
    super(state, x, y, xVelocity, yVelocity, imgPath, hitBox, duration);
  }
  
  public void move() {
    x+=(int)xVelocity;
    y+=(int)yVelocity;
  }
  
  @Override
  public void paint(Graphics2D g2d) {
       
  }
}
