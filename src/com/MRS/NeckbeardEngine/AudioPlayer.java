package com.MRS.NeckbeardEngine;

public class AudioPlayer {
  public Sound BGM1,
               LaserShot1;
  public AudioPlayer () {
    BGM1 = new Sound(FileStore.BG_MUSIC_1);
   // BGM1.stop();
    LaserShot1 = new Sound(FileStore.LASER_SHOT_1);
    LaserShot1.stop();
  }
}