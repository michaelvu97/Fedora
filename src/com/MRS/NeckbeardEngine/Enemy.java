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
     public State state;//enums
     public int health;
     public int x;
     public int y;
     public int xVelocity;
     public int yVelocity;
     public Projectile  projectileType;//will be more specific for every enemy
     public PowerUpPickup heldPowerUp;//if they have power up it is a type if not it is null
     public long timeLine;//used to determine at what time they will enter the game screen/spawn
     
     abstract public void animate();//to animate the enemy
     
     
}
