package com.arohau.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoMultiServer {
    public static final String STOP_SERVER = "STOP_SERVER";
    private static ServerSocket serverSocket;
    private static ServerSocket closingServerSocket;

    public static void main(String[] args) throws IOException {
        EchoMultiServer server = new EchoMultiServer();
        server.start(4444);
        EchoMultiServer.stop();
    }

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        int id = 0;
        while (serverSocket != null) {
            System.out.println("SERVER: Listening for the socket.");
            Socket clientSocket = serverSocket.accept();
            id++;
            new EchoClientHandler(id, clientSocket).start();
            System.out.println("SERVER: Started processing socket connection in separate with thread id = " + id);
        }
    }

    public static void stop() throws IOException {
        closingServerSocket.close();
    }

    private static class EchoClientHandler extends Thread {
        private int id;
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public EchoClientHandler(int id, Socket socket) {
            this.id = id;
            this.clientSocket = socket;
        }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("(SERVER SIDE " + id + ") CLIENT: " + inputLine);
                    if (STOP_SERVER.equals(inputLine)) {
                        out.println("STOPPING_SERVER: id=" + id);
                        closingServerSocket = serverSocket; // never let proceed WHILE in start()
                        serverSocket = null;
                        break;
                    }
                    if (".".equals(inputLine)) {
                        out.println("bye");
                        break;
                    }
                    out.println(inputLine);
                }

                in.close();
                out.close();
                clientSocket.close();
                System.out.println("SERVER (" + id + "): Processed and closed socket session.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
