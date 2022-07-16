package com.prog2.uwugroup;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NewServer extends Application {

    private ServerSocket serverSocket;

    public NewServer(ServerSocket serverSocket){

        this.serverSocket = serverSocket;
    }
    public NewServer(){

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
        launch(args);
        ServerSocket serverSocket = new ServerSocket(8080); //listening for clients on 8080
        //TODO: REMOVE NAKED NUMBER!
        NewServer server = new NewServer(serverSocket);
        server.startServer();
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param stage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception if something goes wrong
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(NewServer.class.getResource("Server.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 580);
        stage.setTitle("UWU GRUPPE");
        stage.setScene(scene);
        stage.show();
    }
}
