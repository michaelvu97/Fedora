package com.MRS.NeckbeardEngine.Enemies;

import com.MRS.NeckbeardEngine.Enemy;
import com.MRS.NeckbeardEngine.HitBox;
import com.MRS.NeckbeardEngine.PowerUpPickup;
import com.MRS.NeckbeardEngine.Projectile;
import com.MRS.NeckbeardEngine.State;

public class Thug extends Enemy {
  
	public static int DEFAULT_HITBOX_WIDTH = 5;
    public static int DEFAULT_HITBOX_HEIGHT = 5;
    
   public Thug (State state, int x, int y, double xVelocity, double yVelocity, Projectile projectileType, PowerUpPickup powerUpPickup, long timeLine, boolean canShoot) {
        super(state, x, y, xVelocity, yVelocity, projectileType, powerUpPickup, timeLine, canShoot);
        health = 2;
        hitBox = new HitBox (x, y, DEFAULT_HITBOX_WIDTH, DEFAULT_HITBOX_HEIGHT);
        
  }
  
  @Override
  public void animate () {
    //thug lyfe
  }
}
