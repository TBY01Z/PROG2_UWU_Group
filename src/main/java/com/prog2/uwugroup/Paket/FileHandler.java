package com.prog2.uwugroup.Paket;


import java.io.*;

public class FileHandler {


    public String fileToString(String address, String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(address));
        StringBuilder data = new StringBuilder();
        String ls = System.getProperty("line.separator");
        data.append("start");
        data.append(ls);
        data.append(fileName);
        data.append(ls);

        String line = "";


        while ((line = reader.readLine()) != null) {
            data.append(line);
            data.append(ls);
        }

        reader.close();

        data.append("end");

        return data.toString();
    }

    public void stringToFile(String fileName, String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(data);
        writer.flush();
        writer.close();
    }



}
