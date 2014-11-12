package com.MRS.NeckbeardEngine.Enemies;

import com.MRS.NeckbeardEngine.*;
import com.MRS.NeckbeardEngine.Projectiles.*;

public class Brute extends Enemy {
     
     public static int DEFAULT_HITBOX_WIDTH = 10;
     public static int DEFUALT_HITBOX_HEIGHT = 10;
     
     public Brute (State state, int x, int y, double xVelocity, double yVelocity, Projectile projectileType, PowerUpPickup heldPowerUp, long timeLine, boolean canShoot) {
          super(state, x, y, xVelocity, yVelocity, projectileType, heldPowerUp, timeLine, canShoot);
          hitBox = new HitBox(x, y, DEFAULT_HITBOX_WIDTH, DEFAULT_HITBOX_HEIGHT);
          health = 3;
     }
     @Override
     public void animate () {
      
          //Swag
          
     }
}
