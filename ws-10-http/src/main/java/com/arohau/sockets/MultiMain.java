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
        
        String resp1 = client1.sendMessage("hello");
        System.out.println("(CLIENT SIDE) SERVER: " + resp1);

        String resp2 = client1.sendMessage("world");
        System.out.println("(CLIENT SIDE) SERVER: " + resp2);

        String resp3 = client1.sendMessage("!");
        System.out.println("(CLIENT SIDE) SERVER: " + resp3);
        
        String resp4 = client1.sendMessage(".");
        System.out.println("(CLIENT SIDE) SERVER: " + resp4);
    }

    private static void client_2() throws IOException {
        EchoClient client2 = new EchoClient();
        client2.startConnection("127.0.0.1", 4444);
    }

    private static void client_stop_server_request() throws IOException {
        EchoClient client_stop = new EchoClient();
        client_stop.startConnection("127.0.0.1", 4444);
        String msg1 = client_stop.sendMessage(STOP_SERVER);

        System.out.println(msg1);
    }
}
