package com.prog2.uwugroup;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * ClientHandler behandelt alle sich mit dem Server verbindenden Clients.
 * @author Niclas Rieckers & Mark Fischer
 */
public class ClientHandler implements Runnable {

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;  //read msgs
    private BufferedWriter bufferedWriter;  //write msgs
    private String clientUsername;

    /**
     * Konstruktor fuer die Klasse ClientHandler
     * @param socket uebergebenes Socket-Objekt, aus dem ein OutputStream gewonnen werden kann, und zu dem
     * ein InputStream fuehren kann. Diese werden in Character Streams Output- und InputStreamWriter gewrapped,
     * die wiederum in Buffered- Reader und Writer gewrapped werden.
     */
    public ClientHandler(Socket socket){
        try{
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); //wrapping wrapped byte stream into character stream into a buffered writer for efficiency
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));   //same shit btw^^
            this.clientUsername = bufferedReader.readLine();    //later eher so mit set methode weil interface minterface
            clientHandlers.add(this);
            broadcastMessage(clientUsername + " has joined the party!");
        } catch(IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    /**
     * Methode run hat eine quasi-unendliche while-Schleife, die die Aufgabe hat
     * mithilfe des BufferedReader die Socket nach neuen Nachrichten abzuhoeren.
     */
    @Override
    public void run() {
        String messageFromClient;

        while(socket.isConnected()){
            try{
                messageFromClient = bufferedReader.readLine();
                broadcastMessage(messageFromClient);
            } catch(IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    /**
     * Methode broadcastMessage uebertraegt eine Nachricht an alle Teilnehmer des Chats, indem sie zuerst
     * den Namen des Nutzers aus der ArrayList holt, und diesen dann gemeinsam mit der zu uebertragenden
     * Nachricht an alle anderen Benutzer sendet.
     * @param messageToSend die an alle Teilnehmer des Chats zu uebertragende Nachricht als String
     */
    public void broadcastMessage(String messageToSend){
        for(ClientHandler clientHandler : clientHandlers){
            try{
                if(!clientHandler.clientUsername.equals(clientUsername)){
                    clientHandler.bufferedWriter.write(messageToSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            } catch(IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    /**
     * Methode removeClientHandler entfernt Nutzer aus der Liste aller Nutzer/Chatter und
     * benachrichtigt die anderen Nutzer des Gruppenchats ueber den Verlust eines Chatters.
     */
    public void removeClientHandler(){
        clientHandlers.remove(this);
        broadcastMessage(clientUsername + " has left the party :(");
    }

    /**
     * "Kill switch" Methode fuer das direkte beenden von Buffered- Reader und Writer
     * sowie das schliessen der Socket.
     * @param socket das zu schliessende Socket-Objekt
     * @param bufferedReader das zu schliessende BufferedReader-Objekt
     * @param bufferedWriter das zu schliessende BufferedWriter-Objekt
     */
    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        removeClientHandler();
        try{
            if(bufferedReader != null){
                bufferedReader.close();
            }
            if(bufferedWriter != null){
                bufferedWriter.close();
            }
            if(socket != null){
                socket.close();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
