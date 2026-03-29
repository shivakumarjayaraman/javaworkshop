package org.spjain.bds.networking;

import java.io.*;
import java.net.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A client for DateServer.
 *
 * Sends "hello" and prints the date/time the server replies with.
 * Also prints the local time BEFORE and AFTER the call so students can
 * clearly see how long they were blocked waiting for the server.
 *
 * Demo tip:
 *   Run two instances of this client at the same time while the server is
 *   sleeping inside its SIMULATED_WORK_MS delay.  The second client will
 *   visibly stall until the first one finishes — demonstrating why a real
 *   server needs threads.
 */
public class DateClient {

    private static final String HOST = "localhost";
    private static final int PORT = 9091;

    public static void main(String[] args) throws IOException {
        DateTimeFormatter hms = DateTimeFormatter.ofPattern("HH:mm:ss");

        System.out.println("Connecting to DateServer at " + HOST + ":" + PORT + "...");
        System.out.println("Local time before call: " + LocalTime.now().format(hms));

        try (Socket socket = new Socket(HOST, PORT)) {
            System.out.println("Connected!");

            PrintWriter  out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in  = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            // Send a greeting
            out.println("hello");
            System.out.println("Sent: \"hello\" — waiting for server reply...");

            // readLine() blocks until the server writes back
            String reply = in.readLine();
            System.out.println("Server replied: " + reply);
        }

        System.out.println("Local time after call:  " + LocalTime.now().format(hms));
        System.out.println("Connection closed.");
    }
}
