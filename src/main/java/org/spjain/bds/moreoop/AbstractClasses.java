package org.spjain.bds.moreoop;

public class AbstractClasses {
    public static void main(String[] args) {
        // Create an instance of the concrete subclass
        MyConcreteClass myObject = new MyConcreteClass();

        // Call the implemented abstract method
        int result = myObject.compute(5, 10);
        System.out.println("Result of compute: " + result);

        // Call the concrete method from the abstract class
        myObject.displayMessage();

        // However abstract classes cannot be instantiated directly
        // MyAbstractClass obj = new MyAbstractClass(); // This will cause a compilation error
    }

    // Define an abstract class with an abstract method and a concrete method
    abstract static class MyAbstractClass {
        // Abstract method (no implementation)
        public abstract int compute(int a, int b);

        // Concrete method (with implementation)
        public void displayMessage() {
            System.out.println("Hello from the abstract class!");
        }
    }

    // Define a concrete subclass that extends the abstract class
    static class MyConcreteClass extends MyAbstractClass {
        // Provide implementation for the abstract method
        @Override
        public int compute(int a, int b) {
            return a + b;
        }
    }

}
