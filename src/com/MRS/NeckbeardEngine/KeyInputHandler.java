package com.MRS.NeckbeardEngine;

import java.awt.event.*;

public class KeyInputHandler {
     
     public boolean up = false, down = false, left = false, right = false, shoot = false, switchState = false;
     
     private final int keyUp = KeyEvent.VK_W, keyDown = KeyEvent.VK_S, keyLeft = KeyEvent.VK_D, keyRight = KeyEvent.VK_A, keyShoot = KeyEvent.VK_J, keySwitchState = KeyEvent.VK_K;
     
     public KeyInputHandler() {
          //Make this create keys from a config
     }
     
     public void sendKeyPressed (int keyPressed) {
          switch (keyPressed) {
               case keyUp:
                    up = true;
                    break;
               case keyDown:
                    down = true;
                    break;
               case keyRight:
                    right = true;
                    break;
               case keyLeft:
                    left = true;
                    break;
               case keyShoot:
                    shoot = true;
                    break;
               case keySwitchState:
                    switchState = true;
                    break;
          }
     }
     
     public void sendKeyReleased (int keyReleased) {
          switch (keyReleased) {
               case keyUp:
                    up = true;
                    break;
               case keyDown:
                    down = true;
                    break;
               case keyRight:
                    right = true;
                    break;
               case keyLeft:
                    left = true;
                    break;
               case keyShoot:
                    shoot = true;
                    break;
               case keySwitchState:
                    switchState = true;
                    break;
                    
          }
     }
}


