package com.MRS.NeckbeardEngine.Enemies;

import com.MRS.NeckbeardEngine.*;

public class Elite extends Enemy {
     
     public static int DEFAULT_HITBOX_WIDTH = 30;
     public static int DEFAULT_HTIBOX_HEIGHT = 20;
     
     public Elite (State state, int x, int y, double xVelocity, double yVelocity, Projectile projectileType, PowerUpPickup heldPowerUp, long timeLine) {
          super(state, x, y, xVelocity, yVelocity, projectileType, heldPowerUp, timeLine);
          health = 3;
          hitbox = new HitBox(x, y, DEFAULT_HITBOX_WIDTH, DEFAULT_HITBOX_HEIGHT;
     }
     @Override
     public void animate () {
      
          //Swag
          
     }
}
