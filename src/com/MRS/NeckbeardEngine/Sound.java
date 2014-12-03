/*Description: To Use sounds, create new instance of Sound with a String array of the files to be opened in that level.
 * Then access those files by using the methods and remembering what order you put them in.
 * Note: If you remove a sound, all the sounds with a greater index will all be moved left(subtracted by one)
 * e.g. 
 * String file = {"one.wav","two.wav"};
 * 
 * Sound player = new Sound(file);
 * 
 * player.play(0);  //starts one.wav
 * 
 * player.play(1);
 * 
 * player.setVolume(0,-40.0); //sets volume of one.wav to -40.0
 */

package com.MRS.NeckbeardEngine;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.util.ArrayList;

public class  Sound{
  public Mixer mixer;
  public ArrayList<CustomClip> sounds = new ArrayList<CustomClip>(1);
  
  public Sound(String[] file){
    Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
    mixer = AudioSystem.getMixer(mixInfos[0]);
    DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
    try{
      for(int i = 0; i<file.length;i++) {
        CustomClip clip = new CustomClip();
        clip.c = (Clip)mixer.getLine(dataInfo); 
        URL filePath = Sound.class.getResource(file[i]);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(filePath);
        clip.c.open(audioStream);
        clip.id = file[i];
        sounds.add(i,clip);
      }
    }
    catch(LineUnavailableException lue){lue.printStackTrace();}
    catch(UnsupportedAudioFileException uafe){uafe.printStackTrace();}
    catch(IOException ioe){ioe.printStackTrace();}
  }
  public void play(String file) {
    for(int i = 0; i<sounds.size();i++) {
      if(sounds.get(i).id.equals(file)) {
      Clip c = sounds.get(i).c; 
      c.start();
      }
    }                                                                      //Play
  }
  public void stop(String file) {                                                      //Pause
    for(int i = 0; i<sounds.size();i++) {
      if(sounds.get(i).id.equals(file)) {
      Clip c = sounds.get(i).c; 
      c.stop();
      }
    }
  }
  public void loop(String file, int loops) {                                           //Loop(use LOOP_CONTINUOUSLY for forever)
    for(int i = 0; i<sounds.size();i++) {
      if(sounds.get(i).id.equals(file)) {
      Clip c = sounds.get(i).c; 
      c.loop(loops);
      }
    }
  }
  public void setMicrosecondPosition(String file, long start){                         //Scrub to that position
    for(int i = 0; i<sounds.size();i++) {
      if(sounds.get(i).id.equals(file)) {
      Clip c = sounds.get(i).c; 
      c.setMicrosecondPosition(start);
      }
    }
  }
  public void setLoopPoints(String file, int start, int end) {                         //Set Loop Points, must know frames
    for(int i = 0; i<sounds.size();i++) {
      if(sounds.get(i).id.equals(file)) {
      Clip c = sounds.get(i).c; 
      c.setLoopPoints(start,end);
      }
    }
  }
  public void setVolume(String file,float vol) {                                       //Set volume.(range is -80.0f to 6.0f)
    for(int i = 0; i<sounds.size();i++) {
      if(sounds.get(i).id.equals(file)) {
      Clip clip = sounds.get(i).c;
    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    gainControl.setValue(vol);
      }
    }
    
  }
  public void addSound(String file) {                                            //Add new sound, will be added to the end of list
    Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
    mixer = AudioSystem.getMixer(mixInfos[0]);
    DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
    CustomClip clip = new CustomClip();
    try{
      clip.c = (Clip)mixer.getLine(dataInfo);    
      URL filePath = Sound.class.getResource(file);
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(filePath);
      clip.c.open(audioStream);
      clip.id = file;
      sounds.add(clip);
    }
    catch(LineUnavailableException lue){lue.printStackTrace();}
    catch(UnsupportedAudioFileException uafe){uafe.printStackTrace();}
    catch(IOException ioe){ioe.printStackTrace();}
  }
  public void delSound(String file) {     //Remove file(to free up space)
                   //Note: If you remove a sound, all the sounds with a greater index will all be moved left(subtracted by one)
    for(int i = 0; i<sounds.size();i++) {
      if(sounds.get(i).id.equals(file)) {
      sounds.remove(i);
      }
    }
  }
  public String getId(int i) {
    CustomClip c = sounds.get(i);
    System.out.println(c.id);
    return c.id;
  }
  public int getSize() {
    return sounds.size();
  }
}
