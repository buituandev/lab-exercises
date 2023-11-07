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
public class LowPortScanner {

    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : "localhost";
        for (int i = 0; i < 1024; i++) {
            try {
                Socket s = new Socket(host, i);
                System.out.println("There is a server on port " + i + " of " + host);
                s.close();
            } catch (UnknownHostException ex) {
                System.err.println(ex);
                break;
            } catch (IOException ex) {
            }
        }
    }
}
