package com.prog2.uwugroup;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NewServer {

    private ServerSocket serverSocket;

    public NewServer(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }

    public void startServer(){
        try{
            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("A new Client joined the party!");
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        }catch(IOException e){

        }
    }

    public void closeServer(){
        try{
            if(serverSocket != null){
                serverSocket.close();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080); //listening for clients on 8080
        //TODO: REMOVE NAKED NUMBER!
        NewServer server = new NewServer(serverSocket);
        server.startServer();
    }

}
