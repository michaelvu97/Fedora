package com.MRS.NeckbeardEngine;

public class PowerUpPickup {
  private int x;
  private int y;
  private int xVelocity;
  private int yVelocity;
  private PowerUp heldPowerUp;
  
  public PowerUpPickup(int x, int y, int xVelocity, int yVelocity, PowerUp heldPowerUp) {
    this.x = x;
    this.y = y;
    this.xVelocity = xVelocity;
    this.yVelocity = yVelocity;
    this.heldPowerUp = heldPowerUp;
  }
}
