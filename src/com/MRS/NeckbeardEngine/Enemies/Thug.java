package com.MRS.NeckbeardEngine.Enemies;

public class Thug extends Enemy {
  
   public Thug (int x, int y, int xVelocity, int yVelocity, PowerUpPickup powerUpPickup, State state) {

    this.x = x;
    this.y = y;
    this.xVelocity = xVelocity;
    this.yVelocity = yVelocity;
    this.heldPowerUp = powerUpPickup;
    this.state = state;
  }
  
  @Override
  public void animate () {
    //thug lyfe
  }
}
