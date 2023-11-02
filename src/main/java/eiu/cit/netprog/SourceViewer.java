package eiu.cit.netprog;

import java.io.*;
import java.net.*;

public class SourceViewer {

    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.tanbinhtech.com/july.webp");
            InputStream in = url.openStream();
            int c;
            while ((c = in.read()) != -1) {
                System.out.write((char) c);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
