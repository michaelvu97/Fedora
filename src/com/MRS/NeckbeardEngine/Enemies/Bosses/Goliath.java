package com.MRS.NeckbeardEngine.Enemies.Bosses;

import com.MRS.NeckbeardEngine.HitBox;
import com.MRS.NeckbeardEngine.Projectile;
import com.MRS.NeckbeardEngine.State;

public class Goliath {
  private State state;
  private int health;
  private int x;
  private int y;
  private Projectile projectileType;
  private long timeLine;
  private boolean canShoot;
  
  public static int DEFAULT_HITBOX_WIDTH = 10;
  public static int DEFAULT_HITBOX_LENGTH = 10;
  public Goliath () {
    
  }
