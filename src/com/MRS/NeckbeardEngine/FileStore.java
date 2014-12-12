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
                            FAST_SHOT_RED = assets + "fastshotred.png",
                            FAST_SHOT_BLUE = assets + "fastshotblue.png",
                            POWERUP_FAST = assets + "FastShotPowerUpIcon.png",
                            POWERUP_BOMB = assets + "BombIcon.png",
                            POWERUP_RAPID= assets + "RapidFireIcon.png",
                            POWERUP_EXTRA_SHIP = assets + "ExtraShipIcon.png",
                            POWERUP_SPEED_BOOST = assets + "SpeedBoostIcon.png",
                            //Effects
                            FX_BLUE_GLOW= assets + fx + "BlueGlow.png",
                            FX_RED_GLOW = assets + fx + "RedGlow.png",
                            FX_VIGNETTE = assets + fx + "Vignette.png",
                            SPACE_BG_1  = assets + "SpaceBG1.png",
                            //Audio
                            BG_MUSIC_1  = assets + sound + "TestBGM1.wav",
                            MONTAGE     = assets + sound + "Montage.wav",
                            LASER_SHOT_1= assets + sound + "LaserShot.wav",
                            BOMB        = assets + sound + "Bomb.wav",
                            EXPLOSION_1 =assets + sound + "Explosion1.wav";
  
  public final static String[] HITEXPLOSIONONE = {
    "\\Assets\\HitExplosionOne\\GrenadeExplosion0.png",
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
  
  public final static String[] DEATHEXPLOSIONMEDIUM = {
    "\\Assets\\DeathExplosionMedium\\DeathExplosion0.png",
    "\\Assets\\DeathExplosionMedium\\DeathExplosion1.png",
    "\\Assets\\DeathExplosionMedium\\DeathExplosion2.png",
    "\\Assets\\DeathExplosionMedium\\DeathExplosion3.png",
    "\\Assets\\DeathExplosionMedium\\DeathExplosion4.png",
    "\\Assets\\DeathExplosionMedium\\DeathExplosion5.png",
    "\\Assets\\DeathExplosionMedium\\DeathExplosion6.png",
    "\\Assets\\DeathExplosionMedium\\DeathExplosion7.png",
    "\\Assets\\DeathExplosionMedium\\DeathExplosion8.png",
    "\\Assets\\DeathExplosionMedium\\DeathExplosion9.png",
    "\\Assets\\DeathExplosionMedium\\DeathExplosion10.png",
    "\\Assets\\DeathExplosionMedium\\DeathExplosion11.png",
    "\\Assets\\DeathExplosionMedium\\DeathExplosion12.png",
    "\\Assets\\DeathExplosionMedium\\DeathExplosion13.png",
    "\\Assets\\DeathExplosionMedium\\DeathExplosion14.png",
    "\\Assets\\DeathExplosionMedium\\DeathExplosion15.png",
    "\\Assets\\DeathExplosionMedium\\DeathExplosion16.png",
    "\\Assets\\DeathExplosionMedium\\DeathExplosion17.png",
    "\\Assets\\DeathExplosionMedium\\DeathExplosion18.png",
    "\\Assets\\DeathExplosionMedium\\DeathExplosion19.png",
    "\\Assets\\DeathExplosionMedium\\DeathExplosion20.png",
    "\\Assets\\DeathExplosionMedium\\DeathExplosion21.png",
    "\\Assets\\DeathExplosionMedium\\DeathExplosion22.png"};
}
