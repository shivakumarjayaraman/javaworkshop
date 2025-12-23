package org.spjain.bds.fundamentals;

public class ScopeOfVariables {
    public static void main(String[] args) {
        int outerVar = 10; // Outer variable

        System.out.println("Outer Variable: " + outerVar);

        {
            // Inner block
            int innerVar = 20; // Inner variable
            System.out.println("Inner Variable: " + innerVar);
            System.out.println("Accessing Outer Variable from Inner Block: " + outerVar);

            // cant redeclare outerVar here
            // int outerVar = 30; // This will cause a compilation error
        }

        // Uncommenting the next line will cause a compilation error
        // System.out.println("Accessing Inner Variable from Outer Block: " + innerVar);
    }
}
