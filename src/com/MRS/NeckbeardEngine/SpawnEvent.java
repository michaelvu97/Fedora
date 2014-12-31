package com.MRS.NeckbeardEngine;

public class SpawnEvent {
  private int spawnFrame;
  private Enemy spawnable;
  
  public SpawnEvent (Enemy e, int spawnFrame) {
    //all times are measured in In-Game frames
    this.spawnable = e;
    this.spawnFrame = spawnFrame;
  }
  
  public int getSpawnFrame() {
    return spawnFrame;
  }
  
  public Enemy getSpawnable() {
   return spawnable; 
  }
}