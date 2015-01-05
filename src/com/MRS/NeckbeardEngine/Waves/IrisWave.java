package com.MRS.NeckbeardEngine.Waves;

import com.MRS.NeckbeardEngine.Enemies.*;
import com.MRS.NeckbeardEngine.*;
import java.util.ArrayList;

public class IrisWave {
  /*
   * For help with waves, refer
   * to "How To Wave.txt" in 
   * the waves package
   */
  
  //uninstatiatable
  private IrisWave () {}
  
  /*
   * A simple Iris wave: spawns an iris at a given xLocation, offscreen, and with a given state
   */
  
  public static ArrayList<SpawnEvent> unpack (State targetState, int currentFrame, int xLocation) {
    ArrayList<SpawnEvent> events = new ArrayList<SpawnEvent>();
    
    events.add (new SpawnEvent (
                                     new Iris(targetState, xLocation, -50, 4, "Laser") 
                                     , currentFrame
                                     ));
    
    return events;
  }
  
}