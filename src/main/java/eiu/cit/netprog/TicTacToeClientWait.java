package eiu.cit.netprog;

import java.net.*;
import java.io.*;
import java.text.*;

public class TicTacToeClientWait {

    public static void main(String[] args) throws ParseException {
        String hostname = "localhost";
        Socket socket = null;
        String encodedBoard = null;
        String strategy = null;
        while (true) {
            try {
                socket = new Socket(hostname, 10);
                socket.setSoTimeout(15000);
                System.out.println("Connected to server");

                InputStream in = socket.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                BufferedReader bif = new BufferedReader(reader);

                OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
                BufferedWriter bout = new BufferedWriter(out);

                BufferedReader terminal = new BufferedReader(new InputStreamReader(System.in));
                if (strategy == null) {
                    strategy = terminal.readLine();
                }
                bout.write(strategy + "\r\n");
                bout.flush();

                String move = terminal.readLine();

                bout.write(encodedBoard + "\r\n");
                bout.flush();
                bout.write(move + "\r\n");
                bout.flush();
                encodedBoard = readBoard(bif, encodedBoard);
                bout.write(encodedBoard + "\r\n");
                bout.flush();
                // socket.close();

            } catch (IOException ex) {
                System.err.println(ex);
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                        System.out.println("Socket closed");
                    } catch (IOException ex) {
                        System.out.println("WHY");
                    }
                }
            }
        }
    }

    static String readBoard(BufferedReader bif, String previousBoard) {
        try {
            String encodedBoard = bif.readLine();
            System.out.println(encodedBoard);
            if (encodedBoard.contains("Let's play again!") || encodedBoard.contains("I won!")
                    || encodedBoard.contains("It's a draw!")) {
                encodedBoard = null;
                return encodedBoard;
            } else if (encodedBoard.equals("Wrong input!") || encodedBoard.equals("Occupied cell!")) {
                return previousBoard;
            } else {
                return encodedBoard;

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
