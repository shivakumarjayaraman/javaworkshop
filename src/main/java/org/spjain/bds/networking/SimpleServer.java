package org.spjain.bds.networking;

import java.io.*;
import java.net.*;

/**
 * The simplest possible TCP server.
 *
 * How it works:
 *   ServerSocket  -> binds to a port and listens for incoming connections (like a phone line)
 *   accept()      -> BLOCKS until a client connects; returns a Socket for that client
 *   Socket        -> a two-way communication channel (has an InputStream and an OutputStream)
 *
 * Python analogy:
 *   s = socket.socket()
 *   s.bind(('', PORT))
 *   s.listen()
 *   conn, addr = s.accept()   <-- same idea as serverSocket.accept()
 *
 * Run this FIRST, then run SimpleClient in a separate run configuration.
 * Set a breakpoint on the accept() line to see it block until the client connects.
 */
public class SimpleServer {

    private static final int PORT = 9090;

    // Bind to a port, wait for one client, read one message, echo it back, shut down.
    private static void startServer() throws IOException {
        System.out.println("Server starting on port " + PORT + "...");

        // ServerSocket binds to the port. try-with-resources closes it when done.
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Waiting for a client to connect...");

            // accept() BLOCKS here — nothing happens until a client connects.
            // When a client connects, it returns a Socket representing that connection.
            try (Socket clientSocket = serverSocket.accept()) {
                System.out.println("Client connected from: " + clientSocket.getInetAddress());

                // Wrap the socket's streams exactly like we did with file streams in the IO class.
                // getInputStream()  -> bytes coming IN from the client
                // getOutputStream() -> bytes going OUT to the client
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); // true = auto-flush

                // Read one line the client sent us
                String message = in.readLine();
                System.out.println("Received from client: " + message);

                // Echo it back with a prefix
                out.println("ECHO: " + message);
                System.out.println("Sent echo back. Closing connection.");
            }
        }

        System.out.println("Server shut down.");
    }

    public static void main(String[] args) throws IOException {
        // --- 1. Start the server: binds port, accepts one client, echoes one message ---
        startServer();
    }
}
