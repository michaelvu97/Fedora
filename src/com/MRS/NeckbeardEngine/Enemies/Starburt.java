package com.MRS.NeckbeardEngine.Enemies;

import com.MRS.NeckbeardEngine.*;

public class Starburt extends Enemy {
  
  public Starburt (State state, int x, int y, int xVelocity, Projectile projectileType, PowerUpPickup heldPowerUp, long timeLine) {
    super (state, x, y, xVelocity, 0, projectileType, heldPowerUp, timeLine);
    health = 2;
  }
  
  @Override
  public void animate () {
    //shout out to safwan's sick type
  }
}
