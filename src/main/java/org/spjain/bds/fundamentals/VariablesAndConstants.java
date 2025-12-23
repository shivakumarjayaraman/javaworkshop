package org.spjain.bds.fundamentals;

public class VariablesAndConstants {
    public static void main(String[] args) {
        // Variable declaration and initialization
        int myVariable = 10;
        System.out.println("Initial Value of myVariable: " + myVariable);

        // Changing the value of the variable
        myVariable = 20;
        System.out.println("Updated Value of myVariable: " + myVariable);

        // Using a local variable without initialization will cause a compilation error
        int uninitializedVariable;
        //System.out.println("Uninitialized Variable cannot be used until initialized. " + uninitializedVariable);

        // however you can initialize it before use
        uninitializedVariable = 30;
        System.out.println("Now the uninitializedVariable is initialized: " + uninitializedVariable);


        // Constant declaration and initialization
        final double PI = 3.14159;
        System.out.println("Value of constant PI: " + PI);

        // Attempting to change the value of the constant (uncommenting the next line will cause a compilation error)
        //PI = 3.14; // This will cause an error

        // Demonstrating different data types with variables
        String name = "John Doe";
        boolean isJavaFun = true;

        System.out.println("Name: " + name);
        System.out.println("Is Java fun? " + isJavaFun);
    }
}
