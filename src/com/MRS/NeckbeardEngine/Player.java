/* 
 * PROJECT:LodeStar
 * Source can be found at www.github.com/michaelvu97/LodeStar
 * Authors: Qazi Qazi (Project Manager), Roy Liu, Michael Vu
 * Date: 9/17/14
 * 
 * This is the main player class.
 */

package com.MRS.NeckbeardEngine;

import java.util.ArrayList;
import java.awt.*;

public class Player {
     //Fields
     private int lives, x, y;
     private double xVelocity, yVelocity;
     private State state;
     private boolean canShoot;
     private ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
     private ArrayList<HitBox> hitBoxes = new ArrayList<HitBox>();
     
     public static float DRAG = 0.2F;
     public static int MAX_VELOCITY = 5;
     public static float ACCELERATION = 0.7F;
     
     //Constructor
     public Player (int x, int y, int lives, State state) {
          this.x = x;
          this.y = y;
          this.xVelocity = 0;
          this.yVelocity = 0;
          this.lives = lives;
          this.state = state;
          canShoot = true;
     }
     
     //Instance Methods
     public void shoot() {
          //Possibly make this in the superclass Game? that way the shots could be added to a list of PlayerShots
     }
     
     public void dropBomb() {
          //Same as above
     }
     
     public void move () {
          x += xVelocity;
          y += yVelocity;
     }
     
     public void accelerateForward () {
          yVelocity -= ACCELERATION;
          if (yVelocity < -1 * MAX_VELOCITY)
               yVelocity = -1 * MAX_VELOCITY;
     }
     public void accelerateBackward () {
          yVelocity += ACCELERATION;
          if (yVelocity > MAX_VELOCITY)
               yVelocity = MAX_VELOCITY;
          
     }
     public void accelerateRight () {
          xVelocity += ACCELERATION;
          if (xVelocity > MAX_VELOCITY)
               xVelocity = MAX_VELOCITY;
     }
     public void accelerateLeft () {
          xVelocity -= ACCELERATION;
          if (xVelocity < -1 * MAX_VELOCITY) 
               xVelocity = -1 * MAX_VELOCITY;
     }
     
     public void paint(Graphics2D g2d) {
          //Draw this object
     }
     public void switchState() {
          if (state == State.RED) {
               state = State.BLUE;
          } else if (state == State.BLUE) {
               state = State.RED;
          }
     }
     
     //Mutators & Accessors (Leave this stuff at the bottom of the class)
     public int getX() {
          return x;
     }
     public int getY() {
          return y;
     }
     public double getXVelocity() {
          return xVelocity;
     }
     public double getYVelocity() {
          return yVelocity;
     }
     public int getLives() {
          return lives;
     }
     public int getState() { //THIS PROBABLY ISNT GOING TO WORK, MAKE SURE TO FIX IT
          return state.getState(); 
     }
     public ArrayList<HitBox> getHitBoxes() {
          return hitBoxes;
     }
     public ArrayList<PowerUp> getPowerUps() {
          return powerUps;
     }
     public boolean getCanShoot() {
          return canShoot;
     }
     public boolean getAlive() {
          return (lives >= 0);
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
     public void setLives(int lives) {
          this.lives = lives;
     }
     public void setCanShoot(boolean canShoot) {
          this.canShoot = canShoot;
     }
     public void addPowerUp(PowerUp p) {
          powerUps.add(p);
     }
     public void addHitBox(HitBox h) {
          hitBoxes.add(h);
     }
}
