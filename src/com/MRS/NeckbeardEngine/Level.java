package com.MRS.NeckbeardEngine;

import com.MRS.NeckbeardEngine.Enemies.*;
import com.MRS.NeckbeardEngine.Projectiles.*;
import com.MRS.NeckbeardEngine.Waves.*;
import java.util.ArrayList;

public class Level {
  
  Game g;
  private int frames;
  private Player target;
  private ArrayList<SpawnEvent> spawnEvents;
  
  public Level(Game g, Player target) {
    spawnEvents = new ArrayList<SpawnEvent>();
    this.g = g;
    frames = 0;
    this.target = target; 
    
    
  }
  public void check() {
    frames++;
        
    //wave loading
    switch(frames) {
      case 180:
        addPackage(DropInWave.unpack(State.getRandom(), frames));
//        g.enemies.add(new Elite(State.BLUE, 300, -100, 0, 3, "Shot", null, 0, target));
//        g.enemies.add(new Shifter(State.BLUE, -72, 100, 3, 0, "Shot", null, 0, "stay", target, 0));
//        g.enemies.add(new Iris(State.BLUE, 100, -128, 3, "Laser", null, 0));
//        g.enemies.add(new Iris(State.BLUE, 500, -128, 3, "Laser", null, 0));
//        g.enemies.add(new Iris(State.RED, 300, -128, 3, "Laser", null, 0));
//        g.enemies.add(new Starburt(State.BLUE, -200, 100, 5, 10, "StarburtShot", null, 0));
//        g.enemies.add(new Mook(State.BLUE, -30, -50, 3, 4, "Shot", null, 0, "form3", Main.WIDTH/4));
//        g.enemies.add(new Mook(State.RED, -90, -110, 3, 4, "Shot", null, 0, "form3", Main.WIDTH/4));
//        g.enemies.add(new Mook(State.BLUE, 750, -50, -3, 4, "Shot", null, 0, "form3", Main.WIDTH/4));
//        g.enemies.add(new Mook(State.RED, 810, -110, -3, 4, "Shot", null, 0, "form3", Main.WIDTH/4));
//        
        break;
      case 360:
        addPackage(IrisWave.unpack(State.BLUE, frames, Main.WIDTH/2));
        break;
//      case 750:
//        g.enemies.add(new Mook(State.BLUE, 720, 800, -4, -4, "Shot", null, 0, "form2", Main.WIDTH/2));
//        g.enemies.add(new Mook(State.RED, 780, 860, -4, -4, "Shot", null, 0, "form2", Main.WIDTH/2));
//      case 840:
//        g.enemies.add(new Mook(State.RED, 600, 0, -1, 10, "Shot", null, 0, "leave", Main.WIDTH/2));
//        break;
//      case 900:
//        g.enemies.add(new Mook(State.RED, 600, 0, -1, 10, "Shot", null, 0, "leave", Main.WIDTH/2));
//        break;
//      case 1200:
//        g.enemies.add(new Mook(State.BLUE, 600, 0, -1, 10, "Shot", null, 0, "leave", Main.WIDTH/2));
//        g.enemies.add(new Mook(State.BLUE, 0, 0, 1, 10, "Shot", null, 0, "leave", Main.WIDTH/2));
//        break;
//      case 1800:
//        g.enemies.add(new Mook(State.RED, 100, -60, 1, 15, "Shot", null, 0, "stay", Main.WIDTH/2));
//        break;
//      case 2400:
//        g.enemies.add(new Mook(State.BLUE, 600, 0, -1, 10, "Shot", null, 0, "leave", Main.WIDTH/2));
//        break;
//      case 3000:
//        g.enemies.add(new Mook(State.BLUE, 600, 0, -1, 10, "Shot", null, 0, "leave", Main.WIDTH/2));
//        g.enemies.add(new Mook(State.RED, 0, 70, 1, 15, "Shot", null, 0, "stay", Main.WIDTH/2));
//        break;
//      case 3600:
//        g.enemies.add(new Mook(State.BLUE, 600, 0, -1, 10, "Shot", null, 0, "leave", Main.WIDTH/2));
//        g.enemies.add(new Mook(State.RED, 0, 70, 1, 15, "Shot", null, 0, "stay", Main.WIDTH/2));
//        break;
//      case 3660:
//        g.enemies.add(new Mook(State.BLUE, 600, 0, -1, 10, "Shot", null, 0, "leave", Main.WIDTH/2));
//        break;
//      case 3720:
//        g.enemies.add(new Mook(State.RED, 600, 0, -1, 10, "Shot", null, 0, "leave", Main.WIDTH/2));
//        break;
//      case 3780:
//        g.enemies.add(new Mook(State.BLUE, 600, 0, -1, 10, "Shot", null, 0, "leave", Main.WIDTH/2));
//        break;
//      case 3840:
//        g.enemies.add(new Mook(State.RED, 600, 0, -1, 10, "Shot", null, 0, "leave", Main.WIDTH/2));
//        break;
//      case 3900:
//        g.enemies.add(new Mook(State.RED, 600, 0, -1, 10, "Shot", null, 0, "leave", Main.WIDTH/2));
//        break;
      default:
        
    }
    
//    enemies.add(new Mook(State.BLUE, 600, 0, 1, 10, "Shot", null, 0, "leave", Main.WIDTH/2));
//    enemies.add(new Mook(State.RED, 0, 70, 1, 15, "Shot", null, 0, "stay",Main.WIDTH/2));
//    powerUpPickups.add(new PowerUpPickup(150, 150, PowerUp.FAST_SHOT));
//    powerUpPickups.add(new PowerUpPickup(300, 0, PowerUp.BOMB));
//    powerUpPickups.add(new PowerUpPickup(200, 0, PowerUp.RAPID_FIRE));
//    powerUpPickups.add(new PowerUpPickup(250, 0, PowerUp.SCATTER_SHOT));
//    powerUpPickups.add(new PowerUpPickup(400, 0, PowerUp.SHIELD));
//    powerUpPickups.add(new PowerUpPickup(450, 0, PowerUp.EXTRA_SHIP));
//    powerUpPickups.add(new PowerUpPickup(500, 0, PowerUp.SPEED_BOOST));
    
    //Checking the spawnPackages
    for (int i = 0; i < spawnEvents.size(); i++) {
      SpawnEvent s = spawnEvents.get(i);
      
      if (frames >= s.getSpawnFrame()) {
        g.enemies.add(s.getSpawnable());
        spawnEvents.remove(s);
        i--;
      }
    }
  }

  public void addPackage (ArrayList<SpawnEvent> events) {
    for (int i = 0; i < events.size(); i++) {
      this.spawnEvents.add(events.get(i));
    }
  } 
}