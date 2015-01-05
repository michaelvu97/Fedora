package com.MRS.NeckbeardEngine.Waves;

import com.MRS.NeckbeardEngine.Enemies.*;
import com.MRS.NeckbeardEngine.*;
import java.util.ArrayList;

public class StarburtWave {
  /*
   * For help with waves, refer
   * to "How To Wave.txt" in 
   * the waves package
   */
  
  //uninstatiatable
  private StarburtWave () {}
  
  /*
   * A simple Starburt wave: spawns a starburt at a given xLocation, offscreen, and with a given state
   */
  
  public static ArrayList<SpawnEvent> unpack (State targetState, int currentFrame, int xLocation) {
    ArrayList<SpawnEvent> events = new ArrayList<SpawnEvent>();
    
    events.add (new SpawnEvent (
                                     new Starburt(targetState, -200, 100, 2.5, 10, "StarburtShot")
                                     , currentFrame
                                     ));
    
    return events;
  }
  
}