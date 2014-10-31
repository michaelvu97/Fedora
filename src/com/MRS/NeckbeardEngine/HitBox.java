package com.MRS.NeckbeardEngine;
class Hitbox {
  private int x;
  private int y;
  private int height;
  private int width;
  
  public Hitbox(int x, int y, int width, int height) {
    this.x=x;
    this.y=y;
    this.height=height;
    this.width=width;
  }
  public boolean checkCollision(Hitbox a) {
    int maxX = Math.max(a.x,x);
    int minX = Math.min(a.x,x);
    int maxY = Math.max(a.y,y);
    int minY = Math.min(a.y,y);
    //Memory values created that will store the associated value
    int maxH;
    //minL contains the length of the rectangle that has the smallest Y value
    int minW;
    //minW contains the width of the rectangle that has the smallest X value
    if(maxX == a.x) {
      //If the maxX is from rectangle r1...
    
      minW = width;
    }
    else {
      //Otherwise the reverse occurs, because that is the only other possibility
      minW = a.width;
    }
    if(maxY == a.y) {
      //Similarly if the maxY values is from r1
    
      maxH = a.height;
    }
    //Otherwise they switch because that is the only other possiblity
    else {
    
      maxH = height;
    }
    
    return ((maxX<=(minX+minW))&&(minY>=(maxY-maxH)));//checks that they contain or are touching
  }
}
