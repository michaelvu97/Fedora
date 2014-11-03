/* 
 * PROJECT:LodeStar
 * Source can be found at www.github.com/michaelvu97/LodeStar
 * Authors: Qazi Qazi (Project Manager), Roy Liu, Michael Vu
 * Date: 9/17/14
 *
 * This is the panel where all calculations and input/output is performed
 */

package com.MRS.NeckbeardEngine;

import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.*;
import java.util.*;
import javax.imageio.*;
import java.io.*;

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
     }
     
     public void step () {
          //Called by main every time interval
          System.out.println(keyInputHandler.up);
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
          g.drawImage(img, 0, 0, null);
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
