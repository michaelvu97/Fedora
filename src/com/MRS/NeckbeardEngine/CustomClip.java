/* 
 * PROJECT:LodeStar
 * Source can be found at www.github.com/michaelvu97/LodeStar
 * Authors: Safwan Qazi (Project Manager), Roy Liu, Michael Vu
 * Date: 9/17/14
 *
 * Stores an identifier and an audio clip, so that they are paired together.
 */
package com.MRS.NeckbeardEngine;

import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;

public class CustomClip {
  String id;
  Clip c;
  AudioInputStream stream;
  
  public CustomClip() {}
}
