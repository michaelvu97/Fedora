package com.MRS.NeckbeardEngine;

public enum PowerUp {
  FAST_SHOT(0, true),
  RAPID_FIRE(1, true),
  SCATTER_SHOT(2, true),
  BOMB(3,true),
  EXTRA_SHIP(0, false),
  SPEED_BOOST(1, false);
  
  private int powerUp;
  private boolean offensive;
  private PowerUp (int powerUp, boolean offensive) {
    this.powerUp = powerUp;
    this.offensive = offensive;
  }
  
  public int getPowerUp() {
    return powerUp;
  }
  public boolean getOffensive() {
    return offensive;
  }
}
