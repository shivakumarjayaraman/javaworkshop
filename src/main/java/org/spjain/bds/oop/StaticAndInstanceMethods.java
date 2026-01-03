package org.spjain.bds.oop.subpackage;

public class StaticAndInstanceMethods {
    // A class that demonstrates static and instance methods
    private static void staticMethod() {
        System.out.println("This is a static method.");
    }

    private void instanceMethod() {
        System.out.println("This is an instance method.");
    }

    public static void main(String[] args) {
        System.out.println("Static and Instance Methods Example");
        // Calling static method
        staticMethod();
        // cannot call instance method directly
        // instanceMethod();
        // Creating an instance to call instance method
        StaticAndInstanceMethods obj = new StaticAndInstanceMethods();
        obj.instanceMethod();
    }

}
