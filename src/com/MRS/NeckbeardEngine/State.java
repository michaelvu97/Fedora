/* 
 * PROJECT:LodeStar
 * Source can be found at www.github.com/michaelvu97/LodeStar
 * Authors: Safwan Qazi (Project Manager), Roy Liu, Michael Vu
 * Inception Date: 9/17/14
 *
 * This is the enumeration for the 3 possible states for objects in the game:
 * RED, BLUE, or BOTH
 */

package com.MRS.NeckbeardEngine;

public enum State {
  RED (1),
  BLUE (2),
  BOTH (3);
  
  private final int state;
  
  private State (int state) {
    this.state = state;
  }
  
  public int getInt() {
    return this.state;
  }
}
