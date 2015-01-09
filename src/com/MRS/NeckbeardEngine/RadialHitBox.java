/* 
 * PROJECT:LodeStar
 * Source can be found at www.github.com/michaelvu97/LodeStar
 * Authors: Safwan Qazi (Project Manager), Roy Liu, Michael Vu
 * Date: 9/17/14
 * 
 * The way these are constructed is as follows:
 *  -x and y are the 0,0 of the bounding box
 *  -the diameter is the width of the bounding box
 *  -0,0 is the top left
 */
package com.MRS.NeckbeardEngine;

public class RadialHitBox extends HitBox {
  public RadialHitBox (int x, int y, int diameter) {
    super(x,y,diameter,diameter);
  }
}