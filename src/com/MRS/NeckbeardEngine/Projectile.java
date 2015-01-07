/*
 * PROJECT: LodeStar
 * Source code can be found at www.github.com/michaelvu97/LodeStar
 * Authors: Safwan Qazi (Project Manager), Michael Vu, Roy Liu
 * Inception Date: 9/17/14
 *
 * The abstraction for any projectiles in game.
 */

package com.MRS.NeckbeardEngine;
import java.awt.*;
public abstract class Projectile {
     
     //Fields
     protected State state;
     protected int x;
     protected int y;
     protected double xVelocity;
     protected double yVelocity;
     protected HitBox hitBox;
     protected double duration;
     protected double killTime;
     
     //Static fields
     public static double ShotVelocity = 8;
     public static double ScatterShotXVelocity = 3;
     public static double FastShotVelocity = 16;
     
     //Constructor
     public Projectile (State state, int x, int y, double xVelocity, double yVelocity, double duration) {
          this.state = state;
          this.x = x;
          this.y = y;
          this.xVelocity = xVelocity;
          this.yVelocity = yVelocity;
          this.duration = duration;
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
     public double getYVelocity () {
          return yVelocity;
     }
     public HitBox getHitBox () {
          return hitBox;
     }
     public double getDuration () {
          return duration;
     }
     public double getKillTime () {
       return killTime;
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
     public void setHitBox (HitBox hitBox) {
          this.hitBox = hitBox;
     }
     public void setDuration (double duration) {
          this.duration = duration;
     }
     
     public void setKillTime (double killTime) {
       this.killTime = killTime;
     }
     
     public void move () {
       x += xVelocity;
       y += yVelocity;
       hitBox.setX(x);
       hitBox.setY(y);
     }
     //Abstract methods
     abstract public void paint(Graphics2D g2d);
}

