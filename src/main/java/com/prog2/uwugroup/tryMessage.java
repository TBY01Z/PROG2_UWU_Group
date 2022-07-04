package com.prog2.uwugroup;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class tryMessage {
    public static void main(String[] args) throws Exception {
        // create socket
        int port = 4444;
        ServerSocket serverSocket = new ServerSocket(port);
        System.err.println("Started server on port " + port);

        // repeatedly wait for connections, and process
        while (true) {
            // a "blocking" call which waits until a connection is requested
            Socket clientSocket = serverSocket.accept();
            System.err.println("Accepted connection from client");

            // open up IO streams
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
            // waits for data and reads it in until connection dies
            // readLine() blocks until the server receives a new line from
            // client
            String s;
            try {
                while ((s = in.readLine()) != null) {
                    out.println(s);
                    out.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // close IO streams, then socket
            System.err.println("Closing connection with client");
            out.close();
            in.close();
            clientSocket.close();
        }
    }
}
