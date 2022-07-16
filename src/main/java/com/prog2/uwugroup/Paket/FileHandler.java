package com.prog2.uwugroup.Paket;


import java.io.*;

public class FileHandler {


    public String fileToString(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuilder data = new StringBuilder();

        String line = "";

        String ls = System.getProperty("line.separator");

        while ((line = reader.readLine()) != null) {
            data.append(line);
            data.append(ls);
        }

        reader.close();

        return data.toString();
    }

    public void stringToFile(String fileName, String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(data);
        writer.flush();
        writer.close();
    }


    public static void main(String[] args) throws IOException {
        FileHandler fileHandler = new FileHandler();
        System.out.println(fileHandler.fileToString("test.txt"));
    }

}
