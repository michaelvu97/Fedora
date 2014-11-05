/*PROJECT: LodeStar
 * Source can be found at www.github.com/michaelvu97/LodeStar
 * Authors: Safwan Qazi(Project Manager), Roy Liu, Michael Vu
 * Date:10/30/2014
 * 
 * Description:
 * This is the abstract which determines what fields and methods all enemies will have.
 */
package com.MRS.NeckbeardEngine;
abstract class Enemy {
  private State state;//enums
  private int health;
  private int x;
  private int y;
  private int xVelocity;
  private int yVelocity;
  private Projectile  projectileType;//will be more specific for every enemy
  private PowerUpPickup heldPowerUp;//if they have power up it is a type if not it is null
  private long timeLine;//used to determine at what time they will enter the game screen/spawn
  
  //Accessors
  public int getState() { 
       return state.state();
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
  public int getYVelocity() {
       return yVelocity;
  }
  public int getXVelocity() {
       return xVelocity;
  }
  public Projectile projectileType() {
       return projectileType;
  }
  public PowerUpPickup heldPowerUp() {
       return heldPowerUp;
  }
  public long timeLine() {
       return timeLine;
  }
  
  abstract public void animate();//to animate the enemy
  
  
}
