/*
 * This class is just for the static image paths, not for instantiation
 * make sure to prefix any uses of this with System.getProperty("user.dir"); 
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
                            FX_VIGNETTE = assets + fx + "Vignette.png",
                            SPACE_BG_1  = assets + "SpaceBG1.png",
                            BG_MUSIC_1  = assets + sound + "TestBGM1.wav",
                            LASER_SHOT_1= assets + sound + "LaserShot.wav";
  
  public final static String[] HITEXPLOSIONONE = {"\\Assets\\HitExplosionOne\\GrenadeExplosion0.png",
    "\\Assets\\HitExplosionOne\\GrenadeExplosion1.png",
    "\\Assets\\HitExplosionOne\\GrenadeExplosion2.png",
    "\\Assets\\HitExplosionOne\\GrenadeExplosion3.png",
    "\\Assets\\HitExplosionOne\\GrenadeExplosion4.png",
    "\\Assets\\HitExplosionOne\\GrenadeExplosion5.png",
    "\\Assets\\HitExplosionOne\\GrenadeExplosion6.png",
    "\\Assets\\HitExplosionOne\\GrenadeExplosion7.png",
    "\\Assets\\HitExplosionOne\\GrenadeExplosion8.png",
    "\\Assets\\HitExplosionOne\\GrenadeExplosion9.png",
    "\\Assets\\HitExplosionOne\\GrenadeExplosion10.png",
    "\\Assets\\HitExplosionOne\\GrenadeExplosion11.png",
    "\\Assets\\HitExplosionOne\\GrenadeExplosion12.png",
    "\\Assets\\HitExplosionOne\\GrenadeExplosion13.png",
    "\\Assets\\HitExplosionOne\\GrenadeExplosion14.png",
    "\\Assets\\HitExplosionOne\\GrenadeExplosion15.png"};
}
