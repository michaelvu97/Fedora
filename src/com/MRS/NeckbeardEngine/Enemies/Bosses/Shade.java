package com.MRS.NeckbeardEngine.Enemies.Bosses;

import com.MRS.NeckbeardEngine.HitBox;
import com.MRS.NeckbeardEngine.Projectile;
import com.MRS.NeckbeardEngine.State;

public class Shade {
  private State state;
  private int health;
  private int x;
  private int y;
  private double xVelocity;
  private double yVelocity;
  private Projectile projectileType;
  private int shiftTimer;
  private int weaponTimer;
  private int bombTimer;
  private int neutTimer;
  private long timeLine;
  private boolean canShoot;
  
  public static int DEFAULT_HITBOX_WIDTH = 10;
  public static int DEFAULT_HITBOX_LENGTH = 10;
  public Shade () {
    state = BLUE; //I don't know how enum works
    health = 70;
    x = 800; //placeholder
    y = 800; //placeholder
    xVelocity = 1;
    yVelocity = 1;
    projectileType = placeholder;
    shiftTimer = 8; //shift has an 8 sec cd
    weaponTimer = 15; //weapon switches have a 15 sec cd
    bombTimer = 20; //bombs have a 20 sec cd
    neutTimer = 30; //Shade neutralizer has a 30 sec cd
    timeLine = 0; //placeholder
    canShoot = true;
  }
}
