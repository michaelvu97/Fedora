/*
 * This class is just for the static image paths, not for instantiation
 */
package com.MRS.NeckbeardEngine;

public class FileStore {  
  public final static String     assets = "\\Assets\\",
                                 fx     = "FX\\",
                                 sound  = "Sound\\",
                            PLAYER_RED  = assets + "PlayerShipRed.png",
                            PLAYER_BLUE = assets + "PlayerShipBlue.png",
                            MOOK_RED    = assets + "MookRed.png",
                            MOOK_BLUE   = assets + "MookBlue.png",
                            SHOT_RED    = assets + "ShotRed.png",
                            SHOT_BLUE   = assets + "ShotBlue.png",
                            FX_BLUE_GLOW= assets + fx + "BlueGlow.png",
                            FX_RED_GLOW = assets + fx + "RedGlow.png",
                            BG_MUSIC_1  = assets + sound + "TestBGM1.wav",
                            LASER_SHOT_1= assets + sound + "LaserShot.wav";
}
