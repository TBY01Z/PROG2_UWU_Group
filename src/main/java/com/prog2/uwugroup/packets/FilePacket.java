package com.prog2.uwugroup.packets;


import java.io.*;
import java.util.Scanner;

// File recived -> chose accept ->
// File send -> chose File -> bestaetigen
public class FilePacket implements Serializable {
    private static final long serialVersionUID = 1l;
    public int id;
    private String path = System.getProperty("user.dir") + "\\model.txt";
    private String inputString = "";
    private File file;
    public FilePacket(String path){
        this.setPath(path);
        file = new File(this.getPath());
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                inputString = inputString + scanner.nextLine();
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }


    private String[] getInputString() {
        return inputString.split("\n\t\r");
    }

    public void setInputString(String inputString) {
        this.inputString = inputString;
    }
    public void download(String path){
        try {
            FileOutputStream outputStream = new FileOutputStream(path);
            outputStream.write(inputString.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
