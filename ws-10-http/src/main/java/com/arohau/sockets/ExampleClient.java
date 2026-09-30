package com.arohau.sockets;

import java.io.*;
import java.net.*;

public class ExampleClient {
    public static void main(String[] args) throws IOException {
        // Connect to Server on local PC (localhost) port 8080
        Socket socket = new Socket("localhost", 8080);

        // Output stream with data for Server
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("Hello, Server!");

        // Close socket
        socket.close();
    }
}
