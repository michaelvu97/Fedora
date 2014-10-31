package com.MRS.NeckbeardEngine;
abstract class HitScan extends HitBox {
     private State state;
     private double duration;
     private long killTime;
     public int getState();
     public double getDuration();
     public long getkillTime();
}
