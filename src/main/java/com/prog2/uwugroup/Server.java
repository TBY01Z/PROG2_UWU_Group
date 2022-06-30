package com.prog2.uwugroup;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server implements Runnable{

    private int port;
    private boolean running;
    private Selector selector;
    private ServerSocket serverSocket;
    private ByteBuffer buffer;

    public Server(int port, int bufferSize){
        this.port = port;
        this.buffer = ByteBuffer.allocate(bufferSize);
    }

    public void start(){
        new Thread(this).start();
    }

    @Override
    public void run() {
        running = true;
        while(running){
            try {
                int client = selector.select();

                if(client == 0){
                    continue;
                }
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while(iterator.hasNext()){
                    SelectionKey key = (SelectionKey)iterator.next();
                    if((key.readyOps() & key.OP_ACCEPT) == SelectionKey.OP_ACCEPT){
                        Socket socket = serverSocket.accept();
                        System.out.print("\nVerbindung von " + socket);
                        SocketChannel socketChannel = socket.getChannel();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    }
                    else if((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ){
                        SocketChannel channel = null;
                        channel = (SocketChannel)key.channel();
                        boolean connection = readData(channel, buffer);
                        //TODO: create method readData()
                        if(!connection){
                            key.cancel();
                            Socket socket = null;
                            socket = channel.socket();
                            socket.close();
                        }
                    }
                    keys.clear();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void open(){
        ServerSocketChannel serverSocketChannel;
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocket = serverSocketChannel.socket();
            InetSocketAddress address = new InetSocketAddress(port);
            serverSocketChannel.bind(address);
            selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.print("\nServer auf Port " + port + " erstellt.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean readData(SocketChannel channel, ByteBuffer buffer){
        return true;
        //TODO: finish me!
    }

}
