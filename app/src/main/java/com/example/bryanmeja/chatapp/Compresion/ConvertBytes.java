package com.example.bryanmeja.chatapp.Compresion;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ConvertBytes {

    public static byte[] getBytes(File file) throws IOException {
        byte[] buffer = new byte[256000000];
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        FileInputStream fis = new FileInputStream(file);
        int read;

        while((read = fis.read(buffer))!= -1) {
            os.write(buffer, 0, read);
        }
        fis.close();
        os.close();
        return os.toByteArray();

    }
}
