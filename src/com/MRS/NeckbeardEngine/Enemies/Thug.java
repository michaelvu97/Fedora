package com.MRS.NeckbeardEngine.Enemies;

import com.MRS.NeckbeardEngine.*;
import com.MRS.NeckbeardEngine.Projectiles.*;

public class Thug extends Enemy {
  
  public static int DEFAULT_HITBOX_WIDTH = 10;
  public static int DEFAULT_HITBOX_HEIGHT = 10;
  
   public Thug (State state, int x, int y, double xVelocity, double yVelocity, String projectileType, PowerUpPickup powerUpPickup, long timeLine, boolean canShoot) {
        super(state, x, y, xVelocity, yVelocity, projectileType, powerUpPickup, timeLine, canShoot);
        hitBox = new HitBox(x, y, DEFAULT_HITBOX_WIDTH, DEFAULT_HITBOX_HEIGHT);
  }
  
  @Override
  public void animate () {
    //thug lyfe
  }
}
