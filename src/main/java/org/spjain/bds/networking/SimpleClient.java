package org.spjain.bds.networking;

import java.io.*;
import java.net.*;

/**
 * The simplest possible TCP client.
 *
 * How it works:
 *   new Socket(host, port)  -> connects to the server at that address
 *   From there: same streams as the server — BufferedReader to read, PrintWriter to write
 *
 * Python analogy:
 *   s = socket.socket()
 *   s.connect(('localhost', PORT))   <-- same idea as new Socket(HOST, PORT)
 *
 * Run SimpleServer FIRST, then run this.
 * Set a breakpoint after the Socket constructor to inspect the connected socket object.
 */
public class SimpleClient {

    private static final String HOST = "localhost";
    private static final int PORT = 9090;

    // Connect to the server, send one message, read the reply, close.
    private static void connectAndSend(String message) throws IOException {
        System.out.println("Connecting to " + HOST + ":" + PORT + "...");

        // new Socket() immediately attempts the TCP connection.
        // This will throw ConnectException if the server is not running.
        try (Socket socket = new Socket(HOST, PORT)) {
            System.out.println("Connected!");

            // Same decorator pattern as file IO: wrap the raw stream for convenience.
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // true = auto-flush
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Send our message to the server
            System.out.println("Sending: " + message);
            out.println(message);

            // Wait for the server's reply (readLine() blocks until server writes a line)
            String reply = in.readLine();
            System.out.println("Server replied: " + reply);
        }

        System.out.println("Connection closed.");
    }

    public static void main(String[] args) throws IOException {
        // --- 1. Connect to the server and send a single message ---
        connectAndSend("Hello from the client!");
    }
}
