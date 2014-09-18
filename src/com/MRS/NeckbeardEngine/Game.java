package com.MRS.NeckbeardEngine;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics;

public class Game extends JPanel implements KeyListener, MouseListener {
     
     public boolean started;
     
     KeyInputHandler keyInputHandler; //contains booleans for all keys
     
     public Game () {
          
          keyInputHandler = new KeyInputHandler ();
          initialize();
          
     }
     
     public void initialize () {
          started = false;
          
          addKeyListener(this);
          
     }
     
     public void step () {
          System.out.println(keyInputHandler.up);
          repaint();
     }
     
     public void paintComponent(Graphics g) {
          g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
     }
     
     @Override
     public void keyPressed (KeyEvent e) {
          keyInputHandler.sendKeyPressed(e.getKeyCode());
     }
     
     @Override
     public void keyReleased (KeyEvent e) { 
          keyInputHandler.sendKeyReleased(e.getKeyCode());
     }
     
     @Override
     public void keyTyped (KeyEvent e) {
          //Unused
     }
     
     @Override
     public void mouseEntered (MouseEvent e) { 
          //Unused
     }
     
     @Override
     public void mouseExited (MouseEvent e) {
          //Unused
     }
     
     @Override
     public void mousePressed (MouseEvent e) {
          
     }
     
     @Override 
     public void mouseReleased (MouseEvent e) {
          
     }
     
     @Override
     public void mouseClicked (MouseEvent e) {
          
     }
     
}