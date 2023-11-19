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
public class TicTacToeClient {

    public static final String SERVER = "localhost";
    public static final int PORT = 13;
    public static final int TIMEOUT = 15000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER, PORT)) {
            socket.setSoTimeout(TIMEOUT);

            //Input
            Reader in = new InputStreamReader(socket.getInputStream(), "UTF-8");
            BufferedReader reader = new BufferedReader(in);

            //Output
            Writer out = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
            BufferedWriter writer = new BufferedWriter(out);

            System.out.println(reader.readLine());
            //receiveMessage(reader);

            BufferedReader terminal = new BufferedReader(new InputStreamReader(System.in));
            String move = terminal.readLine();
            while (!move.equals("quit")) {
                writer.write(move + "\r\n");
                writer.flush();
                //receiveMessage(reader);
                System.out.println(reader.readLine());
                move = terminal.readLine();
            }
            writer.write("quit" + "\r\n");
            writer.flush();
        } catch (Exception e) {

        }
    }

    private static void receiveMessage(BufferedReader reader) {
        try {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                System.out.print(line);
                System.out.println();
            }
        } catch (IOException e) {
        }
    }
}
