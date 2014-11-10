package com.MRS.NeckbeardEngine.Enemies;

import com.MRS.NeckbeardEngine.*;

public class Starburt extends Enemy {
  
  public Starburt (State state, int health, int x, int y, int xVelocity, Projectile projectileType, PowerUpPickUp heldPowerUp, long timeLine) {
    super (state, health, x, y, xVelocity, 0, projectileType, heldPowerup, timeLine);
  }
  
  @Override
  public void animate () {
    //shout out to safwan's sick type
  }
}
