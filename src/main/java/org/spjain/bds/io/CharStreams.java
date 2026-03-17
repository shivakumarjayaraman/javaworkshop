package org.spjain.bds.io;

import java.io.*;

/**
 * Demonstrates character-level IO for text files.
 *
 * Problem with byte streams for text: a single character can be 1, 2, or 3 bytes
 * depending on the encoding (UTF-8, etc.). FileReader/FileWriter handle this for you.
 *
 * The decorator pattern in action:
 *   FileWriter         -> writes characters to a file
 *   BufferedWriter     -> wraps FileWriter, adds an in-memory buffer (fewer disk hits)
 *   PrintWriter        -> wraps BufferedWriter, adds println / printf convenience
 *
 *   FileReader         -> reads characters from a file
 *   BufferedReader     -> wraps FileReader, lets you read a whole line at once
 */
public class CharStreams {

    private static final String FILE_PATH = "/tmp/chars_demo.txt";

    // Write text to a file using FileWriter directly
    // Every write() call goes to disk immediately — inefficient for many small writes
    private static void writeWithFileWriter(String path) throws IOException {
        try (FileWriter fw = new FileWriter(path)) {
            fw.write("Line 1: Hello\n");
            fw.write("Line 2: World\n");
            fw.write("Line 3: Java IO\n");
        }
        System.out.println("Wrote file with FileWriter: " + path);
    }

    // Read the file character by character using FileReader
    // Rarely done in practice, but shows how the underlying stream works
    private static void readCharByChar(String path) throws IOException {
        System.out.println("\nReading char by char:");
        try (FileReader fr = new FileReader(path)) {
            int ch;
            while ((ch = fr.read()) != -1) {   // read() returns an int, -1 means EOF
                System.out.print((char) ch);    // cast int back to char
            }
        }
    }

    // Wrap FileWriter in BufferedWriter + PrintWriter for convenient line writing
    // BufferedWriter holds writes in memory and flushes in bigger chunks -> faster
    private static void writeWithBufferedWriter(String path) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write("Name,Age,City");
            bw.newLine();                       // OS-correct line separator
            bw.write("Alice,30,New York");
            bw.newLine();
            bw.write("Bob,25,London");
            bw.newLine();
            bw.write("Charlie,35,Tokyo");
            bw.newLine();
        }
        System.out.println("Wrote CSV with BufferedWriter: " + path);
    }

    // BufferedReader lets you read an entire line at a time — very common in practice
    private static void readLineByLine(String path) throws IOException {
        System.out.println("\nReading line by line:");
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {   // readLine() returns null at EOF
                System.out.println(lineNumber + ": " + line);
                lineNumber++;
            }
        }
    }

    // Parse CSV data from the file line by line
    private static void parseCsv(String path) throws IOException {
        System.out.println("\nParsing CSV:");
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String header = br.readLine();    // skip the header line
            System.out.println("Header: " + header);

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                int age = Integer.parseInt(parts[1]);
                String city = parts[2];
                System.out.println(name + " is " + age + " years old and lives in " + city);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // --- 1. FileWriter: simple but writes directly to disk each call ---
        writeWithFileWriter(FILE_PATH);
        readCharByChar(FILE_PATH);

        // --- 2. BufferedWriter: batches writes, and lets you use newLine() ---
        writeWithBufferedWriter(FILE_PATH);
        readLineByLine(FILE_PATH);

        // --- 3. Practical example: parse CSV line by line ---
        parseCsv(FILE_PATH);
    }
}
