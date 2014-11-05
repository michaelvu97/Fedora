/*
 * PROJECT: LodeStar
 * Source code can be found at www.github.com/michaelvu97/LodeStar
 * Authors: Safwan Qazi (Project Manager), Michael Vu, Roy Liu
 * Inception Date: 9/17/14
 *
 * The abstraction for any projectiles in game.
 */

package com.MRS.NeckbeardEngine;

public abstract class Projectile {
     
     //Fields
     private State state;
     private int x;
     private int y;
     private int xVelocity;
     private int yVelocity;
     
     private String imgPath;
     private HitBox hitBox;
     
     //Static fields
     public static int playerShotVelocity = 1;
     public static int playerScatterShotXVelocity = 1;
     public static int playerScatterShotYVelocity = 1;
     public static int playerFastShotVelocity = 2;
     
     //Accessors
     abstract int getState ();
     abstract int getX ();
     abstract int getY ();
     abstract int getXVelocity ();
     abstract int getYVelocity ();
     abstract String getImgPath ();
     abstract HitBox getHitBox ();
     
     //Mutators
     abstract void setState (int state);
     abstract void setX (int x);
     abstract void setY (int y);
     abstract void setXVelocity (int xVelocity);
     abstract void setYVelocity (int yVelocity);
     abstract void setImgPath (String imgPath);
     abstract void setHitBox (HitBox hitBox);
}

