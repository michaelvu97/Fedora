package com.MRS.NeckbeardEngine.Waves;

import com.MRS.NeckbeardEngine.Enemies.*;
import com.MRS.NeckbeardEngine.*;
import java.util.ArrayList;

public class ShifterWave {
  /*
   * For help with waves, refer
   * to "How To Wave.txt" in 
   * the waves package
   */
  
  //uninstatiatable
  private ShifterWave () {}
  
  /*
   * A simple Shifter Wave: spawns a Shifter at a given xLocation, offscreen, and with a given state
   */
  
  public static ArrayList<SpawnEvent> unpack (State targetState, int currentFrame, Player target) {
    ArrayList<SpawnEvent> events = new ArrayList<SpawnEvent>();
    
    events.add (new SpawnEvent (
                                     new Shifter(targetState, -72, 100, 3, 0, "Shot", null, 0, "stay", target, 0)
                                     , currentFrame
                                     ));
    
    return events;
  }
  
}