package com.MRS.NeckbeardEngine.Enemies;

public class Thug extends Enemy {
  
   public Thug (int x, int y, int xVelocity, int yVelocity, PowerUpPickup powerUpPickup) {

    this.x = x;
    this.y = y;
    this.xVelocity = xVelocity;
    this.yVelocity = yVelocity;
    this.heldPowerUp = powerUpPickup;
  }
  
  @Override
  public void animate () {
    //thug lyfe
  }
}
