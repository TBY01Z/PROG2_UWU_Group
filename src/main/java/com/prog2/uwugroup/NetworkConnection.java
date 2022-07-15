package com.prog2.uwugroup;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public abstract class NetworkConnection {
    private ConnectionThread connectionThread = new ConnectionThread();
    private Consumer<Serializable> onReceiveCallback;

    public NetworkConnection(Consumer<Serializable> onReceiveCallback){
        this.onReceiveCallback = onReceiveCallback;
        connectionThread.setDaemon(true);
    }

    public void startConnection() throws Exception{
//        new Thread(connectionThread).start();
        connectionThread.start();
    }

    public void send(Serializable data) throws Exception{
        connectionThread.outStream.writeObject(data);
    }

    public void closeConnection() throws Exception{
        connectionThread.socket.close();
    }

    protected abstract boolean isServer();
    protected abstract String getIP();
    protected abstract int getPort();

    private class ConnectionThread extends Thread{
        private Socket socket;
        private ObjectInputStream inStream;
        private ObjectOutputStream outStream;

        @Override
        public void run(){
            try(
                ServerSocket server = isServer() ? new ServerSocket(getPort()) : null;//wenn ServerSocket "server" server ist, instanziiert neue ServerSocket mit Port, sonst null
                Socket socket = isServer() ? server.accept() : new Socket(getIP(), getPort()); //wenn Socket "socket" ein server ist wird verbindung akzeptiert, sonst neuer Socket mit IP und Port{       //ObjectInputStream erstellt
                ObjectOutputStream outStream = new ObjectOutputStream(this.socket.getOutputStream());
                ObjectInputStream inStream = new ObjectInputStream(this.socket.getInputStream())){

                this.socket = socket;
                this.outStream = outStream;
                this.inStream = inStream;
                socket.setTcpNoDelay(true);

                while(true){
                    Serializable data = (Serializable) inStream.readObject();
                    onReceiveCallback.accept(data);
                }

            } catch(Exception e){
                onReceiveCallback.accept("Verbindung wurde geschlossen.");
            }
        }
    }
}
