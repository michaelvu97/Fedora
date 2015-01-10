/* 
 * PROJECT:LodeStar
 * Source can be found at www.github.com/michaelvu97/LodeStar
 * Authors: Qazi Qazi (Project Manager), Roy Liu, Michael Vu
 * Date: 9/17/14
 *
 *This class is just for the static image paths, not for instantiation
 * make sure to prefix any uses of this with System.getProperty("user.dir"); 
 */
package com.MRS.NeckbeardEngine;

public class FileStore {  
  public final static String     assets = "\\Assets\\",
                                 fx     = "FX\\",
                                 sound  = "Sound\\",
                                 lasers = "Lasers\\",
    
                            //ClassNames
                            ELITE = "Elite",
                            STARBURT = "Starburt",
                            MOOK = "Mook",
                            IRIS = "Iris",
                            SHADE = "Shade",
    
                   
                            //Player assets
                            PLAYER_RED        = assets + "PlayerShip"           + "Red"  + ".png",
                            PLAYER_BLUE       = assets + "PlayerShip"           + "Blue" + ".png",
                            SHOT_RED          = assets + "Shot"                 + "Red"  + ".png",
                            SHOT_BLUE         = assets + "Shot"                 + "Blue" + ".png",
                            FAST_SHOT_RED     = assets + "FastShot"             + "Red"  + ".png",
                            FAST_SHOT_BLUE    = assets + "FastShot"             + "Blue" + ".png",
                            BOMB_IMG          = assets + "Bomb"                          + ".png",
                            BOMB_COUNTER      = assets + "BombCounter"                   + ".png",
                            SHOT_INDI_BLUE    = assets + "BasicShotIndicator"   + "Blue" + ".png",
                            SHOT_INDI_RED     = assets + "BasicShotIndicator"   + "Red"  + ".png",
                            FSHOT_INDI_BLUE   = assets + "FShotIndicator"       + "Blue" + ".png",
                            FSHOT_INDI_RED    = assets + "FShotIndicator"       + "Red"  + ".png",
                            RFIRE_INDI_BLUE   = assets + "RapidFireIndicator"   + "Blue" + ".png",
                            RFIRE_INDI_RED    = assets + "RapidFireIndicator"   + "Red"  + ".png",
                            SCATTER_INDI_BLUE = assets + "ScatterShotIndicator" + "Blue" + ".png",
                            SCATTER_INDI_RED  = assets + "ScatterShotIndicator" + "Red"  + ".png",
                            
                            //Powerup icons
                            POWERUP_FAST         = assets + "FastShot"    + "Icon" + ".png",
                            POWERUP_BOMB         = assets + "Bomb"        + "Icon" + ".png",
                            POWERUP_RAPID_FIRE   = assets + "RapidFire"   + "Icon" + ".png",
                            POWERUP_SCATTER_SHOT = assets + "ScatterShot" + "Icon" + ".png",
                            POWERUP_EXTRA_SHIP   = assets + "ExtraShip"   + "Icon" + ".png",
                            POWERUP_SPEED_BOOST  = assets + "SpeedBoost"  + "Icon" + ".png",
                            POWERUP_SHIELD       = assets + "Shield"      + "Icon" + ".png",
    
                            //Shade Shot Icons
                            SHADE_FAST_SHOT    = assets + "FastShot"    + "Shade" + ".png",
                            SHADE_BOMB         = assets + "Bomb"        + "Shade" + ".png",
                            SHADE_RAPID_FIRE   = assets + "RapidFire"   + "Shade" + ".png",
                            SHADE_SCATTER_SHOT = assets + "ScatterShot" + "Shade" + ".png",
    
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
                            MOOK_RED         = assets + MOOK       + "Red"             + ".png",
                            MOOK_BLUE        = assets + MOOK       + "Blue"            + ".png",
                            MOOK_BOTH        = assets + MOOK       + "Both"            + ".png",
                            STARBURT_RED     = assets + "Starburt" + "Red"             + ".png",
                            STARBURT_BLUE    = assets + "Starburt" + "Blue"            + ".png",
                            IRIS_RED         = assets + IRIS       + "Red"             + ".png",
                            IRIS_RED_ACTIVE  = assets + IRIS       + "Red"  + "Active" + ".png",
                            IRIS_BLUE        = assets + IRIS       + "Blue"            + ".png",
                            IRIS_BLUE_ACTIVE = assets + IRIS       + "Blue" + "Active" + ".png",
                            SHIFTER_RED      = assets + "Shifter"  + "Red"             + ".png",
                            SHIFTER_BLUE     = assets + "Shifter"  + "Blue"            + ".png",
                            SHADE_RED        = assets + "Shade"    + "Red"             + ".png",
                            SHADE_BLUE       = assets + "Shade"    + "Blue"            + ".png",
                            ELITE_RED        = assets + ELITE      + "Red"             + ".png",
                            ELITE_BLUE       = assets + ELITE      + "Blue"            + ".png",
                            ELITE_BOTH       = assets + ELITE      + "Both"            + ".png",
    
