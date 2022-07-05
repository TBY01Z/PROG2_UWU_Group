package com.prog2.uwugroup.packets;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class FilePacket implements Serializable {
    private static final long serialVersionUID = 1l;
    public int id;
    private File file;
    private String path;

    public FilePacket(File file) {
        this.file = file;
        this.path = file.getPath();
    }
    public void download(){
        try {
            file.createNewFile();
            file.getPath();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
