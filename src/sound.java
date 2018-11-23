//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.JFrame;

//referenced from John Brosnan's X:/lab/Structured Programming 2 2018/AudioPlayerStuff

public class sound extends JFrame/* implements ActionListener */{

    static MediaPlayer mediaPlayer;



    public static void playAudio(String path) {
        Media audioClip = new Media((new File(path)).toURI().toString());
        mediaPlayer = new MediaPlayer(audioClip);

        try {
            mediaPlayer.play();
        } catch (Exception var3) {
            System.out.println("The audio file " + path + " could not be played!");
        }
    }
}
