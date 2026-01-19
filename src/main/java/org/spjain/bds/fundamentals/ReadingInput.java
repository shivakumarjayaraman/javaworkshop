package org.spjain.bds.fundamentals;
import java.util.Scanner;

public class ReadingInput {
        public static void main(String[] args) {
            // 1. Create a Scanner object
            Scanner scanner = new Scanner(System.in);

            // 2. Read a String
            System.out.print("Enter your name: ");
            String name = scanner.nextLine(); // Reads the entire line
            System.out.println("Hello, " + name + "!");

            // 3. Read an integer
            System.out.print("Enter your age: ");
            int age = scanner.nextInt(); // Scans the next token as an integer
            System.out.println("You are " + age + " years old.");

            // 4. Read a double (decimal number)
            System.out.print("Enter a decimal number: ");
            double number = scanner.nextDouble(); // Scans the next token as a double
            System.out.println("You entered: " + number);

            // 5. Close the scanner
            scanner.close();
        }
}
