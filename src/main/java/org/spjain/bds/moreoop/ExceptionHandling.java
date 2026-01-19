package org.spjain.bds.moreoop;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExceptionHandling {
    public static void main(String[] args) {
        try {
            int result = divide(10, 0);
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Caught an ArithmeticException: " + e.getMessage());
        } finally {
            System.out.println("Execution of the try-catch block is complete.");
        }

        // You can write your own Exceptions
        try {
            checkAge(-5);
        } catch (InvalidAgeException e) {
            System.out.println("Caught an InvalidAgeException: " + e.getMessage());
        }

        // Unchecked exceptions do not need to be declared or caught as shown below
        String str = null;
        try {
            System.out.println(str.length());
        } catch (NullPointerException e) {
            System.out.println("Caught a NullPointerException: " + e.getMessage());
        }
        // but you dont have to catch it
        // str.length();

        // checked exceptions must be declared or caught
        // checkAge(8);

        // Try with resources..
        /*
        List<InputStream> lotsOfInputStreams = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            try {
                lotsOfInputStreams.add(new FileInputStream("/etc/hosts"));
            } catch (Exception e) {
                System.out.println("Opened #files " + lotsOfInputStreams.size());
                throw new RuntimeException(e);
            }
        }
        */

        // Debug this section by setting a bp in the FIS::close method
        for (int i = 0; i < 20000; i++) {
            try (InputStream is = new FileInputStream("/etc/hosts")) {
                if (is.available() <= 0) throw new Exception("Bad stream");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static int divide(int a, int b) {
        return a / b;
    }

    public static void checkAge(int age) throws InvalidAgeException {
        if (age < 0 || age > 150) {
            throw new InvalidAgeException("Age must be between 0 and 150.");
        } else {
            System.out.println("Valid age: " + age);
        }
    }

    // Custom Exception class
    static class InvalidAgeException extends Exception {
        public InvalidAgeException(String message) {
            super(message);
        }
    }
}
