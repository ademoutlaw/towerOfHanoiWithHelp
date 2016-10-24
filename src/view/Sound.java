/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

//import com.sun.media.jfxmedia.AudioClip;
import java.applet.Applet;
import java.applet.AudioClip;
import java.util.ArrayList;
import java.util.List;

//import javafx.scene.media.AudioClip;


//import java.applet.AudioClip;


/**
 *
 * @author outlaw
 */
public class Sound {        
    
    private final AudioClip clip;
    private  boolean isMusic;
    static private boolean musicPlayed;
    static private boolean soundPlayed;
    static private Sound current = new Sound("click");
    static private List<AudioClip> clips;

    public Sound(String name, boolean isMusic) {
        this.isMusic = isMusic;
        musicPlayed = soundPlayed = true;
        clip =  Applet.newAudioClip(getClass().getResource("/view/sounds/"+name+".wav"));
        if(isMusic){
            if(clips==null){
                clips = new ArrayList();
            }
            clips.add(clip);
        }
    }
    
    public Sound(String name){
        this(name, false);
    }
    
    static public void muteSounds(){
        soundPlayed = false;        
    }
    
    static public void muteMusic(){
        musicPlayed = false;
        if(clips!=null){
            for(AudioClip c:clips){
                c.stop();
            }
        }
    }
    
    static public void unMuteSounds(){
        soundPlayed = true;
    }
    
    static public void unMuteMusic(){
        musicPlayed = true;
        current.play();
    }
    
    public void play() {
        if( (isMusic && musicPlayed)||(!isMusic && soundPlayed)){       
            clip.play(); 
            if(isMusic){
                current = this;
            }
        }
    }
    
    public void stop() {
        clip.stop();
    }
    
    public void loop() {
        if( (isMusic && musicPlayed)||(!isMusic && soundPlayed)){
            clip.loop();   
            if(isMusic){
                current = this;
            }
        }
    }
        
}
