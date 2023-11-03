/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package eiu.cit.netprog;

import java.io.*;
import java.net.*;
import javax.sound.sampled.*;

public class DownloadAndPlay {

    private static final int BUFFER_SIZE = 4096;
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.tanbinhtech.com:8443/sample10.wav");
            InputStream inputStream = url.openStream();
            inputStream = new BufferedInputStream(inputStream);
            
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            SourceDataLine audioLine = (SourceDataLine) AudioSystem.getLine(info);
            
            audioLine.open(format);
            audioLine.start();
            
            
            byte[] bytesBuffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
            
            while ((bytesRead = audioStream.read(bytesBuffer)) != -1) {
                audioLine.write(bytesBuffer, 0, bytesRead);
            }
            
            audioLine.drain();
            audioLine.close();
            audioStream.close();
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}

