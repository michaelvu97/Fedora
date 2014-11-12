/* 
 * PROJECT:LodeStar
 * Source can be found at www.github.com/michaelvu97/LodeStar
 * Authors: Qazi Qazi (Project Manager), Roy Liu, Michael Vu
 * Date: 9/17/14
 * 
 * This class reads keybindings from a data file, and compares KeyEvents to them to change input booleans.
 */

package com.MRS.NeckbeardEngine;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class KeyInputHandler {
     
     public boolean up = false, down = false, left = false, right = false, shoot = false, switchState = false, bomb = false;
     
     public final String path = "Data\\KeyBindings.txt";
     
     /*
      * Default Keybindings
      */
     private int keyUp = KeyEvent.VK_UP, keyDown = KeyEvent.VK_DOWN, keyLeft = KeyEvent.VK_LEFT, keyRight = KeyEvent.VK_RIGHT, keyShoot = KeyEvent.VK_Z, keySwitchState = KeyEvent.VK_SPACE,keyBomb = KeyEvent.VK_X; 
    /* 
     public KeyInputHandler() {
          //Read from file
          try {
               String[] keyBindings = openFile();
               keyUp = Integer.parseInt(keyBindings[0]);
               keyDown = Integer.parseInt(keyBindings[1]);
               keyLeft = Integer.parseInt(keyBindings[2]);  //DO WE NEED THIS??????
               keyRight = Integer.parseInt(keyBindings[3]);
               keyShoot = Integer.parseInt(keyBindings[4]);
               keySwitchState = Integer.parseInt(keyBindings[5]);
               keyBomb = Integer.parseInt(keyBindings[6]);
          } catch (Exception e) {
               e.printStackTrace();
          }
     }
    */ 
     public void sendKeyPressed (int keyPressed) {
          //KeyEvent comparasin
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
     }
     
     public void sendKeyReleased (int keyReleased) {
          //KeyEvent comparasin
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
     }
     
     public String[] openFile() throws IOException {
          //Setup readers for file
          FileReader f = new FileReader(path);
          BufferedReader textReader = new BufferedReader(f);
          
          int lines = 7; //change according to file;
          String[] textData = new String[lines];
          
          //set array data line by line
          for (int i = 0; i < lines; i++) {
               textData[i] = textReader.readLine();
          }
          
          //close reader and return data
          textReader.close();
          return textData;
     }
}
