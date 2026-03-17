package org.spjain.bds.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Demonstrates byte-level IO — the foundation of all Java IO.
 *
 * Key idea: everything on disk is ultimately bytes.
 * FileOutputStream writes bytes; FileInputStream reads them back.
 *
 * Python equivalent:
 *   open("file", "wb").write(...)   -> FileOutputStream
 *   open("file", "rb").read(...)    -> FileInputStream
 */
public class ByteStreams {

    private static final String FILE_PATH = "/tmp/bytes_demo.bin";

    // Write an array of bytes to a file
    private static void writeBytes(String path, byte[] data) throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        fos.write(data);
        fos.close();
        System.out.println("Wrote " + data.length + " bytes to " + path);
    }

    // Read all bytes from a file one at a time
    // read() returns -1 when there is nothing left to read
    private static byte[] readBytes(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        byte[] buffer = new byte[1024];
        int totalRead = 0;
        int b;
        while ((b = fis.read()) != -1) {  // read one byte at a time; returns -1 at EOF
            buffer[totalRead++] = (byte) b;
        }
        fis.close();

        // Trim the buffer to the actual number of bytes read
        byte[] result = new byte[totalRead];
        System.arraycopy(buffer, 0, result, 0, totalRead);
        System.out.println("Read " + totalRead + " bytes from " + path);
        return result;
    }

    // Same as above but using try-with-resources — the stream is closed automatically
    // even if an exception is thrown. This is the preferred style.
    private static void writeWithTryResources(String path, byte[] data) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(data);
        }
        System.out.println("Wrote (try-with-resources) " + data.length + " bytes to " + path);
    }

    public static void main(String[] args) throws IOException {
        // --- 1. Write some bytes and read them back ---
        byte[] message = "Hello, Java IO!".getBytes();  // String -> byte[]
        writeBytes(FILE_PATH, message);

        byte[] readBack = readBytes(FILE_PATH);
        String result = new String(readBack);             // byte[] -> String
        System.out.println("Read back: " + result);

        // --- 2. Step through this to see what a byte actually is ---
        System.out.println("\nEach byte as an integer:");
        for (byte b : message) {
            System.out.println("  char='" + (char) b + "'  byte=" + b);
        }

        // --- 3. Same write using try-with-resources (cleaner, safer) ---
        writeWithTryResources(FILE_PATH, "Overwritten content".getBytes());
    }
}
