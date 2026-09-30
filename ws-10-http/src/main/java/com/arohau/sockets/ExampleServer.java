package com.arohau.sockets;

import java.io.*;
import java.net.*;

public class ExampleServer {
    public static void main(String[] args) throws IOException {
        // Init Server, listen to port 8080
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Server is runnung, waiting for connections...");

        // Wait client (blocking method)
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client is connected!");

        // Client input stream data
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("Client is sending: " + in.readLine());

        // Close resources
        clientSocket.close();
        serverSocket.close();
    }
}
