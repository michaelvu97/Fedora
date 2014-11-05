package com.MRS.NeckbeardEngine.Enemies;

import com.MRS.NeckbeardEngine.*;

public class Mook extends Enemy {
     
     public Mook (int x, int y, int xVelocity, int yVelocity, PowerUpPickup powerUpPickup) { //Health?
          this.x = x;
          this.y = y;
          this.xVelocity = xVelocity;
          this.yVelocity = yVelocity;
          this.heldPowerUp = powerUpPickup;
          //Shot type
     }
     @Override
     public void animate () {
      
          //Swag
          
     }
}
