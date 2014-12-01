package com.MRS.NeckbeardEngine.Enemies;

import com.MRS.NeckbeardEngine.Enemy;
import com.MRS.NeckbeardEngine.HitBox;
import com.MRS.NeckbeardEngine.PowerUpPickup;
import com.MRS.NeckbeardEngine.State;
import com.MRS.NeckbeardEngine.Projectiles.*;

public class Starburt extends Enemy {
 
  public static int DEFAULT_HITBOX_WIDTH = 10;
  public static int DEFAULT_HITBOX_HEIGHT = 10;
  
  public Starburt (State state, int x, int y, double xVelocity, String projectileType, PowerUpPickup heldPowerUp, long timeLine, boolean canShoot) {
    super (state, x, y, xVelocity, 0, projectileType, heldPowerUp, timeLine, canShoot);
    health = 2;
    hitBox = new HitBox (x, y, DEFAULT_HITBOX_WIDTH, DEFAULT_HITBOX_HEIGHT);
  }
  
  @Override
  public void animate () {
    //shout out to safwan's sick type
  }
}
