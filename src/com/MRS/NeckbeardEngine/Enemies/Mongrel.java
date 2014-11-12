package com.MRS.NeckbeardEngine.Enemies;

import com.MRS.NeckbeardEngine.*;
import com.MRS.NeckbeardEngine.Projectiles.*;

public class Mongrel extends Enemy {
     
     public static int DEFAULT_HITBOX_HEIGHT = 10;
     public static int DEFAULT_HITBOX_WIDTH = 10;
     
     public Mongrel (State state, int x, int y, double xVelocity, double yVelocity, Projectile projectileType, PowerUpPickup heldPowerUp, long timeLine, boolean canShoot) {
          super(state, x, y, xVelocity, yVelocity, projectileType, heldPowerUp, timeLine, canShoot);
          health = 1;
          hitBox = new HitBox(x, y, DEFAULT_HITBOX_WIDTH, DEFAULT_HITBOX_HEIGHT);
     }
     @Override
     public void animate () {
      
          //Swag
          
     }
}
