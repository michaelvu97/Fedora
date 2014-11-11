/*PROJECT: LodeStar
 * Source can be found at www.github.com/michaelvu97/LodeStar
 * Authors: Safwan Qazi(Project Manager), Roy Liu, Michael Vu
 * Date:10/30/2014
 * 
 * Description:
 * This is the abstract which determines what fields and methods all enemies will have.
 */
package com.MRS.NeckbeardEngine;
public abstract class Enemy {
  protected State state;//enums
  protected int health;
  protected int x;
  protected int y;
  protected double xVelocity;
  protected double yVelocity;
  protected Projectile  projectileType;//will be more specific for every enemy
  protected PowerUpPickup heldPowerUp;//if they have power up it is a type if not it is null
  protected long timeLine;//used to determine at what time they will enter the game screen/spawn
  public Enemy(State state, int x, int y, double xVelocity, double yVelocity, Projectile projectileType, PowerUpPickup heldPowerUp, long timeLine) {
    this.state = state;
    this.x = x;
    this.y = y;
    this.xVelocity = xVelocity;
    this.yVelocity = yVelocity;
    this.projectileType = projectileType;
    this.heldPowerUp = heldPowerUp;
    this.timeLine = timeLine;
  }
  
  public State getState() {
    return state;   
  }
  public void setState(State state) {
    this.state = state;
  }
  public int getHealth() {
    return health;   
  }
  public void setHealth(int health) {
    this.health = health;
  }
  public int getX() {
    return x;
  }
  public void setX(int x) {
    this.x = x;
  }
  public int getY() {
    return y;
  }
  public void setY(int y) {
    this.y = y;
  }
  public double getXVelocity() {
    return xVelocity;
  }
  public void setXVelocity(double xVelocity) {
    this.xVelocity = xVelocity;
  }
  public double getYVelocity() {
    return yVelocity;
  }
  public void setYVelocity(double yVelocity) {
    this.yVelocity = yVelocity;
  }
  public Projectile getProjectileType() {
    return projectileType;
  }
  public void setProjectileType(Projectile projectileType) {
    this.projectileType = projectileType;
  }
  public PowerUpPickup getHeldPowerUp() {
    return heldPowerUp;
  }
  public void setHeldPowerUp(PowerUpPickup heldPowerUp) {
    this.heldPowerUp = heldPowerUp;
  }
  public long getTimeLine () {
    return timeLine;
  }
  public void setTimeLine(long timeLine) {
    this.timeLine = timeLine;
  }
  
  abstract public void animate();//to animate the enemy
  
}
