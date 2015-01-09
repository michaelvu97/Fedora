/* 
 * PROJECT:LodeStar
 * Source can be found at www.github.com/michaelvu97/LodeStar
 * Authors: Safwan Qazi (Project Manager), Roy Liu, Michael Vu
 * Date: 9/17/14
 * 
 * This class holds information on the different types of power ups
 */
package com.MRS.NeckbeardEngine;

public enum PowerUp {
  FAST_SHOT(0, true),
  RAPID_FIRE(1, true),
  SCATTER_SHOT(2, true),
  BOMB(3,true),
  EXTRA_SHIP(0, false),
  SPEED_BOOST(1, false),
  SHIELD(3, false);
  
  private int powerUp;
  private boolean offensive;
  private PowerUp (int powerUp, boolean offensive) {
    this.powerUp = powerUp;
    this.offensive = offensive;
  }
  
  //Constructor controls whether or not a power up is spawned when an enemy dies
  public static PowerUp getPowerUp(int dropChance, int fastShot, int rapidFire, 
                                   int scatterShot, int bomb, int extraShip, 
                                   int speedBoost, int shield) {
    
    int dropCheck = (int)(100*Math.random()+1);
    shield += fastShot+rapidFire+scatterShot+bomb+extraShip+speedBoost;
    speedBoost += fastShot+rapidFire+scatterShot+bomb+extraShip;
    extraShip += fastShot+rapidFire+scatterShot+bomb;
    bomb += fastShot+rapidFire+scatterShot;
    scatterShot += fastShot+rapidFire;
    rapidFire += fastShot;
    
    if(dropCheck >= 1 && dropCheck <= dropChance){
      int typeCheck = (int)(100*Math.random()+1);
      if(typeCheck >= 1 && typeCheck <= fastShot)
        return FAST_SHOT;
      else if(typeCheck > fastShot && typeCheck <= rapidFire)
        return RAPID_FIRE;
      else if(typeCheck > rapidFire && typeCheck <= scatterShot)
        return SCATTER_SHOT;
      else if(typeCheck > scatterShot && typeCheck <= bomb)
        return BOMB;
      else if(typeCheck > bomb && typeCheck <= extraShip)
        return EXTRA_SHIP;
      else if(typeCheck > extraShip && typeCheck <= speedBoost)
        return SPEED_BOOST;
      else if(typeCheck > speedBoost && typeCheck <= shield)
        return SHIELD;
      else
        return null;
    }
    else
      return null;
  }
  
  //Accessor
  public boolean getOffensive() {
    return offensive;
  }
}
