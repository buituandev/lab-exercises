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
public class EchoClient {

    public static final String SERVER = "localhost";

    public static final int PORT = 13;

    public static final int TIMEOUT = 15000;

    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket(SERVER, PORT);
            socket.setSoTimeout(TIMEOUT);

            //Output
            Writer writer = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
            writer = new BufferedWriter(writer);

            //Input
            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            BufferedReader terminal = new BufferedReader(new InputStreamReader(System.in));

            String line = terminal.readLine();
            while (!line.equals("quit")) {
                writer.write(line + "\r\n");
                writer.flush();
                readLine(reader);
                line = terminal.readLine();
            }
            writer.write("quit" + "\r\n");
            writer.flush();

        } catch (IOException ex) {
            System.err.println(ex);
        } finally { // dispose
//            if (socket != null) {
//                try {
//                    //socket.close();
//                } catch (IOException ex) {
//                    // ignore
//                }
//            }
        }
    }

    private static void readLine(BufferedReader br) {
        try {
            String echo = br.readLine();
            System.out.println(echo);
        } catch (IOException e) {

        }
    }
}
