package com.MRS.NeckbeardEngine.Enemies;

import com.MRS.NeckbeardEngine.*;
import com.MRS.NeckbeardEngine.Projectiles.*;

public class Starburt extends Enemy {
  
  public static int DEFAULT_HITBOX_WIDTH = 10;
  public static int DEFAULT_HITBOX_HEIGHT = 10;
  
  public Starburt (State state, int x, int y, double xVelocity, Projectile projectileType, PowerUpPickup heldPowerUp, long timeLine, boolean canShoot) {
    super (state, x, y, xVelocity, 0, projectileType, heldPowerUp, timeLine, canShoot);
    hitBox = new HitBox(x, y, DEFAULT_HITBOX_WIDTH, DEFAULT_HITBOX_HEIGHT);
    health = 2;
  }
  
  @Override
  public void animate () {
    //shout out to safwan's sick type
  }
}
