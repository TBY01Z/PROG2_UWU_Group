package com.prog2.uwugroup.packets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

// File recived -> chose accept ->
// File send -> chose File -> bestaetigen
public class FilePacket implements Serializable {
    private static final long serialVersionUID = 1l;
    public int id;
    private String path = System.getProperty("user.dir") + "\\model.txt";
    private String inputString = "";
    public FilePacket(String path){
        this.setPath(path);
        File file = new File(this.getPath());
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                inputString = inputString + scanner.nextLine();
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            File file1=new File(path);
            try {
                file1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String[] getInputString() {
        return inputString.split("\n\t\r");
    }

    public void setInputString(String inputString) {
        this.inputString = inputString;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
