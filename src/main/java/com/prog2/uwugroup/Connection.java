package com.prog2.uwugroup;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static java.lang.Thread.sleep;

public class Connection implements Runnable{

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private int id;

    public Connection(Socket socket){
        this.socket = socket;
        id = 0; //TODO: change later!
        try{
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try{
            while(socket.isConnected()){
                try{
                    Object data = in.readObject();

                    Listener.received(data);
                } catch(ClassNotFoundException e){
                    e.printStackTrace();
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void close(){
        try{
            in.close();
            out.close();
            socket.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void send(Object packet){
        try{
            out.writeObject(packet);
            out.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
