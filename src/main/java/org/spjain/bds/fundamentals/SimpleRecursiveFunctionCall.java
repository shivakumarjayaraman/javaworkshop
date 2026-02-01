package org.spjain.bds.fundamentals;

public class SimpleRecursiveFunctionCall {
    public static void main(String[] args) {
        int number = 4; // Change this value to compute factorial of a different number
        int result = factorial(number);
        System.out.println("Factorial of " + number + " is: " + result);
    }

    private static int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1; // Base case: factorial of 0 or 1 is 1
        } else {
            return n * factorial(n - 1); // Recursive case
        }
    }
}
