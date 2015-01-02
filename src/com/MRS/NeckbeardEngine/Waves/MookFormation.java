package com.MRS.NeckbeardEngine.Waves;

import com.MRS.NeckbeardEngine.*;
import com.MRS.NeckbeardEngine.Enemies.*;
import com.MRS.NeckbeardEngine.Projectiles.Direction;
import java.util.ArrayList;

public class MookFormation {
  /*
   * For help with waves, refer
   * to "How To Wave.txt" in 
   * the waves package
   */
  
  private static int delayTiming = 20; //in frames
  
  //uninstantiatable
  private MookFormation () {}
  
  public static ArrayList<SpawnEvent> unpack (State state, int currentFrame, int amount, Direction direction) {
    ArrayList<SpawnEvent> spawnEvents = new ArrayList<SpawnEvent>();
    int directionCoeff = 1;
    int startX = -30;
    if (direction == direction.LEFT) {
      directionCoeff = -1;
      startX = Main.WIDTH + 30;
    }
    for (int i = 0; i < amount; i++) {
      spawnEvents.add( new SpawnEvent (
                                       //TODO: make the shot position random
                                       new Mook(state, startX, -50, 3 * directionCoeff, 3, "Shot", null, 0, "form1", (int)(((Main.WIDTH - 100) * Math.random()) + 50))
                                         , currentFrame + (delayTiming * i)
                                      )); System.out.println((int)(((Main.WIDTH - 100) * Math.random()) + 50));
    }
    return spawnEvents;
  }
  
}