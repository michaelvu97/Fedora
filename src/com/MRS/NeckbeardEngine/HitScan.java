package com.MRS.NeckbeardEngine;
public class HitScan extends HitBox {
     private State state;
     private double duration; //duration in millis
     private long killTime;
     
     public HitScan (int x, int y, int height, int width) {
          super (x, y, height, width);
          killTime = System.currentTimeMillis() + (long)duration;
     }
     public int getState() {
          return state.getState();
     }
     public double getDuration() {
          return duration;
     }
     public long getkillTime() {
          return killTime;
     }
}
