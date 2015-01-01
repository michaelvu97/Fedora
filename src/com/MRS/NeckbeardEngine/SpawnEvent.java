package com.MRS.NeckbeardEngine;

public class SpawnEvent {
  /* README
   * SpawnEvents are simply a pairing of an enemy
   * and the frame at which it should spawn.
   * 
   * SpawnEvents are opened in levels and are
   * unpacked from a wave.
   * 
   * The purpose of a 
   * SpawnEvent is to be able to offset the 
   * Enemies spawned in a wave, without having to
   * modify the level too greatly.
   * 
   * See the Waves package for the different waves
   * and implementation of SpawnEvents
   * 
   */
  
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
  
  public void setSpawnFrame(int newFrame) {
    this.spawnFrame = newFrame;
  }
  
  public void setSpawnable(Enemy e) {
    this.spawnable = e;
  }
}