/* 
 * PROJECT:LodeStar
 * Source can be found at www.github.com/michaelvu97/LodeStar
 * Authors: Qazi Qazi (Project Manager), Roy Liu, Michael Vu
 * Date: 9/17/14
 * 
 * This class reads keybindings from a data file, 
 * and compares KeyEvents to them to change input booleans.
 */

package com.MRS.NeckbeardEngine;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.net.*;
import java.io.*;

public class KeyInputHandler {
     
     public boolean up = false, down = false, left = false, right = false, shoot = false, switchState = false, bomb = false, escape = false;
     
     public final String path = "\\Data\\KeyBindings.txt";
     
     /*
      * Default Keybindings
      */
     private int keyUp = KeyEvent.VK_UP, keyDown = KeyEvent.VK_DOWN, keyLeft = KeyEvent.VK_LEFT, keyRight = KeyEvent.VK_RIGHT, keyShoot = KeyEvent.VK_Z, keySwitchState = KeyEvent.VK_SPACE, keyBomb = KeyEvent.VK_X, keyEscape = KeyEvent.VK_ESCAPE;; 
    
     public KeyInputHandler() {
          String workingDir = System.getProperty("user.dir") + path;
          File f = new File(workingDir);
          ArrayList<KeyValuePair> pairs = DataHandler.parseFile(f);
          
          keyUp = Integer.parseInt(KeyValuePair.getByKey("keyUp", pairs).value);
          keyDown = Integer.parseInt(KeyValuePair.getByKey("keyDown", pairs).value);
          keyLeft = Integer.parseInt(KeyValuePair.getByKey("keyLeft", pairs).value);
          keyRight = Integer.parseInt(KeyValuePair.getByKey("keyRight", pairs).value);
          keyShoot = Integer.parseInt(KeyValuePair.getByKey("keyShoot", pairs).value);
          keySwitchState = Integer.parseInt(KeyValuePair.getByKey("keySwitchState", pairs).value);
          keyBomb = Integer.parseInt(KeyValuePair.getByKey("keyBomb", pairs).value);
     }
     
     public void sendKeyPressed (int keyPressed) {
          //KeyEvent comparison
          if (keyPressed == keyUp) {
               up = true;    
          }
          if (keyPressed == keyDown) {
               down = true;
          }
          if (keyPressed == keyLeft) {
               left = true;
          }
          if (keyPressed == keyRight) {
               right = true;
          }
          if (keyPressed == keyShoot) {
               shoot = true;
          }
          if (keyPressed == keySwitchState) {
               switchState = true;
          }
          if (keyPressed == keyBomb) {
               bomb = true;
          }
          if (keyPressed == keyEscape) {
               escape = true;
          }
     }
     
     public void sendKeyReleased (int keyReleased) {
          //KeyEvent comparison
          if (keyReleased == keyUp) {
               up = false;    
          }
          if (keyReleased == keyDown) {
               down = false;
          }
          if (keyReleased == keyLeft) {
               left = false;
          }
          if (keyReleased == keyRight) {
               right = false;
          }
          if (keyReleased == keyShoot) {
               shoot = false;
          }
          if (keyReleased == keySwitchState) {
               switchState = false;
          }
          if(keyReleased == keyBomb) {
               bomb = false;
          }
          if (keyReleased == keyEscape) {
               escape = false;
          }
     }
}
