*PROJECT: LodeStar
 * Source can be fooun at www.github.com/michaelvu97/LodeStar
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
  private PowerUpPickup helpPowerUp;//if they have power up it is a type if not it is null
  private int timeLine;//used to determine at what time they will enter the game screen/spawn
  
  abstract public Enemy(int x int y, State state, int xVelocity, int yVelocity, PowerUpPickup heldPowerUp);//only asks for fields which are variable between every instance
  abstract public void animate();//to animate the enemy
}