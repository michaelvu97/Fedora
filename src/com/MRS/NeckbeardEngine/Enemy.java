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
     protected int xVelocity;
     protected int yVelocity;
     protected Projectile  projectileType;//will be more specific for every enemy
     protected PowerUpPickup heldPowerUp;//if they have power up it is a type if not it is null
     protected long timeLine;//used to determine at what time they will enter the game screen/spawn
     public(State state, int health, int x, int y, int xVelocity, int yVelocity, Projectile projectileType, PowerUpPickUp heldPowerUp, long timeLine) {
      this.state = state;
      this.health = health;
      this.x = x;
      this.y = y;
      this.xVelocity = xVelocity;
      this.yVelocity = yVelocity;
      this.projectileType = projectileType;
      this.heldPowerUpPickUp = heldPowerUpPickUp;
     }
}
    
     
     abstract public void animate();//to animate the enemy
     
     
}
