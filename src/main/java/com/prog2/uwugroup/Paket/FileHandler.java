package com.prog2.uwugroup.Paket;


import java.io.*;

/**
 * Der FileHandler wandelt Files in Strings um und umgekehrt.
 */
public class FileHandler {


    /**
     * Diese Methode wandelt eine Datei, von einem angegebenem Ort, in ein String um.
     *
     * @param address Der Ort, an dem die Datei gespeichert ist.
     * @param fileName Der Name der Datei.
     * @return Zurückgegeben wird der Inhalt der Datei als String.
     * @throws IOException
     */

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

    /**
     * Diese Methode schreibt den String in eine Datei ab.
     *
     * @param fileName Der Dateiname.
     * @param data Der zuspeichernde Inhalt.
     * @throws IOException
     */
    public void stringToFile(String fileName, String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(data);
        writer.flush();
        writer.close();
    }



}
