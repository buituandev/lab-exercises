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
public class DaytimeClient {
    
    public static final String SERVER = "localhost";
    
    public static final int PORT = 13;
    
    public static final int TIMEOUT = 15000;
    
    public static void main(String[] args) throws InterruptedException {
        Socket socket = null;
        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            try {
                socket = new Socket(SERVER, PORT);
                socket.setSoTimeout(TIMEOUT);

                //Input
                InputStream in = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                int c;
                
                while ((c = in.read()) != -1) {
                    System.out.print((char) c);
                }
                
            } catch (IOException ex) {
                System.err.println(ex);
            } finally { // dispose
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException ex) {
                        // ignore
                    }
                }
            }
            Thread.sleep(1000);
        }
    }
}
