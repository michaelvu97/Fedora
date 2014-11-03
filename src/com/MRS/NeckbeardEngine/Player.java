/* 
 * PROJECT:LodeStar
 * Source can be found at www.github.com/michaelvu97/LodeStar
 * Authors: Qazi Qazi (Project Manager), Roy Liu, Michael Vu
 * Date: 9/17/14
 * 
 * This is the main player class.
 */
 
package com.MRS.NeckbeardEngine;

import java.util.ArrayList;
import java.util.List;

public class Player {
  //Fields
  private int lives, x, y;
  private State state;
  private boolean canShoot;
  private ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
  private ArrayList<HitBox> hitBoxes = new ArrayList<HitBox>();
  
  //Constructor
  public Player (int x, int y, int lives, State state) {
    this.x = x;
    this.y = y;
    this.lives = lives;
    this.state = state;
    canShoot = true;
  }
  
  //Instance Methods
  public void shoot() {
    //Possibly make this in the superclass Game? that way the shots could be added to a list of PlayerShots
  }
  
  public void dropBomb() {
    //Same as above
  }
  
  public void switchState() {
    if (state == State.RED) {
      state = State.BLUE;
    } else if (state == State.BLUE) {
      state = State.RED;
    }
  }
  
  //Mutators & Accessors (Leave this stuff at the bottom of the class)
  public int getX() {
    return x;
  }
  public int getY() {
    return y;
  }
  public int getLives() {
    return lives;
  }
  public int getState() { //THIS PROBABLY ISNT GOING TO WORK, MAKE SURE TO FIX IT
    return state.state(); 
  }
  public ArrayList<HitBox> getHitBoxes() {
    return hitBoxes;
  }
  public ArrayList<PowerUp> getPowerUps() {
    return powerUps;
  }
  public boolean getCanShoot() {
    return canShoot;
  }
  public boolean getAlive() {
    return (lives >= 0);
  }
  
  public void setX(int x) {
    this.x = x;
  }
  public void setY(int y) {
    this.y = y;
  }
  public void setLives(int lives) {
    this.lives = lives;
  }
  public void setCanShoot(boolean canShoot) {
    this.canShoot = canShoot;
  }
  public void addPowerUp(PowerUp p) {
    powerUps.add(p);
  }
  public void addHitBox(HitBox h) {
    hitBoxes.add(h);
  }
}
