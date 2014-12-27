/*
 * This class is just for the static image paths, not for instantiation
 * make sure to prefix any uses of this with System.getProperty("user.dir"); 
 */
package com.MRS.NeckbeardEngine;

public class FileStore {  
  public final static String     assets = "\\Assets\\",
                                 fx     = "FX\\",
                                 sound  = "Sound\\",
                                 lasers = "Lasers\\",
                   
                            //Player assets
                            PLAYER_RED     = assets + "PlayerShip" + "Red"  + ".png",
                            PLAYER_BLUE    = assets + "PlayerShip" + "Blue" + ".png",
                            SHOT_RED       = assets + "Shot"       + "Red"  + ".png",
                            SHOT_BLUE      = assets + "Shot"       + "Blue" + ".png",
                            FAST_SHOT_RED  = assets + "FastShot"   + "Red"  + ".png",
                            FAST_SHOT_BLUE = assets + "FastShot"   + "Blue" + ".png",
                            
                            //Powerup icons
                            POWERUP_FAST         = assets + "FastShot"    + "Icon" + ".png",
                            POWERUP_BOMB         = assets + "Bomb"        + "Icon" + ".png",
                            POWERUP_RAPID_FIRE   = assets + "RapidFire"   + "Icon" + ".png",
                            POWERUP_SCATTER_SHOT = assets + "ScatterShot" + "Icon" + ".png",
                            POWERUP_EXTRA_SHIP   = assets + "ExtraShip"   + "Icon" + ".png",
                            POWERUP_SPEED_BOOST  = assets + "SpeedBoost"  + "Icon" + ".png",
                            POWERUP_SHIELD       = assets + "Shield"      + "Icon" + ".png",
    
                            //Shields
                            RED_SHIELD  = assets + "Red"  + "Shield" + ".png",
                            BLUE_SHIELD = assets + "Blue" + "Shield" + ".png",

                            //Lasers
                            LASER_BLUE_HORIZONTAL_CHARGE = assets + lasers + "Laser" + "Horizontal" + "Blue" + "Charge" + ".png",
                            LASER_BLUE_HORIZONTAL_ACTIVE = assets + lasers + "Laser" + "Horizontal" + "Blue" + "Active" + ".png",
                            LASER_RED_HORIZONTAL_CHARGE  = assets + lasers + "Laser" + "Horizontal" + "Red"  + "Charge" + ".png",
                            LASER_RED_HORIZONTAL_ACTIVE  = assets + lasers + "Laser" + "Horizontal" + "Red"  + "Active" + ".png",
                            LASER_BLUE_VERTICAL_CHARGE   = assets + lasers + "Laser" + "Vertical"   + "Blue" + "Charge" + ".png",
                            LASER_BLUE_VERTICAL_ACTIVE   = assets + lasers + "Laser" + "Vertical"   + "Blue" + "Active" + ".png",
                            LASER_RED_VERTICAL_CHARGE    = assets + lasers + "Laser" + "Vertical"   + "Red"  + "Charge" + ".png",
                            LASER_RED_VERTICAL_ACTIVE    = assets + lasers + "Laser" + "Vertical"   + "Red"  + "Active" + ".png",
                            
                            //Enemy Assets    
                            MOOK_RED         = assets + "Mook"     + "Red"             + ".png",
                            MOOK_BLUE        = assets + "Mook"     + "Blue"            + ".png",
                            STARBURT_RED     = assets + "Starburt" + "Red"             + ".png",
                            STARBURT_BLUE    = assets + "Starburt" + "Blue"            + ".png",
                            IRIS_RED         = assets + "Iris"     + "Red"             + ".png",
                            IRIS_RED_ACTIVE  = assets + "Iris"     + "Red"  + "Active" + ".png",
                            IRIS_BLUE        = assets + "Iris"     + "Blue"            + ".png",
                            IRIS_BLUE_ACTIVE = assets + "Iris"     + "Blue" + "Active" + ".png",
                            SHIFTER_RED      = assets + "Shifter"  + "Red"             + ".png",
                            SHIFTER_BLUE     = assets + "Shifter"  + "Blue"            + ".png",
                            SHADE_RED        = assets + "Shade"    + "Red"             + ".png",
                            SHADE_BLUE       = assets + "Shade"    + "Blue"            + ".png",
    
