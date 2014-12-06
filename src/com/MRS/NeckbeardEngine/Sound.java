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
import java.io.File;
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
  
  //The list of all the sounds to be initialized and used
  public ArrayList<CustomClip> sounds = new ArrayList<CustomClip>(1);
  
  public Sound(String[][] file){
    /*
     * Sound takes 2d strings, String[int][0,1]
     * [int] is the identifier
     * [0,1]: 0 is explicit file path,
     *        1 is the identifying tag
     */
    
    //Sound player prerequisites
    Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
    mixer = AudioSystem.getMixer(mixInfos[0]);
    DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
    
    //Loading clips from the string[][] parameters
    try{
      for(int i = 0; i<file.length;i++) {
        
        CustomClip clip = new CustomClip();
        
        //Setting the mixer
        clip.c = (Clip)mixer.getLine(dataInfo); 
        
        //Filepath conversion to URL
        URL filePath = new File(file[i][0]).toURI().toURL();
        
        //Creating the stream from URL
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(filePath);
        
        //Opening the stream into the target clip
        clip.c.open(audioStream);
        
        //Assigning clip id
        clip.id = file[i][1];
        
        //Adding the clip to the arraylist
        sounds.add(i,clip);
      }
    }
    
    //Exception Catches
    catch(LineUnavailableException lue){lue.printStackTrace();}
    catch(UnsupportedAudioFileException uafe){uafe.printStackTrace();}
    catch(IOException ioe){ioe.printStackTrace();}
  }
  
  public void play(String id) {
    //Plays or resumes the selected clip
    for(int i = 0; i<sounds.size();i++) {
      if(sounds.get(i).id.equals(id)) {
      Clip c = sounds.get(i).c; 
      c.setMicrosecondPosition(0);
      c.start();
      }
    }
  }
  
  public void stop(String id) {
    //Pauses the selected clip
    for(int i = 0; i<sounds.size();i++) {
      if(sounds.get(i).id.equals(id)) {
      Clip c = sounds.get(i).c; 
      c.stop();
      }
    }
  }
  
  public void loop(String id, int loops) {
    //Loops the selected clip (use LOOP_CONTINUOUSLY for forever)
    for(int i = 0; i<sounds.size();i++) {
      if(sounds.get(i).id.equals(id)) {
      Clip c = sounds.get(i).c; 
      c.loop(loops);
      }
    }
  }
  
  public void setMicrosecondPosition(String id, long start){
    //Scrub to position on selected clip
    for(int i = 0; i<sounds.size();i++) {
      if(sounds.get(i).id.equals(id)) {
      Clip c = sounds.get(i).c; 
      c.setMicrosecondPosition(start);
      }
    }
  }
  
  public void setLoopPoints(String id, int start, int end) {
    //Set Loop Points on selected clip, must know frames
    for(int i = 0; i<sounds.size();i++) {
      if(sounds.get(i).id.equals(id)) {
      Clip c = sounds.get(i).c; 
      c.setLoopPoints(start,end);
      }
    }
  }
  
  public void setVolume(String id,float vol) {
    //Set volume.(range is -80.0f to 6.0f) in db
    for(int i = 0; i<sounds.size();i++) {
      if(sounds.get(i).id.equals(id)) {
      Clip clip = sounds.get(i).c;
    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    gainControl.setValue(vol);
      }
    }
    
  }
  
  public void addSound(String id) {                                            //Add new sound, will be added to the end of list
    Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
    mixer = AudioSystem.getMixer(mixInfos[0]);
    DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
    CustomClip clip = new CustomClip();
    try{
      clip.c = (Clip)mixer.getLine(dataInfo);    
      URL filePath = Sound.class.getResource(id);
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(filePath);
      clip.c.open(audioStream);
      clip.id = id;
      sounds.add(clip);
    }
    catch(LineUnavailableException lue){lue.printStackTrace();}
    catch(UnsupportedAudioFileException uafe){uafe.printStackTrace();}
    catch(IOException ioe){ioe.printStackTrace();}
  }
  
  public void delSound(String id) {     //Remove file(to free up space)
                   //Note: If you remove a sound, all the sounds with a greater index will all be moved left(subtracted by one)
    for(int i = 0; i<sounds.size();i++) {
      if(sounds.get(i).id.equals(id)) {
      sounds.remove(i);
      }
    }
  }
  
  public String getId(int i) {
    CustomClip c = sounds.get(i);
    return c.id;
  }
  
  public int getSize() {
    return sounds.size();
  }
}
