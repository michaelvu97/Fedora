package com.MRS.NeckbeardEngine.Enemies;

import com.MRS.NeckbeardEngine.*;

public class Vortex extends Enemy {
     
     public Vortex (State state, int x, int y, int xVelocity, int yVelocity, Projectile projectileType, PowerUpPickup heldPowerUp, long timeLine) {
          super(state, x, y, xVelocity, yVelocity, projectileType, heldPowerUp, timeLine);
          health = 4;
     }
     @Override
     public void animate () {
      
          //Swag
          
     }
}