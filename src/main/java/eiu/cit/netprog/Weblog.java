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
public class Weblog {

    public static void main(String[] args) {
        String name = "E:\\Tuan For Study Data\\CSE\\CSE 306\\lab-exercises\\src\\main\\resources\\weblog.txt";
        try (FileInputStream fin = new FileInputStream(new File(name)); 
                Reader in = new InputStreamReader(fin); 
                BufferedReader bin = new BufferedReader(in);) {

            for (String entry = bin.readLine(); entry != null; entry = bin.readLine()) {
                int index = entry.indexOf(' ');
                String ip = entry.substring(0, index);
                String theRest = entry.substring(index);
                try {
                    InetAddress address = InetAddress.getByName(ip);
                    System.out.println(address.getHostName() + theRest);
                } catch (UnknownHostException e) {
                    System.err.println("Exception: " + e);
                }
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
}
