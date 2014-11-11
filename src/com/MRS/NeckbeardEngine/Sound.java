package com.MRS.NeckbeardEngine;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;
public class  Sound{
public static Mixer mixer;
public static Clip clip;

 public Sound(String file) {
  Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
  mixer = AudioSystem.getMixer(mixInfos[0]);
  DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
  try{clip = (Clip)mixer.getLine(dataInfo);}
  catch(LineUnavailableException lue) {lue.printStackTrace();}
  try {
   URL soundURL = Sound.class.getResource(file);
   AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
   clip.open(audioStream);
  }
  catch(LineUnavailableException lue){lue.printStackTrace();}
  catch(UnsupportedAudioFileException uafe){uafe.printStackTrace();}
  catch(IOException ioe){ioe.printStackTrace();}
 }
  public void play()
 {
  clip.start();
 }
  public void loop() {
   clip.loop(clip.LOOP_CONTINUOUSLY);
  }
  public void customLoop(int times){
  clip.loop(times-1);
  }
  public void stop()
  {
   if(clip.isRunning()) {
     clip.stop();
   }
  }
  
  public void volume(float vol) {
   FloatControl gainControl = 
        (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    gainControl.setValue(vol);
  }
}
