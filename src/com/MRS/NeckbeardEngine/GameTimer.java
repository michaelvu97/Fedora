package com.MRS.NeckbeardEngine;

import java.util.TimerTask;

public class GameTimer extends TimerTask {
     
     Game game;
     
     public GameTimer (Game g1) {
          game = g1;
     }
     
     @Override
     public void run() {
          game.step();
     }
     
}