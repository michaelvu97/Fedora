package com.MRS.NeckbeardEngine;
abstract class HitScan extends HitBox {
     private State state;
     private double duration;
     private long killTime;
     
     public HitScan(int x,int y,State state,double duration,int height,int width) {
          this.state = state;
          this.duration = duration;
          super(x,y,width,height);
          killTime = duration+System.currentTimeMillis();
     }
     
     public int getState();
     public double getDuration();
     public long getkillTime();
}