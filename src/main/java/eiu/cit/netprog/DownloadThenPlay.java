/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eiu.cit.netprog;

import java.io.*;
import java.net.*;
import javax.sound.sampled.*;

/**
 *
 */
public class DownloadThenPlay {

    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.tanbinhtech.com:8443/sample10.wav");
            String name = "E:\\Tuan For Study Data\\CSE\\CSE 306\\lab-exercises\\src\\main\\resources\\Audio\\sample10.wav";
            File file = new File(name);
            
            //Download
            InputStream reader = url.openStream();
            OutputStream writer = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(reader);
            BufferedOutputStream bos = new BufferedOutputStream(writer);
            int c;
            while ((c = bis.read()) != -1) {
                bos.write(c);
            }
            bos.flush();
            bos.close();
            
            //Play
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
