package org.spjain.bds.oop;

public class DemoInstanceAndClassVariables {
    // Class variable (static variable)
    static String organization = "OpenAI";

    // Instance variable
    String name;

    // Constructor
    public DemoInstanceAndClassVariables(String name) {
        this.name = name;
    }

    // Method to display instance and class variables
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Organization: " + organization);
    }

    public static void main(String[] args) {
        // Creating instances of the class
        DemoInstanceAndClassVariables person1 = new DemoInstanceAndClassVariables("Alice");
        DemoInstanceAndClassVariables person2 = new DemoInstanceAndClassVariables("Bob");

        // Displaying information for both instances
        person1.displayInfo();
        person2.displayInfo();
    }
}
