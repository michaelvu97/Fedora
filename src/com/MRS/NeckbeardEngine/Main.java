/* 
 * PROJECT:LodeStar
 * Source can be found at www.github.com/michaelvu97/LodeStar
 * Authors: Qazi Qazi (Project Manager), Roy Liu, Michael Vu
 * Inception Date: 9/17/14
 * 
 * Dr. Java prefs/misc/indent level = 2
 * 
 * This project will eventually be a shoot 'em up game with the ability to switch states to gain advantages and avoid
 * certain obstacles. There will be different types of enemies, and boss fights. I don't really know what else to put
 * here, so yeah.
 * 
 * This is the main running class. It holds an instance of Game in a JFrame, and uses Timers to update Game at a set rate.
 */

package com.MRS.NeckbeardEngine;

import java.util.Timer;
import java.util.TimerTask;

import java.awt.Toolkit;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main extends JFrame {
  
  public static final int FRAME_RATE = 16, //60 fps  
    HEIGHT = 900, //16:9 720 ratio
    WIDTH = 720;
  
  public static void main (String[] args) {
    //Main running method
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        
        //Get insets (thanks to stackoverflow user ring_bearer)
//                    JFrame temp = new JFrame("");
//                    temp.setSize(0,0);
//                    temp.pack();
//                    temp.setResizable(false);
//                    Insets insets = temp.getInsets();
//                    temp = null;
        
        //user screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth         = screenSize.getWidth();
        double screenHeight        = screenSize.getHeight();
        
        //determining frame location (centered)
        int FRAME_X             = (int) Math.floor( (screenWidth / 2) - (WIDTH/2) );
        int FRAME_Y             = (int) Math.floor( (screenHeight / 2) - (HEIGHT/2) );
        
        //JFrame extension set up
        Main main = new Main();
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setFocusable(false);
        //main.setSize(new Dimension(insets.left + insets.right + WIDTH, insets.top + insets.bottom + HEIGHT));
        //System.err.println(main.getSize());
        main.setSize(WIDTH,HEIGHT);
        // main.setBounds(0, 0, insets.left + insets.right + WIDTH, insets.top + insets.bottom + HEIGHT);   
        main.setResizable(false);
        main.setUndecorated(true);
        
        //Game set up
        Game game = new Game(main);
        game.setSize(WIDTH, HEIGHT);
        
        main.add(game);
        main.setLocationRelativeTo(null);
        main.setLocation(FRAME_X, FRAME_Y);
        
        main.setVisible(true);
        game.requestFocus();
        
        //Timer setup
        TimerTask timerTask = new GameTimer(game); //GameTimer has game.step() in run()
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, FRAME_RATE);
      }
    });
  }
}
