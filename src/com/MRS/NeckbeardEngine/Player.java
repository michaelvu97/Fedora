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
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player {
  
     //Fields
     private int lives, x, y;
     private int bombs;
     private int xVelocity, yVelocity;
     private State state;
     private boolean canShoot;
     private boolean shield;
     private PowerUp offensePowerUp;
     private ArrayList<PowerUp> defensePowerUps = new ArrayList<PowerUp>();
     private HitBox hitBox;
     private int shotCoolDown = 0;
     
     //Constants
     public static int MAXSHOTCOOLDOWN = 20;
     public static int MAX_VELOCITY = 5;
     public static int DEFAULT_HITBOX_WIDTH = 96;
     public static int DEFAULT_HITBOX_HEIGHT = 96;
     
     //Constructor
     public Player (int x, int y, int lives, State state) {
          this.x = x;
          this.y = y;
          this.bombs = 0;
          this.xVelocity = 0;
          this.yVelocity = 0;
          this.lives = lives;
          this.state = state;
          this.hitBox = new HitBox(x, y, DEFAULT_HITBOX_WIDTH, DEFAULT_HITBOX_HEIGHT);
          canShoot = true;
     }
     
     //Instance Methods
     
     public void move () {
          x += xVelocity;
          y += yVelocity;
          hitBox.setX(x);
          hitBox.setY(y);
     }
     
     public void accelerateForward () {
          yVelocity = -MAX_VELOCITY;
     }
     public void accelerateBackward () {
          yVelocity = MAX_VELOCITY;
     }
     public void accelerateRight () {
          xVelocity = MAX_VELOCITY;
     }
     public void accelerateLeft () {
          xVelocity = - MAX_VELOCITY;
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
     public int getXVelocity() {
          return xVelocity;
     }
     public int getYVelocity() {
          return yVelocity;
     }
     public int getLives() {
          return lives;
     }
     public State getState() {
          return state;
     }
     public HitBox getHitBox() {
          return hitBox;
     }
     public PowerUp getOffensePowerUp() {
          return offensePowerUp;
     }
     public ArrayList<PowerUp> getDefensePowerUps() {
       return defensePowerUps;
     }
     public boolean canShoot() {
          return canShoot;
     }
     public boolean getAlive() {
          return (lives >= 0);
     }
     public int getShotCoolDown() {
          return shotCoolDown;
     }
     public int getBombs() {
       return bombs;
     }
     
     public void setX(int x) {
          this.x = x;
     }
     public void setY(int y) {
          this.y = y;
     }
     public void setState(State state) {
       this.state = state;
     }
     public void setXVelocity(int xVelocity) {
          this.xVelocity = xVelocity;
     }
     public void setYVelocity(int yVelocity) {
          this.yVelocity = yVelocity;
     }
     public void setLives(int lives) {
          this.lives = lives;
     }
     public void setCanShoot(boolean canShoot) {
          this.canShoot = canShoot;
     }
     public void setShotCoolDown(int cd) {
          shotCoolDown = cd;
     }
     public void setOffensePowerUp(PowerUp p) {
          this.offensePowerUp = p;
     }
     public void addDefensePowerUp(PowerUp p) {
       this.defensePowerUps.add(p);
     }
     public void removeDefensePowerUp(PowerUp p) {
       this.defensePowerUps.remove(p);
     }
     public void setBombs(int i) {
       bombs = i;
     }
     
     public void paint (Graphics2D g) {    
       String workingDir = System.getProperty("user.dir"); 
       BufferedImage img_ship = null;
       String path_ship = "";
       if (state == State.RED) {
         path_ship = workingDir + FileStore.PLAYER_RED;
       } else if (state == State.BLUE){
         path_ship = workingDir + FileStore.PLAYER_BLUE;
       }
       try {
         g.drawImage(ImageIO.read(new File (path_ship)), x, y, null);
       } catch (IOException e) {
         e.printStackTrace();
       }
     }
}
