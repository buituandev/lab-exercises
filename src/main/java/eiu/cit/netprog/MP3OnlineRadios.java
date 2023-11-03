/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eiu.cit.netprog;

import java.io.*;
import java.net.*;
import javazoom.jl.player.Player;

/**
 *
 */
public class MP3OnlineRadios {

    public static void main(String[] args) {
        try {
            URL url = new URL("http://ice10.outlaw.fm/KIEV2");
            InputStream inputStream = url.openStream();

                inputStream = new BufferedInputStream(inputStream);
                Player mp3Player = new Player(inputStream);
                mp3Player.play();
            } catch (Exception ex) {
                System.out.println("Error occured during playback process:" + ex.getMessage());
            }
    }
}
