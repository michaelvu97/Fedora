package com.MRS.NeckbeardEngine.Enemies;

import com.MRS.NeckbeardEngine.*;
import com.MRS.NeckbeardEngine.Projectiles.*;

public class Brute extends Enemy {
     
     public static int DEFAULT_HITBOX_WIDTH = 10;
     public static int DEFUALT_HITBOX_HEIGHT = 10;
     
     public Brute (State state, int x, int y, double xVelocity, double yVelocity, Projectile projectileType, PowerUpPickup heldPowerUp, long timeLine) {
          super(state, x, y, xVelocity, yVelocity, projectileType, heldPowerUp, timeLine);
          health = 3;
     }
     @Override
     public void animate () {
      
          //Swag
          
     }
}
