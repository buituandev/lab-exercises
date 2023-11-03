package eiu.cit.netprog;

import java.io.*;
import javax.sound.sampled.*;

public class PlayAudio {

    public static void main(String[] args) {
        try {
            String name = "E:\\Tuan For Study Data\\CSE\\CSE 306\\lab-exercises\\src\\main\\resources\\Audio\\sample1.wav";
            File file = new File(name);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            Thread.sleep(clip.getMicrosecondLength() / 1000);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
