package com.prog2.uwugroup;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hier wird der Server gesettelt als Socket.
 * @author Niclas & Mark
 */
public class Server implements Runnable {
    private ServerSocket serverSocket;

    /**
     * @param serverSocket Dies ist der Konstruktor der Klasse Server.
     */
    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    private ClientHandler clientHandler;
    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        while(!serverSocket.isClosed()){
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("A new Client joined the party!");
            clientHandler = new ClientHandler(socket);
            Thread thread1 = new Thread(clientHandler);
            thread1.start();
        }

    }

}
