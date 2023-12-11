
package eiu.cit.netprog;

import java.io.*;
import java.net.*;

public class ServerTTTWait extends Thread {

    private final static int PORT = 10;

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                Socket connection = server.accept();
                System.out.println("Connection accepted");
                Thread clientThread = new TicTacToeServerMultithreadingWait(connection);
                clientThread.start();
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
