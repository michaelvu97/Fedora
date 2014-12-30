package com.MRS.NeckbeardEngine;

import com.MRS.NeckbeardEngine.Enemies.*;
import java.util.ArrayList;

public abstract class Wave {
  //The abstraction for waves of enemies
  //that get deployed in main, held in 
  //level
  
  protected ArrayList<Enemy> enemies;
  
  public Wave () {
    enemies = new ArrayList<Enemy>();
  }
  
  public ArrayList<Enemy> unpack () {
    //returns the wave as a list
    return enemies;
  }
}