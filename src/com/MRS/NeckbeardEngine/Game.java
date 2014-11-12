/* 
 * PROJECT:LodeStar
 * Source can be found at www.github.com/michaelvu97/LodeStar
 * Authors: Qazi Qazi (Project Manager), Roy Liu, Michael Vu
 * Date: 9/17/14
 *
 * This is the panel where all calculations and input/output is performed
 */

package com.MRS.NeckbeardEngine;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.MRS.NeckbeardEngine.Enemies.*;
import com.MRS.NeckbeardEngine.Projectiles.*;

public class Game extends JPanel implements KeyListener, MouseListener {
     
     public boolean started; //If the game has begun, this may become deprecated depending on how levels are handled
     
     public KeyInputHandler keyInputHandler; //contains booleans for all keys
     
     //On screen object lists
     public Player player;
     public ArrayList<Enemy> enemies = new ArrayList<Enemy>();
     
     //More on screen object lists
     public ArrayList<HitScan> hitScans = new ArrayList<HitScan>();
     public ArrayList<Projectile> enemyProjectiles = new ArrayList<Projectile>();
     public ArrayList<Projectile> playerProjectiles = new ArrayList<Projectile>();
     public ArrayList<PowerUpPickup> powerUpPickups = new ArrayList<PowerUpPickup>();
     
     public Level level;
     public Game () {
          //Class constructor
          keyInputHandler = new KeyInputHandler ();
          initialize();
          
     }
     
     public void initialize () {
          //Variable setup
          started = false;
          addKeyListener(this);
          addMouseListener(this);
          
          player = new Player(30, 30, 3, State.RED);
     }
     
     public void step () {
          /*
           * Called by main every time interval
           */
          
          //Propulsion
          if (keyInputHandler.up && !keyInputHandler.down) {
               player.accelerateForward();
          }
          if (keyInputHandler.down && !keyInputHandler.up) {
               player.accelerateBackward();
          }
          if (keyInputHandler.right && !keyInputHandler.left) {
               player.accelerateRight();
          }
          if (keyInputHandler.left && !keyInputHandler.right) {
               player.accelerateLeft();
          }
          
          //Vertical Drag
          if (!keyInputHandler.up && !keyInputHandler.down && player.getYVelocity() != 0) {
               if (Math.abs(player.getYVelocity()) <= Player.DRAG) {
                    player.setYVelocity(0);
               } else {
                    if (player.getYVelocity() > 0) {
                         player.setYVelocity(player.getYVelocity() - Player.DRAG);//just set it to 0
                    } else {
                         player.setYVelocity(player.getYVelocity() + Player.DRAG);//just set it to 0
                    }
               }
          }
          //Horizontal Drag
          if (!keyInputHandler.right && !keyInputHandler.left && player.getXVelocity() != 0) {
               if (Math.abs(player.getXVelocity()) <= Player.DRAG) {
                    player.setXVelocity(0);
               } else {
                    if (player.getXVelocity() > 0) {
                         player.setXVelocity(player.getXVelocity() - Player.DRAG);//just set it to 0
                    } else {
                         player.setXVelocity(player.getXVelocity() + Player.DRAG);//just set it to 0
                    }
               }
          }
          
          //Shooting
          if (keyInputHandler.shoot && player.canShoot()) {
               //test projectiles
               playerProjectiles.add(new Shot(player.getX(), player.getY(), 0, Shot.ShotVelocity, "", new HitBox(0,0,10,10), 30);
          }
          player.move();
          
          repaint();
     }
     
     public void paintComponent(Graphics g) {
          //Paints all gui
          g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
          BufferedImage img = null;
          try {
               img = ImageIO.read(new File(FileStore.PLAYER_TEST));
          } catch (IOException e) {
               e.printStackTrace();    
          }
          g.drawImage(img, player.getX(), player.getY(), null);
     }
     
     @Override
     public void keyPressed (KeyEvent e) {
          //Called when a key is pressed, sends info to keyInputHandler
          keyInputHandler.sendKeyPressed(e.getKeyCode());
     }
     
     @Override
     public void keyReleased (KeyEvent e) { 
          //Called when a key is released, sends info to keyInputHandler
          keyInputHandler.sendKeyReleased(e.getKeyCode());
     }
     
     @Override
     public void keyTyped (KeyEvent e) {
          //Currently unused
     }
     
     @Override
     public void mouseEntered (MouseEvent e) { 
          //Currently unused
     }
     
     @Override
     public void mouseExited (MouseEvent e) {
          //Currently unused
     }
     
     @Override
     public void mousePressed (MouseEvent e) {
          //Currently unuused
     }
     
     @Override 
     public void mouseReleased (MouseEvent e) {
          //Currently unused
     }
     
     @Override
     public void mouseClicked (MouseEvent e) {
          //Currently unused
     }
     
}
