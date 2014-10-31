package com.MRS.NeckbeardEngine;
class PowerUpPickup {
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
    this.heldPowerup = heldPowerup;
  }
}
