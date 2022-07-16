package com.prog2.uwugroup;

import com.prog2.uwugroup.Paket.FileHandler;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class NewClient {

    //    private TextArea messages = new TextArea();
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

    public void sendMessage(String message) {
        try {
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            while (socket.isConnected()) {
                String messageToSend = message;
                bufferedWriter.write(username + ": " + messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void sendMessage2(String msg) {
        try {
                bufferedWriter.write(msg);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                System.out.println("sent");
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
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
                            System.out.println(msgFromChat);
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
