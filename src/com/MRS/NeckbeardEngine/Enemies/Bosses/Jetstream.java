package com.MRS.NeckbeardEngine.Enemies.Bosses;

import com.MRS.NeckbeardEngine.HitBox;
import com.MRS.NeckbeardEngine.Projectile;
import com.MRS.NeckbeardEngine.State;

public class Jetstream {
  private State state; //only affects projectiles
  private int health;
  private int x;
  private int y;
  private double xVelocity;
  private double yVelocity;
  private Projectile projectileType;
  private long timeLine;
  private boolean canShoot;
  
  public static int DEFAULT_HITBOX_WIDTH = 10;
  public static int DEFAULT_HITBOX_LENGTH = 10;
  
  public Jetstream () {
    state = RED; //I don't know how enum works
    health = 100;
    x = 800; //placeholder
    y = 800; //placeholder
    xVelocity = 2;
    yVelocity = 2;
    projectileType = placeholder;
    timeLine = 0; //placeholder
    canShoot = true;
  }
}
