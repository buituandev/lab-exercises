/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eiu.cit.netprog;

import java.net.*;

/**
 *
 */
public class OReillyByName {

    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName("www.eiu.edu.vn");
            System.out.println(address);
        } catch (UnknownHostException e) {
            System.err.println("Could not find www.oreilly.com");
        }
    }
}
