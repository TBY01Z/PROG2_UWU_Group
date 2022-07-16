package com.prog2.uwugroup;

import com.prog2.uwugroup.Paket.FileHandler;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class NewClient {

    //    private TextArea messages = new TextArea();
    private int messageID = 0;
    private CreateClientUIControl gui;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;

    public NewClient(Socket socket, String username) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = username;
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void sendMessage(String message, CreateClientUIControl createClientUIControl) {
        try {
            if (messageID == 0) {
                gui = createClientUIControl;
                bufferedWriter.write(username); // TODO: 16.07.2022 ersten drei Zeilen von sendFile und sendMessage zusammen ausführen
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            String messageToSend = message;
            bufferedWriter.write(messageToSend);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            messageID ++;
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void sendFile(String msg) {

        try {

            FileHandler fileHandler = new FileHandler();
            bufferedWriter.write(username); // TODO: 16.07.2022 ersten drei Zeilen von sendFile und sendMessage zusammen ausführen
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String address = scanner.nextLine();
                String fileName = scanner.nextLine();
                bufferedWriter.write(fileHandler.fileToString(address, fileName));
                bufferedWriter.newLine();
                bufferedWriter.flush();
                System.out.println("sent");
            }
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }




//        try {
//                bufferedWriter.write(msg);
//                bufferedWriter.newLine();
//                bufferedWriter.flush();
//                System.out.println("sent");
//        } catch (IOException e) {
//            closeEverything(socket, bufferedReader, bufferedWriter);
//        }
    }

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
