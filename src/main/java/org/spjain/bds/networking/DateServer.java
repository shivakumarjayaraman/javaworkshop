package org.spjain.bds.networking;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An iterative (single-threaded) date server.
 *
 * "Iterative" means the server handles one client at a time, in a loop.
 * While it is busy with client A, client B is stuck waiting in the OS accept queue.
 *
 * How it works:
 *   1. ServerSocket binds to PORT and starts listening.
 *   2. The outer while-loop calls accept() again after every client, so the server
 *      never shuts down — it just keeps accepting new connections.
 *   3. For each connected client it reads one line, sleeps to simulate work,
 *      then replies with the current date/time.
 *
 * Demo script:
 *   - Start this server.
 *   - Connect with DateClient #1.  Notice it gets a reply after ~3 seconds.
 *   - Start DateClient #1 again, and while it is sleeping, start DateClient #2
 *     in a second terminal.  DateClient #2 will be BLOCKED until #1 is fully done.
 *   - This is the problem that concurrent (threaded) servers solve.
 *
 * Python analogy for the loop:
 *   while True:
 *       conn, addr = s.accept()
 *       data = conn.recv(1024)
 *       conn.sendall(reply)
 *       conn.close()
 */
public class DateServer {

    private static final int PORT = 9091;

    // Simulated processing delay (milliseconds).
    // Increase this to make the "blocking" effect more dramatic during the demo.
    private static final int SIMULATED_WORK_MS = 3000;

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws IOException {
        System.out.println("DateServer starting on port " + PORT + "...");
        System.out.println("(Simulated work delay: " + SIMULATED_WORK_MS + " ms per request)");

        // ServerSocket is created ONCE outside the loop.
        // It keeps listening even as clients come and go.
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            // --- The accept loop ---
            // This is what makes the server "iterative": one client at a time, forever.
            while (true) {
                System.out.println("\nWaiting for next client...");

                // accept() BLOCKS until a client connects.
                // Any other clients connecting right now are queued by the OS.
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Client connected from: " + clientSocket.getInetAddress());

                    BufferedReader in  = new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter  out   = new PrintWriter(
                            clientSocket.getOutputStream(), true); // auto-flush

                    // Read the client's greeting (e.g. "hello")
                    String message = in.readLine();
                    System.out.println("Received: \"" + message + "\"");

                    // Simulate a slow operation (e.g. a database lookup, heavy computation).
                    // While we sleep here, any other client that connects will be WAITING.
                    System.out.println("Processing request... (sleeping " + SIMULATED_WORK_MS + " ms)");
                    try {
                        Thread.sleep(SIMULATED_WORK_MS);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    // Build and send the response
                    String now = LocalDateTime.now().format(FORMATTER);
                    out.println("Current date/time: " + now);
                    System.out.println("Replied with: " + now);

                } // clientSocket is closed here; the loop goes back to accept()
            }
        }
    }
}
