/* 
 * PROJECT:LodeStar
 * Source can be found at www.github.com/michaelvu97/LodeStar
 * Authors: Safwan Qazi (Project Manager), Roy Liu, Michael Vu
 * Date: 9/17/14
 *
 * This is the place where all the encounters and movements of enemies is stored. At the correct positions new enememies
 * are spawned with the correct parameters
 */
package com.MRS.NeckbeardEngine;

import com.MRS.NeckbeardEngine.Enemies.*;
import com.MRS.NeckbeardEngine.Projectiles.*;
import java.util.ArrayList;

public class Level {
  
  //needs game so that it can interact with it
  Game g;
  
  //keeps track of time through frames(essentially every frame it calls the check() method which increases frames by one
  private int frames;
  
  //some enemies need a target so player is offered to them
  private Player target;
  
  // the game is completely different when the player reaches boss so there is a boolean
  private boolean boss = false;
  
  //during the boss fight power ups are dropped at certain healths, to prevent constant dropping while it is at that health
  //there is an array of booleans that represnts if the powerup is dropped or not
  private boolean dropped[] = new boolean[3];
  
  public Level(Game g, Player target) {
    this.g = g;
    frames = 0;
    this.target = target; 
    
    // nor powerUps are dropped yet
    for (int i = 0; i < dropped.length; i++){
      dropped[i] = false;
    }    
  }
  public void check() {    
    //All enemy spawning(except the boss) is choreographed here. Using a switch statement we compare the frames passed
    //and add enemies according to our level design.
    if(!boss) {
      switch(frames) {
        case 180:
          g.enemies.add(new Mook(State.RED, -70, 50, 4, 2.5, "Shot", "leave", Main.WIDTH/2));
          break;
        case 195:
          g.enemies.add(new Mook(State.RED, -70, 50, 4, 2.5, "Shot", "leave", Main.WIDTH/2));
          break;
        case 210:
          g.enemies.add(new Mook(State.RED, -70, 50, 4, 2.5, "Shot", "leave", Main.WIDTH/2));
          break;
        case 300:
          g.enemies.add(new Mook(State.RED, Main.WIDTH + 10, Main.HEIGHT/2, -4, -2.5, "Shot", "leave", Main.WIDTH/2-60));
          break;
        case 315:
          g.enemies.add(new Mook(State.RED, Main.WIDTH + 10, Main.HEIGHT/2, -4, -2.5, "Shot", "leave", Main.WIDTH/2-60));
          break;
        case 330:
          g.enemies.add(new Mook(State.RED, Main.WIDTH + 10, Main.HEIGHT/2, -4, -2.5, "Shot", "leave", Main.WIDTH/2-60));
          break;
        case 420:
          g.enemies.add(new Mook(State.RED, Main.WIDTH + 10, 50, -3, -4, "Shot", "form", Main.WIDTH/2-60));
          g.enemies.add(new Mook(State.BLUE, -70, 50, 3, -4, "Shot", "form", Main.WIDTH/2));
          break;
        case 435:
          g.enemies.add(new Mook(State.RED, Main.WIDTH + 10, 50, -3, -4, "Shot", "form", Main.WIDTH/2-60));
          g.enemies.add(new Mook(State.BLUE, -70, 50, 3, -4, "Shot", "form", Main.WIDTH/2));
          break;
        case 450:
          g.enemies.add(new Mook(State.RED, Main.WIDTH + 10, 50, -3, -4, "Shot", "form", Main.WIDTH/2-60));
          g.enemies.add(new Mook(State.BLUE, -70, 50, 3, -4, "Shot", "form", Main.WIDTH/2));
          break;
        case 465:
          g.enemies.add(new Mook(State.RED, Main.WIDTH + 10, 50, -3, -4, "Shot", "form", Main.WIDTH/2-60));
          g.enemies.add(new Mook(State.BLUE, -70, 50, 3, -4, "Shot", "form", Main.WIDTH/2));
          break;
        case 600:
          g.enemies.add(new Mook(State.RED, -70, 400, 4, -2.5, "Shot", "leave", Main.WIDTH/2));
          g.enemies.add(new Mook(State.RED, -70, 400, 4, 0, "Shot", "stay", 258));
          g.enemies.add(new Mook(State.BLUE, Main.WIDTH + 10, 300, -4, 0, "Shot", "stay", 402-60));
          break;
        case 690:
          g.enemies.add(new Mook(State.BLUE, Main.WIDTH + 10, 150, -3, -4, "Shot", "form", Main.WIDTH/2));
          break;
        case 705:
          g.enemies.add(new Mook(State.BLUE, Main.WIDTH + 10, 150, -3, -4, "Shot", "form", Main.WIDTH/2));
          break;
        case 720:
          g.enemies.add(new Mook(State.BLUE, Main.WIDTH + 10, 150, -3, -4, "Shot", "form", Main.WIDTH/2));
          break;
        case 870:
          g.enemies.add(new Mook(State.RED, -70, 50, 4, 0, "Shot", "stay", 160));
          g.enemies.add(new Mook(State.BLUE, Main.WIDTH + 10, 250, -4, 0, "Shot", "stay", 500));
          g.enemies.add(new Mook(State.BLUE, -70, Main.HEIGHT/2 - 30, 4, 0, "Shot", "patrol", 160));
          g.enemies.add(new Mook(State.BLUE, Main.WIDTH + 10, Main.HEIGHT/2 - 30, -4, 0, "Shot", "patrol", 500));
          break;
        case 1200:
          g.enemies.add(new Mook(State.BLUE, Main.WIDTH + 70, 0, -3, -4, "Shot", "form", Main.WIDTH/2-60));        
          break;
        case 1215:
          g.enemies.add(new Mook(State.BLUE, Main.WIDTH + 70, 0, -3, -4, "Shot", "form", Main.WIDTH/2-60));        
          break;
        case 1425:
          g.enemies.add(new Mook(State.BLUE, -70, 50, 3, -4, "Shot", "form", Main.WIDTH/2));
          break;
        case 1440:
          g.enemies.add(new Mook(State.RED, -70, 50, 3, -4, "Shot", "form", Main.WIDTH/2));
          break;
        case 1455:
          g.enemies.add(new Mook(State.BLUE, -70, 50, 3, -4, "Shot", "form", Main.WIDTH/2));
          break;
        case 1500:
          g.enemies.add(new Mook(State.RED, Main.WIDTH + 10, 50, -4, 0, "Shot", "stay", 480));
          g.enemies.add(new Mook(State.RED, Main.WIDTH + 10, 150, -4, 0, "Shot", "stay", 480));
          break;
        case 1620:
          g.enemies.add(new Mook(State.BLUE, -70, 50, 4, 2.5, "Shot", "leave", 10));
          g.enemies.add(new Mook(State.RED, Main.WIDTH + 10, 500, -4, -2.5, "Shot", "leave", 650));
          break;
        case 1800:
          g.enemies.add(new Mook(State.RED, -70, 500, 4, -2.5, "Shot", "leave", Main.WIDTH/2));
          break;
        case 1815:
          g.enemies.add(new Mook(State.RED, -70, 500, 4, -2.5, "Shot", "leave", Main.WIDTH/2));
          break;
        case 1830:
          g.enemies.add(new Mook(State.RED, -70, 500, 4, -2.5, "Shot", "leave", Main.WIDTH/2));
          break;
        case 2040:
          g.enemies.add(new Elite(State.BLUE, 174, -50, -4, 4, "Shot", target));
          g.enemies.add(new Elite(State.RED, 546, -50, -4, 4, "Shot", target));
          break;
        case 2580:
          g.enemies.add(new Mook(State.RED, -70, 350 - 30, 4, 0, "Shot", "patrol", 0));
          g.enemies.add(new Mook(State.RED, Main.WIDTH + 10, Main.HEIGHT/2 - 30, -4, 0, "Shot", "patrol", 0));
          break;        
        case 3060:
          g.enemies.add(new Elite(State.RED, 330, -50, -4, 4, "Shot", target));
          g.enemies.add(new Mook(State.BLUE, -70, 50, 4, 4, "Shot", "leave", 210));
          g.enemies.add(new Mook(State.BLUE, Main.WIDTH + 10, 50, -4, 4, "Shot", "leave", 450));
          break;
        case 3240:
          g.enemies.add(new Mook(State.BLUE, Main.WIDTH + 10, 50, -3, -4, "Shot", "form", Main.WIDTH/2-60));
          g.enemies.add(new Mook(State.RED, -70, 50, 3, -4, "Shot", "form", Main.WIDTH/2));
          break;
        case 3255:
          g.enemies.add(new Mook(State.BLUE, Main.WIDTH + 10, 50, -3, -4, "Shot", "form", Main.WIDTH/2-60));
          g.enemies.add(new Mook(State.RED, -70, 50, 3, -4, "Shot", "form", Main.WIDTH/2));
          break;
        case 3360:
          g.enemies.add(new Mook(State.RED, -70, 510, 4, 0, "Shot", "patrol", 0));
          g.enemies.add(new Mook(State.BLUE, Main.WIDTH + 10, 210, -4, 0, "Shot", "patrol", 0));
          break;
        case 3600:
          g.enemies.add(new Elite(State.BOTH, 330, -50, -4, 4, "Shot", target));
          g.enemies.add(new Mook(State.BOTH, -70, 400, 4, 0, "Shot", "stay", 160));
          g.enemies.add(new Mook(State.BOTH, Main.WIDTH + 10, 500, -4, 0, "Shot", "stay", 500));
          break;
        case 4080:
          g.enemies.add(new Iris(State.BLUE, 122, "Laser"));
          g.enemies.add(new Iris(State.RED, 256, "Laser"));
          g.enemies.add(new Iris(State.BLUE, 400, "Laser"));
          g.enemies.add(new Iris(State.RED, 544, "Laser"));
          break;
        case 4560:
          g.enemies.add(new Mook(State.BOTH, Main.WIDTH + 10, 50, -4, 4, "Shot", "leave", 330));
          break;
        case 4575:
          g.enemies.add(new Mook(State.BOTH, Main.WIDTH + 10, 50, -4, 4, "Shot", "leave", 390));
          break;
        case 4590:
          g.enemies.add(new Mook(State.BOTH, Main.WIDTH + 10, 50, -4, 4, "Shot", "leave", 450));
          break;
        case 5010:
          g.enemies.add(new Mook(State.RED, -70, 350 - 30, 4, 0, "Shot", "leave", 110));
          g.enemies.add(new Mook(State.RED, Main.WIDTH + 10, Main.HEIGHT/2 - 30, -4, 0, "Shot", "leave", 550));
          break;
        case 5100:
          g.enemies.add(new Mook(State.BOTH, -70, 500, 4, 0, "Shot", "patrol", 0));
          g.enemies.add(new Mook(State.BOTH, Main.WIDTH + 10, 600, -4, 0, "Shot", "patrol", 0));
          break;
        case 5460:
          g.enemies.add(new Iris(State.RED, 8, "Laser"));
          g.enemies.add(new Iris(State.RED, 72, "Laser"));
          g.enemies.add(new Iris(State.RED, 136, "Laser"));
          g.enemies.add(new Iris(State.RED, 200, "Laser"));
          g.enemies.add(new Iris(State.RED, 264, "Laser"));
          g.enemies.add(new Iris(State.RED, 328, "Laser"));
          g.enemies.add(new Iris(State.RED, 392, "Laser"));
          g.enemies.add(new Iris(State.RED, 456, "Laser"));
          g.enemies.add(new Iris(State.RED, 520, "Laser"));
          g.enemies.add(new Iris(State.RED, 584, "Laser"));
          g.enemies.add(new Iris(State.RED, 648, "Laser"));
          g.enemies.add(new Iris(State.RED, 712, "Laser"));
          break;
        case 5610:
          g.enemies.add(new Mook(State.BLUE, -70, 350 - 30, 4, 0, "Shot", "leave", 600));
          break;
        case 5625:
          g.enemies.add(new Mook(State.BLUE, -70, 350 - 30, 4, 0, "Shot", "leave", 540));
          break;
        case 5640:
          g.enemies.add(new Mook(State.BLUE, -70, 350 - 30, 4, 0, "Shot", "leave", 480));
          break;
        case 5655:
          g.enemies.add(new Mook(State.BLUE, -70, 350 - 30, 4, 0, "Shot", "leave", 420));
          break;
        case 5940:
          g.enemies.add(new Mook(State.BLUE, -70, 100, 4, 4, "Shot", "leave", 420));
          g.enemies.add(new Mook(State.BLUE, Main.WIDTH + 10, 100, -4, 4, "Shot", "leave", 240));
          break;
        case 5955:
          g.enemies.add(new Mook(State.BLUE, -70, 100, 4, 4, "Shot", "leave", 360));
          g.enemies.add(new Mook(State.BLUE, Main.WIDTH + 10, 100, -4, 4, "Shot", "leave", 300));
          break;
        case 5970:
          g.enemies.add(new Mook(State.BLUE, -70, 100, 4, 4, "Shot", "leave", 300));
          g.enemies.add(new Mook(State.BLUE, Main.WIDTH + 10, 100, -4, 4, "Shot", "leave", 360));
          break;
        case 5985:
          g.enemies.add(new Mook(State.BLUE, -70, 100, 4, 4, "Shot", "leave", 240));
          g.enemies.add(new Mook(State.BLUE, Main.WIDTH + 10, 100, -4, 4, "Shot", "leave", 420));
          break;
        case 6120:
          g.enemies.add(new Starburt(State.RED, Main.WIDTH+10, 40, -4));
          g.enemies.add(new Starburt(State.RED, Main.WIDTH+10, 340, -4));
          g.enemies.add(new Starburt(State.RED, -138, 190, 4));
          break;
        case 6420:
          g.enemies.add(new Starburt(State.BLUE, -138, 40, 4));
          g.enemies.add(new Starburt(State.BLUE, -138, 340, 4));
          g.enemies.add(new Starburt(State.BLUE, Main.WIDTH+10, 190, -4));
          break;
        case 6855:
          g.enemies.add(new Mook(State.BOTH, Main.WIDTH+10, 400, -4, 0, "Shot", "leave",330));
          break;
        case 6870:
          g.enemies.add(new Mook(State.BOTH, Main.WIDTH+10, 400, -4, 0, "Shot", "leave",390));
          break;
        case 7200:
          g.enemies.add(new Shifter(State.BLUE, -70, 120, 4, 0, "Shot", "stay", 150));
          g.enemies.add(new Shifter(State.RED, -70, 270, 4, 0, "Shot", "stay", 330));
          g.enemies.add(new Shifter(State.BLUE, Main.WIDTH + 10, 420, -4, 0, "Shot", "stay", 510));
          break;
        case 7515:
          g.enemies.add(new Shifter(State.BLUE, -70, 500, 4, -4, "Shot", "leave",248));
          g.enemies.add(new Shifter(State.BLUE, Main.WIDTH + 10, 250, -3, -4, "Shot", "form", Main.WIDTH/2-60));
          break;
        case 7530:
          g.enemies.add(new Shifter(State.BLUE, Main.WIDTH + 10, 250, -3, -4, "Shot", "form", Main.WIDTH/2-60));
          break;
        case 7545:
          g.enemies.add(new Shifter(State.BLUE, Main.WIDTH + 10, 250, -3, -4, "Shot", "form", Main.WIDTH/2-60));
          break;
        case 7680:
          g.enemies.add(new Starburt(State.BLUE, -138, 100, 4));
          g.enemies.add(new Starburt(State.RED, Main.WIDTH + 10, 250, -4));
          g.enemies.add(new Shifter(State.RED, -138, 500, 4, 0, "Shot", "leave",600));
          break;
        case 7695:
          g.enemies.add(new Shifter(State.BLUE, -70, 500, 4, 0, "Shot", "leave",540));
          break;
        case 8100:
          g.enemies.add(new Mook(State.BOTH, -70, 500, 4, -4, "Shot", "leave",180));
          g.enemies.add(new Shifter(State.BLUE, Main.WIDTH + 10, 50, -4, 0, "Shot", "patrol",180));
          g.enemies.add(new Shifter(State.BLUE, Main.WIDTH + 10, 230, -4, 0, "Shot", "patrol",180));
          break;
        case 8115:
          g.enemies.add(new Mook(State.BOTH, -70, 500, 4, -4, "Shot", "leave",120));
          break;
        case 8130:
          g.enemies.add(new Mook(State.BOTH, -70, 500, 4, -4, "Shot", "leave",60));
          break;
        case 8250:
          g.enemies.add(new Iris(State.RED, 100, "Laser"));
          g.enemies.add(new Iris(State.RED, 540, "Laser"));
          break;
        case 8400:
          g.enemies.add(new Shifter(State.RED, Main.WIDTH + 10, 450, -4, 0, "Shot", "stay",402));
          g.enemies.add(new Shifter(State.BLUE, -70, 250, 4, 0, "Shot", "stay",258));
          break;
        case 8430:
          g.enemies.add(new Shifter(State.RED, Main.WIDTH + 10, 350, -4, 0, "Shot", "stay",548));
          g.enemies.add(new Shifter(State.BLUE, -70, 150, 4, 0, "Shot", "stay",114));
          break;
        case 8520:
          g.enemies.add(new Starburt(State.RED, -138, 10, 4));
          break;
        case 8820:
          g.enemies.add(new Elite(State.BOTH, 100, -50, 4, 4, "Shot", target));        
          g.enemies.add(new Elite(State.BOTH, 540, -50, -4, 4, "Shot", target));
          g.enemies.add(new Shifter(State.RED, -70, 200, 4, 0, "Shot", "leave",410));
          break;
        case 9180:
          g.enemies.add(new Shifter(State.BLUE, Main.WIDTH + 10, 200, -4, 0, "Shot", "patrol",410));
          break;
        case 9200:
          g.enemies.add(new Shifter(State.RED, Main.WIDTH + 10, 300, -4, 0, "Shot", "patrol",410));
          break;
        case 9220:
          g.enemies.add(new Shifter(State.BLUE, Main.WIDTH + 10, 200, -4, 0, "Shot", "patrol",410));
          break;
        case 9420:
          g.enemies.add(new Iris(State.RED, 228, "Laser"));
          g.enemies.add(new Iris(State.BLUE, 328, "Laser"));
          g.enemies.add(new Iris(State.RED, 428, "Laser"));
          g.enemies.add(new Starburt(State.BLUE, -138, 150, 4));
          g.enemies.add(new Starburt(State.RED, Main.WIDTH + 10, 300, -4));
          g.enemies.add(new Starburt(State.BLUE, -138, 450, 4));
          break;
        case 9900:
          g.enemies.add(new Mook(State.BOTH, -70, 50, 3, -4, "Shot", "form", Main.WIDTH/2));
          break;
        case 9915:
          g.enemies.add(new Mook(State.BOTH, -70, 50, 3, -4, "Shot", "form", Main.WIDTH/2));
          break;
        case 9930:
          g.enemies.add(new Mook(State.BOTH, -70, 50, 3, -4, "Shot", "form", Main.WIDTH/2));
          break;
        case 9945:
          g.enemies.add(new Mook(State.BOTH, -70, 50, 3, -4, "Shot", "form", Main.WIDTH/2));
          break;
        case 9960:
          g.enemies.add(new Mook(State.BOTH, -70, 50, 3, -4, "Shot", "form", Main.WIDTH/2));
          break;
        case 10080:
          g.enemies.add(new Elite(State.RED, 30, -50, 4, 4, "Shot", target));
          g.enemies.add(new Elite(State.BOTH, 312, -50, 0, 4, "Shot", target));
          g.enemies.add(new Elite(State.BLUE, 594, -50, -4, 4, "Shot", target));
          break;
        default:
      }
    }
    // once it reaches the end and all enemies die
    if(frames > 10080 && g.enemies.size() == 0 && !boss)
    {
      // it now boss time
      boss = true;
      
      // and it resets the frames so that they can work for boss
      frames = 0;
    }
    // if it is boss
    if(boss) {
      //while it is less than about 5 secs it fades out the current boss music
      if(frames <= 320){
        g.audioPlayer.setBackgroundVolume("BGM1", -1*(frames/4));
      }
      // then deletes it(to preserve memory
      if(frames > 320) {
        g.audioPlayer.delSound("BGM1");
      }
      // at about 10 secs it adds the boss and plays the boss fight music
      if(frames == 640){
        g.enemies.add(new Shade(target.getState(), 330, -100, 0, 1, "Shot", g));
        String workingDir = System.getProperty("user.dir");
        g.audioPlayer.addSound(workingDir + FileStore.BG_MUSIC_SHADE, "BGMS");
        g.audioPlayer.setBackgroundVolume("BGMS", -5F);
        g.audioPlayer.loop("BGMS", -1);
      }
      if(frames > 640){
        // if the boss is on screen(to prevent game freezing right at the end
        if(g.enemies.size() > 0){
          // and it is at 30 health
          if(g.enemies.get(0).getHealth() == 30 && !dropped[0]){
            // it drops a weapon powerUp determined using the random power up generator in PowerUp
            PowerUp p = PowerUp.getPowerUp(100,25,25,25,25,0,0,0);
            g.powerUpPickups.add(new PowerUpPickup(g.enemies.get(0).getX(), g.enemies.get(0).getY(), p));
            // sets its boolean so that it only drops one
            dropped[0] = true;
          }
          // same for the rest
          if(g.enemies.get(0).getHealth() == 20 && !dropped[1]){
            // only drops life powerUp
            PowerUp p = PowerUp.getPowerUp(100,0,0,0,0,100,0,0);
            g.powerUpPickups.add(new PowerUpPickup(g.enemies.get(0).getX(), g.enemies.get(0).getY(), p));
            dropped[1] = true;
          }
          if(g.enemies.get(0).getHealth() == 10 && !dropped[2]){
            // drops anything except bomb so that the player cant just bomb the rest of the encounter
            PowerUp p = PowerUp.getPowerUp(100,15,15,15,0,15,15,20);
            g.powerUpPickups.add(new PowerUpPickup(g.enemies.get(0).getX(), g.enemies.get(0).getY(), p));
            dropped[2] = true;
          }
        }
      }
    }
    frames++;
  }
}