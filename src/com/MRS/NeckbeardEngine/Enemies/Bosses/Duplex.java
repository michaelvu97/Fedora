package com.MRS.NeckbeardEngine.Enemies.Bosses;

import com.MRS.NeckbeardEngine.HitBox;
import com.MRS.NeckbeardEngine.Projectile;
import com.MRS.NeckbeardEngine.State;

public class Duplex {
  private State state;
  private int health;
  private int x;
  private int y;
  private double xVelocity;
  private double yVelocity;
  private Projectile projectileType;
  private int shiftTimer; //not sure if we should use this or just manipulate timeLine
  private long timeLine;
  private boolean canShoot;
  
  public static int DEFAULT_HITBOX_WIDTH = 10;
  public static int DEFAULT_HITBOX_LENGTH = 10;
  
  public Duplex () {
    state = State.RED;
    health = 100;
    x = 800; //placeholder
    y = 800; //placeholder
    xVelocity = 1;
    yVelocity = 1;
    projectileType = placeholder;
    shiftTimer = 5; //Duplex shifts every 5 sec
    timeLine = 0; //placeholder
    canShoot = true;
  }
}
