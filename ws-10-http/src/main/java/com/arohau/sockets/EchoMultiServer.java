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
        while (serverSocket != null) {
            System.out.println("1");
            Socket clientSocket = serverSocket.accept();
            new EchoClientHandler(clientSocket).start();
            System.out.println("2");
        }
    }

    public static void stop() throws IOException {
        closingServerSocket.close();
    }

    private static class EchoClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public EchoClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    if (STOP_SERVER.equals(inputLine)) {
                        out.println("STOPPING_SERVER");
                        closingServerSocket = serverSocket;
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
                System.out.println("Processed");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
