package org.spjain.bds.fundamentals;

public class BasicFunctionCalls {
    public static void main(String[] args) {
        greetUser("Alice");
        int result = addNumbers(5, 10);
        System.out.println("Sum: " + result);
    }

    private static void greetUser(String name) {
        System.out.println("Hello, " + name + "!");
    }

    private static int addNumbers(int a, int b) {
        return a + b;
    }
}
