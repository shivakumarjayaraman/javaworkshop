package org.spjain.bds.fundamentals;

public class TypeConversions {
    public static void main(String[] args) {
        // Implicit Conversion (Widening)
        int intVar = 100;
        long longVar = intVar; // int to long
        float floatVar = longVar; // long to float
       /* System.out.println("Implicit Conversion:");
        System.out.println("Integer Value: " + intVar);
        System.out.println("Converted to Long: " + longVar);
        System.out.println("Converted to Float: " + floatVar);*/


        // math with mixed types
       /* System.out.printf("100/3 is %f\n", 100.0/3); // int and double => double
        System.out.println("100/3 is " +  100/3); // int and double => double
        var y = 100 + 3.5; // int and float => float
        System.out.println("100 + 3.5f is " + y);
        */


      /*  int result = 10 + 'A'; // char to int
        System.out.println("Result of 10 + 'A' : " + result);*/


        // Explicit Conversion (Narrowing)

       /* double doubleVar = 99.99;
        // float floatVarDirect =  doubleVar; // double to float
        float floatVar2 = (float) doubleVar; // double to float
        int intVar2 = (int) floatVar2; // float to int
        System.out.println("\nExplicit Conversion:");
        System.out.println("Double Value: " + doubleVar);
        System.out.println("Converted to Float: " + floatVar2);
        System.out.println("Converted to Integer: " + intVar2);*/

        // Conversion between String and Primitive Types
       /* String strNum = "12345";
        int parsedInt = Integer.parseInt(strNum); // String to int
        String strFromInt = Integer.toString(parsedInt); // int to String
        System.out.println("\nString and Primitive Type Conversion:");
        System.out.println("String Value: " + strNum);
        System.out.println("Parsed to Integer: " + parsedInt);
        System.out.println("Converted back to String: " + strFromInt);*/
    }
}
