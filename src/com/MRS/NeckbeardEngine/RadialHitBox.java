package com.MRS.NeckbeardEngine;

public class RadialHitBox extends HitBox {
  /*
   * The way these are constructed is as follows:
   *  -x and y are the 0,0 of the bounding box
   *  -the diameter is the width of the bounding box
   *  -0,0 is the top left
   */
  public RadialHitBox (int x, int y, int diameter) {
    super(x,y,diameter,diameter);
  }
}