                            //Enemy shots
                            ENEMY_SHOT_RED                    = assets + "Enemy" + "Shot"     + "Red"    + ".png",
                            ENEMY_SHOT_BLUE                   = assets + "Enemy" + "Shot"     + "Blue"   + ".png",
                            ENEMY_SHOT_PURPLE                 = assets + "Enemy" + "Shot"     + "Purple" + ".png",
                            ENEMY_FAST_SHOT_RED               = assets + "Enemy" + "FastShot" + "Red"    + ".png",
                            ENEMY_FAST_SHOT_BLUE              = assets + "Enemy" + "FastShot" + "Blue"   + ".png",

                            ENEMY_STARBURT_SHOT_BLUE           = assets + "B" + ".png", //temporary names
                            ENEMY_STARBURT_SHOT_BLUE_ACTIVE    = assets + "A" + ".png",
                            ENEMY_STARBURT_SHOT_RED          = assets + "D" + ".png",
                            ENEMY_STARBURT_SHOT_RED_ACTIVE   = assets + "C" + ".png",
    
                            //Effects
                            FX_BLUE_GLOW    = assets + fx + "Blue" + "Glow" + ".png",
                            FX_RED_GLOW     = assets + fx + "Red"  + "Glow" + ".png",
                            FX_VIGNETTE     = assets + fx + "Vignette"      + ".png",

                            //Backgrounds
                            PARTICLE_LAYER_1 = assets + "ParticleLayer1" + ".png",
                            PARTICLE_LAYER_2 = assets + "ParticleLayer2" + ".png",
                            BASE_BG          = assets + "BaseBG"         + ".png",
                            TEST_FOREGROUND  = assets + "testForeGround" + ".png",
                            TEST_MIDGROUND   = assets + "testmidground"  + ".png",
    
                            //Audio
                            BG_MUSIC_1         = assets + sound + "TestBGM1.wav",
                            MONTAGE            = assets + sound + "Montage.wav",
                            LASER_SHOT_1       = assets + sound + "LaserShot.wav",
                            LASERBEAM          = assets + sound + "LaserStandard.wav",
                            LASERSHADE         = assets + sound + "LaserShade.wav",
                            STARBURT_SHOT      = assets + sound + "StarburtShot.wav",
                            BG_MUSIC_SHADE     = assets + sound + "ShadeFightMusic.wav",
                            BOMB               = assets + sound + "Bomb.wav",
                            SWITCH_STATE       = assets + sound + "SwitchState.wav",
                            ENEMY_SWITCH_STATE = assets + sound + "EnemySwitchState.wav",
                            SHADE_SWITCH       = assets + sound + "ShadeSwitchWeapon.wav",
                            MENU_FX            = assets + sound + "MenuFX.wav",
                            METAL_HIT_1        = assets + sound + "MetalHit1.wav",
                            METAL_HIT_2        = assets + sound + "MetalHit2.wav",
                            METAL_HIT_3        = assets + sound + "MetalHit3.wav",
                            EXPLOSION_1        = assets + sound + "Explosion1.wav",
                            EXPLOSION_2        = assets + sound + "Explosion2.wav",
                            POWERUP_GAINED     = assets + sound + "PowerUp.wav",
                            MONTAGE_BUILD      = assets + sound + "MontageBuild.wav",
                            MONTAGE_DROP       = assets + sound + "MontageDrop.wav";
  
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
  
  public final static String[] DEATHEXPLOSIONLARGE = {
    "\\Assets\\DeathExplosionLarge\\DeathExplosion (" + 1 + ").png",
    "\\Assets\\DeathExplosionLarge\\DeathExplosion (" + 2 + ").png",
    "\\Assets\\DeathExplosionLarge\\DeathExplosion (" + 3 + ").png",
    "\\Assets\\DeathExplosionLarge\\DeathExplosion (" + 4 + ").png",
    "\\Assets\\DeathExplosionLarge\\DeathExplosion (" + 5 + ").png",
    "\\Assets\\DeathExplosionLarge\\DeathExplosion (" + 6 + ").png",
    "\\Assets\\DeathExplosionLarge\\DeathExplosion (" + 7 + ").png",
    "\\Assets\\DeathExplosionLarge\\DeathExplosion (" + 8 + ").png",
    "\\Assets\\DeathExplosionLarge\\DeathExplosion (" + 9 + ").png",
    "\\Assets\\DeathExplosionLarge\\DeathExplosion (" + 10 + ").png",
    "\\Assets\\DeathExplosionLarge\\DeathExplosion (" + 11 + ").png",
    "\\Assets\\DeathExplosionLarge\\DeathExplosion (" + 12 + ").png",
    "\\Assets\\DeathExplosionLarge\\DeathExplosion (" + 13 + ").png",
    "\\Assets\\DeathExplosionLarge\\DeathExplosion (" + 14 + ").png",
    "\\Assets\\DeathExplosionLarge\\DeathExplosion (" + 15 + ").png",
    "\\Assets\\DeathExplosionLarge\\DeathExplosion (" + 16 + ").png",
    "\\Assets\\DeathExplosionLarge\\DeathExplosion (" + 17 + ").png",
    "\\Assets\\DeathExplosionLarge\\DeathExplosion (" + 18 + ").png",
    "\\Assets\\DeathExplosionLarge\\DeathExplosion (" + 19 + ").png",
    "\\Assets\\DeathExplosionLarge\\DeathExplosion (" + 20 + ").png",
    "\\Assets\\DeathExplosionLarge\\DeathExplosion (" + 21 + ").png",
    "\\Assets\\DeathExplosionLarge\\DeathExplosion (" + 22 + ").png",
    "\\Assets\\DeathExplosionLarge\\DeathExplosion (" + 23 + ").png",
    "\\Assets\\DeathExplosionLarge\\DeathExplosion (" + 24 + ").png",
    "\\Assets\\DeathExplosionLarge\\DeathExplosion (" + 25 + ").png",
    "\\Assets\\DeathExplosionLarge\\DeathExplosion (" + 26 + ").png",
    "\\Assets\\DeathExplosionLarge\\DeathExplosion (" + 27 + ").png",
    "\\Assets\\DeathExplosionLarge\\DeathExplosion (" + 28 + ").png",
    "\\Assets\\DeathExplosionLarge\\DeathExplosion (" + 29 + ").png"};
  
