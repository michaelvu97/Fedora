package com.MRS.NeckbeardEngine.Enemies;

import com.MRS.NeckbeardEngine.*;
import com.MRS.NeckbeardEngine.Projectiles.*;

public class Iris extends Enemy {
     
     public static int DEFAULT_HITBOX_WIDTH = 20;
     public static int DEFAULT_HITBOX_HEIGHT = 20;
     
     public Iris (State state, int x, int y, double yVelocity, Projectile projectileType, PowerUpPickup heldPowerUp, long timeLine) {
          super(state, x, y, 0, yVelocity, projectileType, heldPowerUp, timeLine);
          health = 2;
          hitBox = new HitBox (x, y, DEFAULT_HITBOX_WIDTH, DEFAULT_HITBOX_HEIGHT);
     }
     @Override
     public void animate () {
      
          //Swag
          
     }
}
