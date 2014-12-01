/*Description: To Use sounds, create new instance of Sound with a String array of the files to be opened in that level.
 * Then access those files by using the variable sound[index of sound file].
 * The functions are:
 * start()-play;
 * stop()-pause;
 * loop(int numberOfLoopsPlusOne)-loop
 * loop(LOOP_CONTINUOUSLY)-loop forever
 * setMicrosecondPosition(long microseconds)-scrubbing
 * setLoopPoints(int startFrame, int endFrame)-specific loops, need to know exact frame
 * setVolume(float vol, int index)-changes gain of clip at index to vol(-80.0 to 6.0);
 * 
 * e.g. 
 * String file = {"one.wav","two.wav"};
 * Sound player = new Sound(file);
 * player.sounds[0].start();  //starts one.wav
 * player.sounds[1].start();
 * 
 * player.sounds[0].setVolume(-40.0,0); //sets volume of one.wav to -40.0
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

public class  Sound{
  public Mixer mixer;
  public Clip[] sounds;
  public Sound(String[] file){
    sounds = new Clip[file.length];
    Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
    mixer = AudioSystem.getMixer(mixInfos[0]);
    DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
    try{
      for(int i = 0; i<file.length;i++) {
        sounds[i] = (Clip)mixer.getLine(dataInfo);
      }
    }
    catch(LineUnavailableException lue) {lue.printStackTrace();}
    try {
      for(int i = 0; i<sounds.length;i++) {
        URL filePath = Sound.class.getResource(file[i]);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(filePath);
        sounds[i].open(audioStream);
      }
    }
    catch(LineUnavailableException lue){lue.printStackTrace();}
    catch(UnsupportedAudioFileException uafe){uafe.printStackTrace();}
    catch(IOException ioe){ioe.printStackTrace();}
  }
  public void setVolume(float vol,int i) {
    FloatControl gainControl = (FloatControl) sounds[i].getControl(FloatControl.Type.MASTER_GAIN);
    gainControl.setValue(vol);
  }
}
