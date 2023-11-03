package eiu.cit.netprog;

import java.io.*;
import java.net.*;

public class DownloadFile {

    public static void main(String[] args) {
        try {
            URL url = new URL("https://file-examples.com/storage/fe1134defc6538ed39b8efa/2017/11/file_example_MP3_700KB.mp3");
            InputStream in = url.openStream();
            String name = "E:\\Tuan For Study Data\\CSE\\CSE 306\\lab-exercises\\src\\main\\resources\\music.mp3";
            OutputStream writer = new FileOutputStream(name);
            BufferedInputStream bis = new BufferedInputStream(in);
            BufferedOutputStream bos = new BufferedOutputStream(writer);
            int c;
            while ((c = bis.read()) != -1) {
                bos.write(c);
            }
            bos.flush();
            bos.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
