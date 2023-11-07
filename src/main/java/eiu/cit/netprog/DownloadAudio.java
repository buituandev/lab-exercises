/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eiu.cit.netprog;

import java.io.*;
import java.net.*;

/**
 *
 */
public class DownloadAudio {

    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.tanbinhtech.com:8443/sample10.wav");
            InputStream in = url.openStream();
            String name = "E:\\Tuan For Study Data\\CSE\\CSE 306\\lab-exercises\\src\\main\\resources\\Audio\\sample10.wav";
            OutputStream writer = new FileOutputStream(name);
            BufferedInputStream bis = new BufferedInputStream(in);
            BufferedOutputStream bos = new BufferedOutputStream(writer);
            int c;
            while ((c = bis.read()) != -1) {
                bos.write(c);
            }
            bos.flush();
            bos.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}