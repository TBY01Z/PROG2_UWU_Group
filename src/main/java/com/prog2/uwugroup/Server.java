package com.prog2.uwugroup;

import javafx.application.Platform;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{

    private int port;
    private ServerSocket serverSocket;
    private boolean running = false;

    public Server(int port) {
        this.port = port;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void changePort(int port){
        this.port = port;
//        try {
//            shutDown();
//            serverSocket.close();
//            serverSocket = new ServerSocket(port);
//            run();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } todo fix me
    }


    public void start(){
        new Thread(this).start();
    }

    @Override
    public void run() {
        running = true;

        while(running){
            try{
                Socket socket = serverSocket.accept();
                initSocket(socket);
                //Platform.runLater(()-> {});
            } catch(IOException e){
                e.printStackTrace();
            }
        }
        shutDown();
    }

    private void initSocket(Socket socket){
        Connection connection = new Connection(socket);
        new Thread(connection).start();
    }

    public void shutDown(){
        running = false;
        try{
            serverSocket.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
