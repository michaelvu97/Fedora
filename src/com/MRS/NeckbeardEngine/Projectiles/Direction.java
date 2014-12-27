package com.MRS.NeckbeardEngine.Projectiles;

public enum Direction {
  UP(1),
  DOWN(2),
  LEFT(3),
  RIGHT(4);
  
  private final int direction;
  
  private Direction (int direction) {
    this.direction = direction;
  }
  
  public int getValue() {
    return direction;
  }

}