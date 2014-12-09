  package com.MRS.NeckbeardEngine;
  
  import java.awt.Rectangle;
  import java.awt.geom.Ellipse2D;
  
  public class HitBox {
    private int x;
    private int y;
    private int height;
    private int width;
    
    public HitBox(int x, int y, int width, int height) {
      //Constructor
      this.x=x;
      this.y=y;
      this.height=height;
      this.width=width;
    }
    
    //Accessors
    public int getX() {
      return x;
    }
    public int getY() {
      return y;
    }
    public int getHeight() {
      return height;
    }
    public int getWidth() {
      return width;
    }
    
    //Mutators
    public void setX (int x) {
      this.x = x;
    }
    public void setY (int y) {
      this.y = y;
    }
    public void setHeight (int height) {
      this.height = height;
    }
    public void setWidth (int width) {
      this.width = width;
    }
    
    /*
     * Collisions
     * Unfortunately, each type of
     * collision needs to be explicitly
     * called, and cannot be implied
     */
    public static boolean checkCollisionRectRect(HitBox h1, HitBox h2) {
      Rectangle r1 = new Rectangle(h1.getX(), h1.getY(), h1.getWidth(), h1.getHeight());
      Rectangle r2 = new Rectangle(h2.getX(), h2.getY(), h2.getWidth(), h2.getHeight());
      return r1.intersects(r2);
    }
    public static boolean checkCollisionRectRadial(HitBox h1, RadialHitBox h2) {
      Rectangle r1 = new Rectangle(h1.getX(), h1.getY(), h1.getWidth(), h1.getHeight());
      Ellipse2D.Double e1 = new Ellipse2D.Double(h2.getX(), h2.getY(), h2.getWidth(), h2.getHeight());
      return e1.intersects(r1);
    }
    public static boolean checkCollisionRadialRadial(RadialHitBox h1, RadialHitBox h2) {
      double distance = Math.sqrt( Math.pow( h1.getX() - h2.getX() , 2) + Math.pow( h1.getY() - h2.getY() , 2 ));
      double sumRadius = (h1.getWidth()/2) + (h2.getWidth()/2);
      return distance <= sumRadius;
    }
  }
