//AudioFilePlayer.java
/*Code researched and written by John Brosnan who sent it on for use with the audio aspects of the project
  It uses some of the JavaFX classes and contains its own driver to show how it might be used. The GUI contains
  2 JButtons, one plays an audio file while the other generates a random number between 1 and 1000*/

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.*;

//referenced from John Brosnan's X:/lab/Structured Programming 2 2018/AudioPlayerStuff

/*You pass in the path to the audio file you wish to play as a String to the static method playAudio()
  and just call AudioFilePlayer.playAudio() as necessary from whichever class you need to use it from in your
  project*/

public class AudioFilePlayer extends JFrame{

    static MediaPlayer mediaPlayer; //need to declare this reference globally to prevent garbage collector
    //from removing it prematurely when play() is called in the playAudio()
    //method, which would stop playing the clip after a few seconds



    public static void playAudio(String path)
    {
        //create a new Media object using the file path specified in the call to playAudio
        //this will become the audio clip object we wish to play

        Media audioClip = new Media(new File(path).toURI().toString());

        //create a new MediaPlayer object that will be used to play the audio clip

        mediaPlayer = new MediaPlayer(audioClip);

        try
        {
            mediaPlayer.play();
        }
        catch(Exception e)
        {
            System.out.println("The audio file " + path + " could not be played!");
        }

    }

}