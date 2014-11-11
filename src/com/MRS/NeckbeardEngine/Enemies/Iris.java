package com.MRS.NeckbeardEngine.Enemies;

import com.MRS.NeckbeardEngine.*;

public class Iris extends Enemy {
     
     public Iris (State state, int x, int y, double yVelocity, Projectile projectileType, PowerUpPickup heldPowerUp, long timeLine) {
          super(state, x, y, 0, yVelocity, projectileType, heldPowerUp, timeLine);
          health = 2;
     }
     @Override
     public void animate () {
      
          //Swag
          
     }
}
