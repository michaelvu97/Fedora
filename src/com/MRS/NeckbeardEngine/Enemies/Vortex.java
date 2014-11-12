package com.MRS.NeckbeardEngine.Enemies;

<<<<<<< HEAD
import com.MRS.NeckbeardEngine.Enemy;
import com.MRS.NeckbeardEngine.HitBox;
import com.MRS.NeckbeardEngine.PowerUpPickup;
import com.MRS.NeckbeardEngine.Projectile;
import com.MRS.NeckbeardEngine.State;
=======
import com.MRS.NeckbeardEngine.*;
import com.MRS.NeckbeardEngine.*;
>>>>>>> origin/master

public class Vortex extends Enemy {
	
	public static int DEFAULT_HITBOX_WIDTH = 5;
    public static int DEFAULT_HITBOX_HEIGHT = 5;
    
     
     public static int DEFAULT_HITBOX_WIDTH = 10;
     public static int DEFAULT_HITBOX_HEIGHT = 10;
     
     public Vortex (State state, int x, int y, double xVelocity, double yVelocity, Projectile projectileType, PowerUpPickup heldPowerUp, long timeLine, boolean canShoot) {
          super(state, x, y, xVelocity, yVelocity, projectileType, heldPowerUp, timeLine, canShoot);
          hitBox = new HitBox(x, y, DEFAULT_HITBOX_WIDTH, DEFAULT_HITBOX_HEIGHT);
          health = 4;
          hitBox = new HitBox (x, y, DEFAULT_HITBOX_WIDTH, DEFAULT_HITBOX_HEIGHT);
     }
     @Override
     public void animate () {
      
          //Swag
          
     }
}
