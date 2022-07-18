package com.prog2.uwugroup;

import com.prog2.uwugroup.Packet.FileHandler;

import java.io.*;
import java.net.Socket;

/**
 * Klasse zur die Erstellung eines Client Objekts. Ein Client ist ein Nutzer, der sich mit dem Server
 * fuers Chatten verbinden will.
 * @author Niclas Rieckers & Mark Fischer
 */
public class Client {
    private int messageID = 0;
    private CreateClientUIControl gui;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;

    /**
     * Konstruktor fuer Client
     * @param socket das zu initialisierende Objekt Socket
     * @param username der Benutzername des Clients wird uebergeben
     */
    public Client(Socket socket, String username) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = username;
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    /**
     * Methode fuer das senden von Nachrichten
     * @param message die zu sendende Nachricht als String
     * @param createClientUIControl GUI Controller Klasse
     */
    public void sendMessage(String message, CreateClientUIControl createClientUIControl) {
        try {
            if (messageID == 0) {
                gui = createClientUIControl;
                bufferedWriter.write(username);
//                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            String messageToSend = message;
            bufferedWriter.write(messageToSend);
//            bufferedWriter.newLine();
            bufferedWriter.flush();
            messageID ++;
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    /**
     * Methode fuer das Senden einer Datei
     * @param file die zu sendende Datei als File-Objekt
     */
    public void sendFile(File file) {

        try {

            FileHandler fileHandler = new FileHandler();
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            String address = file.getAbsolutePath();
            String fileName = file.getName();

            bufferedWriter.write(fileHandler.fileToString(address, fileName));
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    /**
     * Listener Methode, die mit einem BufferedReader die Socket nach einkommenden
     * Textnachrichten oder Dateien abhoert, und diese den Empfaenger-Client
     * empfangen laesst.
     */
    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromChat;
                while (socket.isConnected()) {
                    try {
                        msgFromChat = bufferedReader.readLine();


                        StringBuilder data = new StringBuilder();
                        String line = "";
                        if (msgFromChat.equals("start")) {

                            String fileName = bufferedReader.readLine();
                            System.out.println(fileName);

                            while (!(line = bufferedReader.readLine()).equals("end")) {
                                data.append(line);
                                String ls = System.getProperty("line.separator");
                                data.append(ls);

                            }
                            FileHandler fileHandler = new FileHandler();
                            fileHandler.stringToFile(fileName, data.toString());
                            System.out.println("imported");




                        } else {

                            gui.appendChat(msgFromChat);
                        }


                    } catch (IOException e) {
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }

    /**
     * "Kill switch" Methode fuer das direkte beenden von Buffered- Reader und Writer
     * sowie das schliessen der Socket.
     * @param socket das zu schliessende Socket-Objekt
     * @param bufferedReader das zu schliessende BufferedReader-Objekt
     * @param bufferedWriter das zu schliessende BufferedWriter-Objekt
     */
    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
