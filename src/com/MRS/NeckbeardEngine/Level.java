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
    frames = 100000;
    this.target = target; 
    
    
  }
  public void check() {    
    //wave loading
    // IF YOU WANT SHADE SET FRAMES TO 100000
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
      case 480:
        g.enemies.add(new Mook(State.RED, Main.WIDTH + 10, 50, -3, -4, "Shot", "form", Main.WIDTH/2-60));
        g.enemies.add(new Mook(State.BLUE, -70, 50, 3, -4, "Shot", "form", Main.WIDTH/2));
        break;
      case 600:
        g.enemies.add(new Mook(State.RED, -70, 400, 4, -2.5, "Shot", "leave", Main.WIDTH/2));
        g.enemies.add(new Mook(State.RED, -70, 400, 4, 0, "Shot", "stay", 258));
        g.enemies.add(new Mook(State.BLUE, Main.WIDTH + 10, 300, -4, 0, "Shot", "stay", 402-60));
        break;
      case 615:
        g.enemies.add(new Mook(State.BLUE, -70, 400, 4, -2.5, "Shot", "leave", Main.WIDTH/2));
        break;
      case 630:
        g.enemies.add(new Mook(State.RED, -70, 400, 4, -2.5, "Shot", "leave", Main.WIDTH/2));
        break;
      case 636:
        g.enemies.add(new Mook(State.BLUE, -70, 100, 4, 0, "Shot", "stay", 114));
        g.enemies.add(new Mook(State.RED, Main.WIDTH + 10, 200, -4, 0, "Shot", "stay", 546-60));
        break;
      case 645:
        g.enemies.add(new Mook(State.BLUE, -70, 400, 4, -2.5, "Shot", "leave", Main.WIDTH/2));
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
      case 780:
        g.enemies.add(new Mook(State.RED, -70, 50, 4, 0, "Shot", "leave", 450));
        break;        
      case 796:
        g.enemies.add(new Mook(State.BLUE, -70, 50, 4, 0, "Shot", "leave", 450));
        break;
      case 812:
        g.enemies.add(new Mook(State.RED, -70, 50, 4, 0, "Shot", "leave", 450));
        break;
      case 828:
        g.enemies.add(new Mook(State.BLUE, -70, 50, 4, 0, "Shot", "leave", 450));
        break;
      case 870:
        g.enemies.add(new Mook(State.RED, -70, 50, 4, 0, "Shot", "stay", 160));
        g.enemies.add(new Mook(State.BLUE, Main.WIDTH + 10, 250, -4, 0, "Shot", "stay", 500));
        g.enemies.add(new Mook(State.BLUE, -70, Main.HEIGHT/2 - 30, 4, 0, "Shot", "patrol", 160));
        g.enemies.add(new Mook(State.BLUE, Main.WIDTH + 10, Main.HEIGHT/2 - 30, -4, 0, "Shot", "patrol", 500));
        break;
      case 1140:
        g.enemies.add(new Mook(State.RED, Main.WIDTH + 70, 00, -3, -4, "Shot", "form", Main.WIDTH/2-60));        
        break;
      case 1155:
        g.enemies.add(new Mook(State.RED, Main.WIDTH + 70, 0, -3, -4, "Shot", "form", Main.WIDTH/2-60));        
        break;
      case 1170:
        g.enemies.add(new Mook(State.RED, Main.WIDTH + 70, 0, -3, -4, "Shot", "form", Main.WIDTH/2-60));        
        break;
      case 1185:
        g.enemies.add(new Mook(State.BLUE, Main.WIDTH + 70, 0, -3, -4, "Shot", "form", Main.WIDTH/2-60));        
        break;
      case 1200:
        g.enemies.add(new Mook(State.BLUE, Main.WIDTH + 70, 0, -3, -4, "Shot", "form", Main.WIDTH/2-60));        
        break;
      case 1215:
        g.enemies.add(new Mook(State.BLUE, Main.WIDTH + 70, 0, -3, -4, "Shot", "form", Main.WIDTH/2-60));        
        break;
      case 1320:
        g.enemies.add(new Mook(State.RED, -70, 300, 4, 2.5, "Shot", "leave", 190));
        break;
      case 1335:
        g.enemies.add(new Mook(State.RED, -70, 300, 4, 2.5, "Shot", "leave", 130));
        break;
      case 1350:
        g.enemies.add(new Mook(State.RED, -70, 300, 4, 2.5, "Shot", "leave", 70));
        break;
      case 1365:
        g.enemies.add(new Mook(State.RED, -70, 300, 4, 2.5, "Shot", "leave", 10));
        break;
      case 1410:
        g.enemies.add(new Mook(State.RED, -70, 50, 3, -4, "Shot", "form", Main.WIDTH/2));
        g.enemies.add(new Mook(State.BLUE, Main.WIDTH + 10, 50, -4, 0, "Shot", "stay", 300));
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
      case 1590:
        g.enemies.add(new Mook(State.BLUE, Main.WIDTH + 10, 150, -4, 0, "Shot", "stay", 660));
        g.enemies.add(new Mook(State.BLUE, -70, 50, 4, 2.5, "Shot", "leave", 130));
        g.enemies.add(new Mook(State.RED, Main.WIDTH + 10, 500, -4, -2.5, "Shot", "leave", 530));
        break;
      case 1605:
        g.enemies.add(new Mook(State.BLUE, -70, 50, 4, 2.5, "Shot", "leave", 70));
        g.enemies.add(new Mook(State.RED, Main.WIDTH + 10, 500, -4, -2.5, "Shot", "leave", 590));
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
      case 100000:
        g.enemies.add(new Shade(target.getState(), 330, -100, 0, 4, "Shot", g));
      default:
        
        
    frames++;
        
    }
    
    
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

//          g.enemies.add(new Mook(State.BLUE, 600, 0, 1, 10, "Shot", "leave", Main.WIDTH/2));
//          g.enemies.add(new Elite(State.BLUE, Main.WIDTH + 70, -50, -4, 4, "Shot", target));
//          g.enemies.add(new Iris(State.BLUE, 200, "Laser"));
//          g.enemies.add(new Shifter(State.BLUE, -70, 70, 4, 0, "Shot", "patrol", target, Main.WIDTH/2));
//          g.enemies.add(new Starburt(State.BLUE, -70, 70, 4));

//          case 180:

//          break;