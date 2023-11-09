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
public class MyEliza {

    public static final String SERVER = "telehack.com";

    public static final int PORT = 23;

    public static final int TIMEOUT = 15000;

    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket(SERVER, PORT);
            socket.setSoTimeout(TIMEOUT);

            //Output
            OutputStream out = socket.getOutputStream();
            Writer writer = new OutputStreamWriter(out, "UTF-8");
            writer = new BufferedWriter(writer);

            //Input
            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            int c, prevC = -1;

            while ((c = in.read()) != -1) {
                //System.out.print((char) c);
                if (c == '.') {
                    if (prevC == '\n') {
                        break;
                    }
                }
                prevC = c;
            }

            BufferedReader terminal = new BufferedReader(new InputStreamReader(System.in));

            writer.write("eliza" + "\r\n");
            writer.flush();
            readFirst(reader);

            String line = terminal.readLine();

            while (!(line.equals("quit"))) {
                writer.write(line + "\r\n");
                writer.flush();
                readEliza(reader);
                line = terminal.readLine();
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
    }

    private static void readFirst(BufferedReader bif) {
        int c, count = 0;
        try {
            while ((c = bif.read()) != -1) {
                if (c == '\r') {
                    if (count == 2) {
                        break;
                    } else {
                        count++;
                    }
                }
                if (count == 1) {
                    System.out.print((char) c);
                }
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readEliza(BufferedReader bif) {
        int c, count = 0;
        try {
            while ((c = bif.read()) != -1) {
                if (c == '\r') {
                    if (count == 3) {
                        break;
                    } else {
                        count++;
                    }
                }
                if (count == 2) {
                    System.out.print((char) c);
                }
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
