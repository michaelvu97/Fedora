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
  public ArrayList<Clip> sounds = new ArrayList<Clip>(1);
  
  public Sound(String[] file){
    Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
    mixer = AudioSystem.getMixer(mixInfos[0]);
    DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
    try{
      for(int i = 0; i<file.length;i++) {
        Clip clip;
        clip = (Clip)mixer.getLine(dataInfo); 
        URL filePath = Sound.class.getResource(file[i]);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(filePath);
        clip.open(audioStream);
        sounds.add(i,clip);
      }
    }
    catch(LineUnavailableException lue){lue.printStackTrace();}
    catch(UnsupportedAudioFileException uafe){uafe.printStackTrace();}
    catch(IOException ioe){ioe.printStackTrace();}
  }
  public void play(int i) {                                                      //Play
    Clip c = sounds.get(i); 
    c.start();
  }
  public void stop(int i) {                                                      //Pause
    Clip c = sounds.get(i);
    c.stop();
  }
  public void loop(int i, int loops) {                                           //Loop(use LOOP_CONTINUOUSLY for forever)
    Clip c = sounds.get(i);
    c.loop(loops);
  }
  public void setMicrosecondPosition(int i, long start){                         //Scrub to that position
    Clip c = sounds.get(i);
    c.setMicrosecondPosition(start);
  }
  public void setLoopPoints(int i, int start, int end) {                         //Set Loop Points, must know frames
    Clip c = sounds.get(i);
    c.setLoopPoints(start,end);
  }
  public void setVolume(int i,float vol) {                                       //Set volume range is -80.0f to 6.0f
    Clip clip = sounds.get(i);
    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    gainControl.setValue(vol);
  }
  public void addSound(String file) {                                            //Add new sound, will be added to the end of list
    Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
    mixer = AudioSystem.getMixer(mixInfos[0]);
    DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
    Clip c;
    try{
      c = (Clip)mixer.getLine(dataInfo);    
      URL filePath = Sound.class.getResource(file);
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(filePath);
      c.open(audioStream);
      sounds.add(c);
    }
    catch(LineUnavailableException lue){lue.printStackTrace();}
    catch(UnsupportedAudioFileException uafe){uafe.printStackTrace();}
    catch(IOException ioe){ioe.printStackTrace();}
  }
  public void delSound(int i) {     //Remove file(to free up space)
    sounds.remove(i);               //Note: If you remove a sound, all the sounds with a greater index will all be moved left(subtracted by one)
  }
}
