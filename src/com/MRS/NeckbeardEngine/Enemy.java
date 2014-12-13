/*PROJECT: LodeStar
 * Source can be found at www.github.com/michaelvu97/LodeStar
 * Authors: Safwan Qazi(Project Manager), Roy Liu, Michael Vu
 * Date:10/30/2014
 * 
 * Description:
 * This is the abstract which determines what fields and methods all enemies will have.
 */
package com.MRS.NeckbeardEngine;

import java.awt.Graphics2D;

public abstract class Enemy {
  protected State state;//enums
  protected int health;
  protected int x;
  protected int y;
  protected int startX;
  protected int startY;
  protected double xVelocity;
  protected double yVelocity;
  protected String  projectileType;//will be more specific for every enemy
  protected PowerUpPickup heldPowerUp;//if they have power up it is a type if not it is null
  protected HitBox hitBox;//hitbox not set to anything yet, because that will happen in the individual enemy classes
  protected long timeLine;//used to determine at what time they will enter the game screen/spawn
  protected int shotCoolDown;//to calculate when to shoot
  
  public Enemy(State state, int x, int y, double xVelocity, double yVelocity, String projectileType, PowerUpPickup heldPowerUp, long timeLine) {
    this.state = state;
    this.x = x;
    this.y = y;
    this.xVelocity = xVelocity;
    this.yVelocity = yVelocity;
    this.projectileType = projectileType;
    this.heldPowerUp = heldPowerUp;
    this.timeLine = timeLine;
    startX = x;
    startY = y;
  }
  
  public void move(boolean collide) {
    /*
     * Increments the position by every
     * frame of time
     */
    x += xVelocity;
    y += yVelocity;
    hitBox.setX(x);
    hitBox.setY(y);
  }
  
  //Accessors
  public State getState() {
    return state;   
  }
  public int getHealth() {
    return health;   
  }
  public int getX() {
    return x;
  }
  public int getY() {
    return y;
  }
  public HitBox getHitBox() {
    return hitBox;
  }
  public double getXVelocity() {
    return xVelocity;
  }
  public double getYVelocity() {
    return yVelocity;
  }
  public String getProjectileType() {
    return projectileType;
  }
  public PowerUpPickup getHeldPowerUp() {
    return heldPowerUp;
  }
  public long getTimeLine() {
    return timeLine;
  }
  public int getShotCoolDown() {
    return shotCoolDown;
  }
  
  //Mutators
  public void setState(State state) {
    this.state = state;
  }
  public void setHealth(int health) {
    this.health = health;
  }
  public void setX(int x) {
    this.x = x;
  }
  public void setY(int y) {
    this.y = y;
  }
  public void setXVelocity(double xVelocity) {
    this.xVelocity = xVelocity;
  }
  public void setYVelocity(double yVelocity) {
    this.yVelocity = yVelocity;
  }
  public void setProjectileType(String projectileType) {
    this.projectileType = projectileType;
  }
  public void setHeldPowerUp(PowerUpPickup heldPowerUp) {
    this.heldPowerUp = heldPowerUp;
  }
  public void setTimeLine(long timeLine) {
    this.timeLine = timeLine;
  }
  public void setShotCoolDown(int shotCoolDown) {
    this.shotCoolDown = shotCoolDown;
  }
  abstract public void paint(Graphics2D g);
  abstract public boolean onScreen();
  abstract public boolean canShoot();
  abstract public void resetShotCoolDown();
  
}
