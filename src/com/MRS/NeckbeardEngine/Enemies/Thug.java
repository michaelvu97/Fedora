package com.MRS.NeckbeardEngine.Enemies;

import com.MRS.NeckbeardEngine.*;

public class Thug extends Enemy {
  
   public Thug (State state, int x, int y, double xVelocity, double yVelocity, Projectile projectileType, PowerUpPickup powerUpPickup, long timeLine, boolean canShoot) {
        super(state, x, y, xVelocity, yVelocity, projectileType, powerUpPickup, timeLine, canShoot);
  }
  
  @Override
  public void animate () {
    //thug lyfe
  }
}
