//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

//referenced from John Brosnan's X:/lab/Structured Programming 2 2018/AudioPlayerStuff

public class sound extends JFrame implements ActionListener {
    JButton audioButton;
    static MediaPlayer mediaPlayer;
    String audioFile1 = "src/gunshot.wav";
    String audioFile2 = "src/beep.mp3";

    public static void main(String[] args) {
        new AudioFilePlayer();
        new JFXPanel();
    }

    public sound() {
        super("AudioFilePlayer Tester");
        FlowLayout flowLayout = new FlowLayout();
        this.setLayout(flowLayout);
        this.audioButton = new JButton("Play");
        this.add(this.audioButton);
        JButton randomButton = new JButton("Generate Random Number");
        this.add(randomButton);
        this.audioButton.addActionListener(this);
        randomButton.addActionListener(this);
        this.setSize(300, 200);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
    }

    public static void playAudio(String path) {
        Media audioClip = new Media((new File(path)).toURI().toString());
        mediaPlayer = new MediaPlayer(audioClip);

        try {
            mediaPlayer.play();
        } catch (Exception var3) {
            System.out.println("The audio file " + path + " could not be played!");
        }

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.audioButton) {
            playAudio(this.audioFile1);
        } else {
            JOptionPane.showMessageDialog((Component)null, "The randomly generated number was " + (int)(Math.random() * 1000.0D + 1.0D));
        }

    }
}
