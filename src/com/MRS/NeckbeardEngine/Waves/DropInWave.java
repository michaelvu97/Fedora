package com.MRS.NeckbeardEngine.Waves;

import com.MRS.NeckbeardEngine.*;
import com.MRS.NeckbeardEngine.Enemies.*;
import java.util.ArrayList;

public class DropInWave extends Wave {
  public DropInWave () {
    super();
    
    //probability of states
    int chance = (int) (Math.random() * 2);
    State waveType = State.RED;
    if (chance == 0) {
      waveType = State.RED;
    } else {
      waveType = State.BLUE;
    }
    enemies.add(new Mook(waveType, -30, -50, 3, 4, "Shot", null, 0, "stay", Main.WIDTH/4));
  }
}