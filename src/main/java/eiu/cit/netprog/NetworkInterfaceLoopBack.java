/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eiu.cit.netprog;

import java.net.*;
import java.util.*;

/**
 *
 */
public class NetworkInterfaceLoopBack {

    public static void main(String[] args) {
        try {
            InetAddress local = InetAddress.getByName("127.0.0.1");
            NetworkInterface ni = NetworkInterface.getByInetAddress(local);
            if (ni == null) {
                System.err.println("That's weird. No local loopback address.");
            } else {
                System.out.println(ni);
            }

            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements()) {
                NetworkInterface ni2 = interfaces.nextElement();
                System.out.println(ni2);
            }

        } catch (SocketException e) {
            System.err.println("Could not list network interface.");
        } catch (UnknownHostException E) {
            System.err.println("That's weird. Could not lookup 127.0.0.1.");
        }
    }
}
