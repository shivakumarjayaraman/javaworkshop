package org.spjain.bds.fundamentals;

public class ArithmeticOperations {
    public static void main(String[] args) {
        int a = 15;
        int b = 4;

        // Addition
        int sum = a + b;
        System.out.println("Addition: " + a + " + " + b + " = " + sum);

        // Subtraction
        int difference = a - b;
        System.out.println("Subtraction: " + a + " - " + b + " = " + difference);

        // Multiplication
        int product = a * b;
        System.out.println("Multiplication: " + a + " * " + b + " = " + product);

        // Division
        int quotient = a / b;
        System.out.println("Division: " + a + " / " + b + " = " + quotient);

        // Modulus
        int remainder = a % b;
        System.out.println("Modulus: " + a + " % " + b + " = " + remainder);

        // formatted printing can be done this way
        System.out.printf("a is %d b is %.2f\n", a, (float) b);
    }
}
