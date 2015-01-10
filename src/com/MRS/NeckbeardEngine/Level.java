package com.MRS.NeckbeardEngine;

import com.MRS.NeckbeardEngine.Enemies.*;
import com.MRS.NeckbeardEngine.Projectiles.*;
import java.util.ArrayList;

public class Level {
  
  Game g;
  private int frames;
  private Player target;
  private boolean boss = false;
  
  public Level(Game g, Player target) {
    this.g = g;
    frames = 10080;
    this.target = target; 
    
    
  }
  public void check() {    
    //wave loading
    // IF YOU WANT SHADE SET FRAMES TO 100000
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
        case 420://blaze
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
          break;
        case 2580:
          g.enemies.add(new Elite(State.BLUE, 330, -50, -4, 4, "Shot", target));
          g.enemies.add(new Mook(State.RED, -70, 350 - 30, 4, 0, "Shot", "patrol", 0));
          g.enemies.add(new Mook(State.RED, Main.WIDTH + 10, Main.HEIGHT/2 - 30, -4, 0, "Shot", "patrol", 0));
          break;        
        case 3060:
          g.enemies.add(new Elite(State.RED, 330, -50, -4, 4, "Shot", target));
          g.enemies.add(new Mook(State.BLUE, -70, 50, 4, 4, "Shot", "leave", 210));
          g.enemies.add(new Mook(State.BLUE, Main.WIDTH + 10, 50, -4, 4, "Shot", "leave", 450));
          break;
        case 3075:
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
        case 3270:
          g.enemies.add(new Mook(State.BLUE, Main.WIDTH + 10, 50, -3, -4, "Shot", "form", Main.WIDTH/2-60));
          g.enemies.add(new Mook(State.RED, -70, 50, 3, -4, "Shot", "form", Main.WIDTH/2));
          break;
        case 3360:
          g.enemies.add(new Mook(State.RED, -70, 510, 4, 0, "Shot", "patrol", 0));
          g.enemies.add(new Mook(State.BLUE, Main.WIDTH + 10, 210, -4, 0, "Shot", "patrol", 0));
          break;
        case 3390:
          g.enemies.add(new Mook(State.BLUE, -70, 610, 4, 0, "Shot", "patrol", 0));
          g.enemies.add(new Mook(State.RED, Main.WIDTH + 10, 10, -4, 0, "Shot", "patrol", 0));
          break;
        case 3420://blaze
          g.enemies.add(new Mook(State.RED, -70, 110, 4, 0, "Shot", "patrol", 0));
          g.enemies.add(new Mook(State.BLUE, Main.WIDTH + 10, 310, -4, 0, "Shot", "patrol", 0));
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
        case 4980:
          g.enemies.add(new Elite(State.BOTH, 330, -50, -4, 4, "Shot", target));
          g.enemies.add(new Mook(State.RED, -70, 350 - 30, 4, 0, "Shot", "leave", 230));
          g.enemies.add(new Mook(State.RED, Main.WIDTH + 10, Main.HEIGHT/2 - 30, -4, 0, "Shot", "leave", 430));
          break;
        case 4995:
          g.enemies.add(new Mook(State.RED, -70, 350 - 30, 4, 0, "Shot", "leave", 170));
          g.enemies.add(new Mook(State.RED, Main.WIDTH + 10, Main.HEIGHT/2 - 30, -4, 0, "Shot", "leave", 490));
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
        case 5760:
          g.enemies.add(new Mook(State.BLUE, -70, 50, 4, 0, "Shot", "patrol", 0));
          g.enemies.add(new Mook(State.BLUE, Main.WIDTH + 10, 150, -4, 0, "Shot", "patrol", 0));        
        case 5775:
          g.enemies.add(new Mook(State.BLUE, -70, 50, 4, 0, "Shot", "patrol", 0));
          g.enemies.add(new Mook(State.BLUE, Main.WIDTH + 10, 150, -4, 0, "Shot", "patrol", 0));
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
        case 6660:
          g.enemies.add(new Mook(State.BOTH, -70, 500, 4, -4, "Shot", "leave",248));
          g.enemies.add(new Mook(State.BOTH, Main.WIDTH+10, 500, -4, -4, "Shot", "leave",412));
          break;
        case 6675:
          g.enemies.add(new Mook(State.BLUE, -70, 500, 4, -4, "Shot", "leave",166));
          g.enemies.add(new Mook(State.RED, Main.WIDTH+10, 500, -4, -4, "Shot", "leave",494));
          break;
        case 6690:
          g.enemies.add(new Mook(State.BOTH, -70, 500, 4, -4, "Shot", "leave",84));
          g.enemies.add(new Mook(State.BOTH, Main.WIDTH+10, 500, -4, -4, "Shot", "leave",576));
          break;
        case 6840:
          g.enemies.add(new Elite(State.BOTH, 560, -50, -4, 4, "Shot", target));
          g.enemies.add(new Starburt(State.RED, -138, 190, 4));
          g.enemies.add(new Mook(State.BOTH, Main.WIDTH+10, 400, -4, 0, "Shot", "leave",270));
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
        case 7500:
          g.enemies.add(new Shifter(State.RED, -70, 500, 4, -4, "Shot", "leave",248));
          g.enemies.add(new Shifter(State.BLUE, Main.WIDTH + 10, 250, -3, -4, "Shot", "form", Main.WIDTH/2-60));
          break;
        case 7515:
          g.enemies.add(new Shifter(State.BLUE, -70, 500, 4, -4, "Shot", "leave",248));
          g.enemies.add(new Shifter(State.BLUE, Main.WIDTH + 10, 250, -3, -4, "Shot", "form", Main.WIDTH/2-60));
          break;
        case 7530:
          g.enemies.add(new Shifter(State.RED, -70, 500, 4, -4, "Shot", "leave",248));
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
        case 7800:
          g.enemies.add(new Elite(State.BOTH, 330, -50, -4, 4, "Shot", target));
          break;
        case 8100:
          g.enemies.add(new Mook(State.BOTH, -70, 500, 4, -4, "Shot", "leave",180));
          g.enemies.add(new Shifter(State.BLUE, Main.WIDTH + 10, 50, -4, 0, "Shot", "patrol",180));
          g.enemies.add(new Shifter(State.BLUE, Main.WIDTH + 10, 230, -4, 0, "Shot", "patrol",180));
          break;
        case 8115:
          g.enemies.add(new Shifter(State.BLUE, Main.WIDTH + 10, 140, -4, 0, "Shot", "patrol",180));
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
          g.enemies.add(new Mook(State.BOTH, Main.WIDTH + 10, 250, -3, -4, "Shot", "form", Main.WIDTH/2-60));
          break;
        case 8535:        
          g.enemies.add(new Mook(State.BOTH, Main.WIDTH + 10, 250, -3, -4, "Shot", "form", Main.WIDTH/2-60));
          break;
        case 8550:        
          g.enemies.add(new Mook(State.BOTH, Main.WIDTH + 10, 250, -3, -4, "Shot", "form", Main.WIDTH/2-60));
          break;
        case 8820:
          g.enemies.add(new Elite(State.BOTH, 100, -50, 4, 4, "Shot", target));        
          g.enemies.add(new Elite(State.BOTH, 540, -50, -4, 4, "Shot", target));
          g.enemies.add(new Shifter(State.RED, -70, 200, 4, 0, "Shot", "leave",410));
          break;
        case 8840:
          g.enemies.add(new Shifter(State.BLUE, -70, 300, 4, 0, "Shot", "leave",330));
          break;        
        case 8860:
          g.enemies.add(new Shifter(State.RED, -70, 200, 4, 0, "Shot", "leave",250));
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
        case 9600:
          g.enemies.add(new Mook(State.BOTH, -70, 350 - 30, 4, 0, "Shot", "leave", 600));
          break;
        case 9615:
          g.enemies.add(new Mook(State.BOTH, -70, 350 - 30, 4, 0, "Shot", "leave", 540));
          break;
        case 9630:
          g.enemies.add(new Mook(State.BOTH, -70, 350 - 30, 4, 0, "Shot", "leave", 480));
          break;
        case 9645:
          g.enemies.add(new Mook(State.BOTH, -70, 350 - 30, 4, 0, "Shot", "leave", 420));
          break;
        case 9660:
          g.enemies.add(new Mook(State.BOTH, -70, 350 - 30, 4, 0, "Shot", "leave", 360));
          break;
        case 9780:
          g.enemies.add(new Shifter(State.BLUE, -70, 1, 4, 0, "Shot", "patrol", 360));
          g.enemies.add(new Shifter(State.BLUE, Main.WIDTH + 10, 420, -4, 0, "Shot", "patrol", 360));        
          break;
        case 9862:
          g.enemies.add(new Shifter(State.RED, -70, 140, 4, 0, "Shot", "patrol", 360));
          g.enemies.add(new Shifter(State.RED, Main.WIDTH + 10, 280, -4, 0, "Shot", "patrol", 360));        
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
        case 10020:
          g.enemies.add(new Mook(State.BOTH, Main.WIDTH+10, 250, -4, 0, "Shot", "leave",240));
          break;
        case 10035:
          g.enemies.add(new Mook(State.BOTH, Main.WIDTH+10, 250, -4, 0, "Shot", "leave",300));
          break;
        case 10050:
          g.enemies.add(new Mook(State.BOTH, Main.WIDTH+10, 250, -4, 0, "Shot", "leave",360));
          break;
        case 10065:
          g.enemies.add(new Mook(State.BOTH, Main.WIDTH+10, 250, -4, 0, "Shot", "leave",420));
          break;
        case 10080:
          //g.enemies.add(new Elite(State.RED, 30, -50, 4, 4, "Shot", target));
          //g.enemies.add(new Elite(State.BOTH, 312, -50, 0, 4, "Shot", target));
          //g.enemies.add(new Elite(State.BLUE, 594, -50, -4, 4, "Shot", target));
          break;
        default:
      }
    }
    if(frames > 10080 && g.enemies.size() == 0 && !boss)
    {
      boss = true;
      frames = 0;
    }
    if(boss) {
      if(frames <= 320){
        g.audioPlayer.setBackgroundVolume("BGM1", -1*(frames/4));
      }
      if(frames > 320 && frames < 640) {
        g.audioPlayer.delSound("BGM1");
      }
      switch(frames) {
        case 640:
          g.enemies.add(new Shade(target.getState(), 330, -100, 0, 1, "Shot", g));
          String workingDir = System.getProperty("user.dir");
          g.audioPlayer.addSound(workingDir + FileStore.BG_MUSIC_SHADE, "BGMS");
          g.audioPlayer.loop("BGMS", -1);
          break;
        default:
      }
    }
    frames++;
  }
}

//          g.enemies.add(new Mook(State.BLUE, 600, 0, 1, 10, "Shot", "leave", Main.WIDTH/2));
//          g.enemies.add(new Elite(State.BLUE, Main.WIDTH + 70, -50, -4, 4, "Shot", target));
//          g.enemies.add(new Iris(State.BLUE, 200, "Laser"));
//          g.enemies.add(new Shifter(State.BLUE, -70, 70, 4, 0, "Shot", "patrol", Main.WIDTH/2));
//          g.enemies.add(new Starburt(State.BLUE, -70, 70, 4));

//          g.enemies.add(new Shade(target.getState(), 330, -100, 0, 3, "Shot", g));

//          case 180:

//          break;