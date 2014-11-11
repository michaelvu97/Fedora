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
     protected State state;
     protected int x;
     protected int y;
     protected double xVelocity;
     protected double yVelocity;
     protected String imgPath;
     protected HitBox hitBox;
     
     //Static fields
     public static double ShotVelocity = 1;
     public static double ScatterShotXVelocity = 1;
     public static double ScatterShotYVelocity = 1;
     public static double FastShotVelocity = 2;
     
     //Constructor
     public Projectile (State state, int x, int y, double xVelocity, double yVelocity, String imgPath) {
          this.state = state;
          this.x = x;
          this.y = y;
          this.xVelocity = xVelocity;
          this.yVelocity = yVelocity;
          this.imgPath = imgPath;
     }
     //Accessors
     public State getState () {
          return state;
     }
     public int getX () {
          return x;
     }
     public double getY () {
          return y;
     }
     public double getXVelocity () {
          return xVelocity;
     }
     public int getYVelocity () {
          return yVelocity;
     }
     public String getImgPath () {
          return imgPath;
     }
     public HitBox getHitBox () {
          return hitBox;
     }
     
     //Mutators
     public void setState (State state) {
          this.state = state;
     }
     public void setX (int x) {
          this.x = x;
     }
     public void setY (int y) {
          this.y = y;
     }
     public void setXVelocity (double xVelocity) {
          this.xVelocity = xVelocity;
     }
     public void setYVelocity (double yVelocity) {
          this.yVelocity = yVelocity;
     }
     public void setImgPath (String imgPath) {
          this.imgPath = imgPath;
     }
     public void setHitBox (HitBox hitBox) {
          this.hitBox = hitBox;
     }
}