                            //Enemy shots
                            ENEMY_SHOT_RED                    = assets + "Enemy" + "Shot"     + "Red"    + ".png",
                            ENEMY_SHOT_BLUE                   = assets + "Enemy" + "Shot"     + "Blue"   + ".png",
                            ENEMY_SHOT_PURPLE                 = assets + "Enemy" + "Shot"     + "Purple" + ".png",
                            ENEMY_FAST_SHOT_RED               = assets + "Enemy" + "FastShot" + "Red"    + ".png",
                            ENEMY_FAST_SHOT_BLUE              = assets + "Enemy" + "FastShot" + "Blue"   + ".png",

                            ENEMY_STARBURT_SHOT_RED           = assets + "A" + ".png", //temporary names
                            ENEMY_STARBURT_SHOT_RED_ACTIVE    = assets + "B" + ".png",
                            ENEMY_STARBURT_SHOT_BLUE          = assets + "C" + ".png",
                            ENEMY_STARBURT_SHOT_BLUE_ACTIVE   = assets + "D" + ".png",
    
                            //Effects
                            FX_BLUE_GLOW    = assets + fx + "Blue" + "Glow" + ".png",
                            FX_RED_GLOW     = assets + fx + "Red"  + "Glow" + ".png",
                            FX_VIGNETTE     = assets + fx + "Vignette"      + ".png",

                            //Backgrounds
                            SPACE_BG_1      = assets + "SpaceBG1"           + ".png",
                            TEST_FOREGROUND = assets + "testForeGround"     + ".png",
                            TEST_MIDGROUND  = assets + "testmidground"      + ".png",
    
                            //Audio
                            BG_MUSIC_1   = assets + sound + "TestBGM1.wav",
                            MONTAGE      = assets + sound + "Montage.wav",
                            LASER_SHOT_1 = assets + sound + "LaserShot.wav",
                            BOMB         = assets + sound + "Bomb.wav",
                            METAL_HIT_1  = assets + sound + "MetalHit1.wav",
                            METAL_HIT_2  = assets + sound + "MetalHit2.wav",
                            METAL_HIT_3  = assets + sound + "MetalHit3.wav",
                            EXPLOSION_1  = assets + sound + "Explosion1.wav";
  
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
  
  public final static String[] HITEXPLOSIONONEFLIPPED = {
    "\\Assets\\HitExplosionOneFlipped\\GrenadeExplosion0.png",
    "\\Assets\\HitExplosionOneFlipped\\GrenadeExplosion1.png",
    "\\Assets\\HitExplosionOneFlipped\\GrenadeExplosion2.png",
    "\\Assets\\HitExplosionOneFlipped\\GrenadeExplosion3.png",
    "\\Assets\\HitExplosionOneFlipped\\GrenadeExplosion4.png",
    "\\Assets\\HitExplosionOneFlipped\\GrenadeExplosion5.png",
    "\\Assets\\HitExplosionOneFlipped\\GrenadeExplosion6.png",
    "\\Assets\\HitExplosionOneFlipped\\GrenadeExplosion7.png",
    "\\Assets\\HitExplosionOneFlipped\\GrenadeExplosion8.png",
    "\\Assets\\HitExplosionOneFlipped\\GrenadeExplosion9.png",
    "\\Assets\\HitExplosionOneFlipped\\GrenadeExplosion10.png",
    "\\Assets\\HitExplosionOneFlipped\\GrenadeExplosion11.png",
    "\\Assets\\HitExplosionOneFlipped\\GrenadeExplosion12.png",
    "\\Assets\\HitExplosionOneFlipped\\GrenadeExplosion13.png",
    "\\Assets\\HitExplosionOneFlipped\\GrenadeExplosion14.png",
    "\\Assets\\HitExplosionOneFlipped\\GrenadeExplosion15.png"};
  
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
