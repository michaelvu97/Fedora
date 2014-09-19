/* 
 * PROJECT:Fedora
 * Source can be found at www.github.com/michaelvu97/Fedora
 * Authors: Qazi Qazi (Project Manager), Roy Liu, Michael Vu
 * Date: 9/17/14
 * 
 * Dr. Java prefs/misc/indent level = 5
 * 
 * This project will eventually be a shoot 'em up game with the ability to switch states to gain advantages and avoid
 * certain obstacles. There will be different types of enemies, and boss fights. I don't really know what else to put
 * here, so yeah.
 * 
 * This is the main running class. It holds an instance of Game in a JFrame, and uses Timers to update Game at a set rate.
 */

package com.MRS.NeckbeardEngine;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends JFrame {
 
     public static final int FRAME_RATE = 16, //60 fps  
                             HEIGHT = 1280, //16:9 720 ratio
                             WIDTH = 720;
     
     public static void main (String[] args) {
          //Main running method
          SwingUtilities.invokeLater(new Runnable() {
               public void run() {
                    //JFrame extension set up
                    Main main = new Main();
                    main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    main.setFocusable(false);
                    main.setSize(WIDTH, HEIGHT);
                    main.setUndecorated(true);
                    main.setVisible(true);
                    
                    //Game set up
                    Game game = new Game();
                    game.setSize(WIDTH, HEIGHT);
                    main.add(game);
                    game.requestFocus();
                    
                    //Timer setup
                    TimerTask timerTask = new GameTimer(game); //GameTimer has game.step() in run()
                    Timer timer = new Timer(true);
                    timer.scheduleAtFixedRate(timerTask, 0, FRAME_RATE);
               }
          });
     }
     
     public Main () {
          //Don't really need this
     }
  
}