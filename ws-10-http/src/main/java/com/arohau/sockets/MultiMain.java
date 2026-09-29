package com.arohau.sockets;

import java.io.IOException;

import static com.arohau.sockets.EchoMultiServer.STOP_SERVER;

public class MultiMain {
    public static void main(String[] args) throws IOException {
        client_1();
        client_2();
        client_stop_server_request();
    }

    private static void client_1() throws IOException {
        EchoClient client1 = new EchoClient();
        client1.startConnection("127.0.0.1", 4444);
        String msg1 = client1.sendMessage("hello");
        String msg2 = client1.sendMessage("world");
        String terminate = client1.sendMessage(".");

        System.out.println(msg1 + " == hello");
        System.out.println(msg2 + " == world");
        System.out.println(terminate + " == bye");
    }

    private static void client_2() throws IOException {
        EchoClient client2 = new EchoClient();
        client2.startConnection("127.0.0.1", 4444);
        String msg1 = client2.sendMessage("hello");
        String msg2 = client2.sendMessage("world");
        String terminate = client2.sendMessage(".");

        System.out.println(msg1 + " == hello");
        System.out.println(msg2 + " == world");
        System.out.println(terminate + " == bye");
    }

    private static void client_stop_server_request() throws IOException {
        EchoClient client_stop = new EchoClient();
        client_stop.startConnection("127.0.0.1", 4444);
        String msg1 = client_stop.sendMessage(STOP_SERVER);

        System.out.println(msg1);
    }
}
