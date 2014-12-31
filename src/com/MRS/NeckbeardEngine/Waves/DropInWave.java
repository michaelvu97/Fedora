package com.MRS.NeckbeardEngine.Waves;

import com.MRS.NeckbeardEngine.*;
import com.MRS.NeckbeardEngine.Enemies.*;
import java.util.ArrayList;

public class DropInWave {
  
  //uninstantiatable
  private DropInWave () {}
  
  public static ArrayList<SpawnEvent> unpack (State state, int currentFrame) {
    ArrayList<SpawnEvent> spawnEvents = new ArrayList<SpawnEvent>();
    
    spawnEvents.add( new SpawnEvent (
                                     new Mook(state, -30, -50, 3, 4, "Shot", null, 0, "stay", Main.WIDTH/2) 
                                     , currentFrame
                                     ));
    
    spawnEvents.add( new SpawnEvent (
                                     new Mook(state, -30 - Mook.DEFAULT_HITBOX_WIDTH, -50, 3, 4, "Shot", null, 0, "stay", Main.WIDTH/2 - Mook.DEFAULT_HITBOX_WIDTH) 
                                     , currentFrame + 30
                                     ));
    
    spawnEvents.add( new SpawnEvent (
                                     new Mook(state, -30 - (2 * Mook.DEFAULT_HITBOX_WIDTH), -50, 3, 4, "Shot", null, 0, "stay", Main.WIDTH/2 - (2*Mook.DEFAULT_HITBOX_WIDTH)) 
                                     , currentFrame + 60
                                     ));
    
    return spawnEvents;
  }
  
}