  public final static String[] GAMEOVEREXPLOSION = {
    "\\Assets\\GameOverExplosion\\GameOverExplosion (" + 1 + ").png",
    "\\Assets\\GameOverExplosion\\GameOverExplosion (" + 2 + ").png",
    "\\Assets\\GameOverExplosion\\GameOverExplosion (" + 3 + ").png",
    "\\Assets\\GameOverExplosion\\GameOverExplosion (" + 4 + ").png",
    "\\Assets\\GameOverExplosion\\GameOverExplosion (" + 5 + ").png",
    "\\Assets\\GameOverExplosion\\GameOverExplosion (" + 6 + ").png",
    "\\Assets\\GameOverExplosion\\GameOverExplosion (" + 7 + ").png",
    "\\Assets\\GameOverExplosion\\GameOverExplosion (" + 8 + ").png",
    "\\Assets\\GameOverExplosion\\GameOverExplosion (" + 9 + ").png",
    "\\Assets\\GameOverExplosion\\GameOverExplosion (" + 10 + ").png",
    "\\Assets\\GameOverExplosion\\GameOverExplosion (" + 11 + ").png",
    "\\Assets\\GameOverExplosion\\GameOverExplosion (" + 12 + ").png",
    "\\Assets\\GameOverExplosion\\GameOverExplosion (" + 13 + ").png",
    "\\Assets\\GameOverExplosion\\GameOverExplosion (" + 14 + ").png",
    "\\Assets\\GameOverExplosion\\GameOverExplosion (" + 15 + ").png",
    "\\Assets\\GameOverExplosion\\GameOverExplosion (" + 16 + ").png",
    "\\Assets\\GameOverExplosion\\GameOverExplosion (" + 17 + ").png",
    "\\Assets\\GameOverExplosion\\GameOverExplosion (" + 18 + ").png",
    "\\Assets\\GameOverExplosion\\GameOverExplosion (" + 19 + ").png",
    "\\Assets\\GameOverExplosion\\GameOverExplosion (" + 20 + ").png",
    "\\Assets\\GameOverExplosion\\GameOverExplosion (" + 21 + ").png",
    "\\Assets\\GameOverExplosion\\GameOverExplosion (" + 22 + ").png",
    "\\Assets\\GameOverExplosion\\GameOverExplosion (" + 23 + ").png",
    "\\Assets\\GameOverExplosion\\GameOverExplosion (" + 24 + ").png",
    "\\Assets\\GameOverExplosion\\GameOverExplosion (" + 25 + ").png",
    "\\Assets\\GameOverExplosion\\GameOverExplosion (" + 26 + ").png",
    "\\Assets\\GameOverExplosion\\GameOverExplosion (" + 27 + ").png",
    "\\Assets\\GameOverExplosion\\GameOverExplosion (" + 28 + ").png",
    "\\Assets\\GameOverExplosion\\GameOverExplosion (" + 29 + ").png",
    "\\Assets\\GameOverExplosion\\GameOverExplosion (" + 30 + ").png",
    "\\Assets\\GameOverExplosion\\GameOverExplosion (" + 31 + ").png"
  };
  
  public static String GameOverSequence (int frame) {
    /*
     * Because 121 is too many to do by hand
     */
    String base = "\\Assets\\GameOver\\GameOver_";
    if (frame < 10) {
      return base + "0000" + frame + ".png";
    } else if (frame < 100) {
      return base + "000" + frame + ".png";
    } else {
      return base + "00" + frame + ".png";
    }
  }
 public static String MenuSequence (int frame) {
    String whiteSpace = "";
    if (frame < 10) 
      whiteSpace = "0000";
    else if (frame < 100) 
      whiteSpace = "000";
    else
      whiteSpace = "00";
    return "\\Assets\\MenuBG\\MenuBG_1_" + whiteSpace + frame + ".jpg";
  }
  
}
