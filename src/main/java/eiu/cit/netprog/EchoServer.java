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
public class EchoServer {

    public final static int PORT = 13;

    public static void main(String[] args) {
        try (ServerSocket socket = new ServerSocket(PORT)) {
            while (true) {
                try (Socket connection = socket.accept()) {
                    //Output
                    Writer writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
                    writer = new BufferedWriter(writer);

                    //Input
                    Reader in = new InputStreamReader(connection.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(in);

                    for (String line = reader.readLine(); !line.equals("quit"); line = reader.readLine()) {
                        writer.write(line + "\r\n");
                        writer.flush();
                    }
                    connection.close();
                    socket.close();
                    break;
                } catch (Exception e) {
                }
            }
        } catch (IOException ex) {

        }
    }
}
