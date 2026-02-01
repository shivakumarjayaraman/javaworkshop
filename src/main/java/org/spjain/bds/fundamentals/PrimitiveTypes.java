package org.spjain.bds.fundamentals;

public class PrimitiveTypes {
    public static void main(String[] args) {
        // Integer types
        byte byteVar = 10;
        short shortVar = 1000;
        int intVar = 100000;
        long longVar = 100000L;

        // Floating-point types
        float floatVar = 10.5f;
        double doubleVar = 20.99;

        // Character type
        char charVar = 'A';

        // Boolean type
        boolean boolVar = true;



        // Print the values
       /* System.out.println("Primitive Data Types in Java:");
        System.out.println("Byte Value: " + byteVar);
        System.out.println("Short Value: " + shortVar);
        System.out.println("Integer Value: " + intVar);
        System.out.println("Long Value: " + longVar);
        System.out.println("Float Value: " + floatVar);
        System.out.println("Double Value: " + doubleVar);
        System.out.println("Character Value: " + charVar);
        System.out.println("Boolean Value: " + boolVar);*/

     /*   charVar = '\u263a'; // Unicode for a smiley face
        System.out.println("This is fun, right ? " + charVar);*/

        // print the size of all the primitive types in bytes
        System.out.println("-------------------------------");
        System.out.println("Size of primitive data types in Java:");
        System.out.println("Size of byte: " + Byte.BYTES + " byte");
        System.out.println("Size of short: " + Short.BYTES + " bytes");
        System.out.println("Size of int: " + Integer.BYTES + " bytes");
        System.out.println("Size of long: " + Long.BYTES + " bytes");
        System.out.println("Size of float: " + Float.BYTES + " bytes");
        System.out.println("Size of double: " + Double.BYTES + " bytes");
        System.out.println("Size of char: " + Character.BYTES + " bytes");

        // Note: boolean size is not precisely defined in Java
        //System.out.println("Size of boolean: " + "1 bit (not precisely defined in Java)");


        // Print the maximum and minimum values of all the primitive types

        System.out.println("-------------------------------");
        System.out.println("Minimum and Maximum values of primitive data types in Java:");
        System.out.println("Byte Min Value: " + Byte.MIN_VALUE + ", Max Value: " + Byte.MAX_VALUE);
        System.out.println("Short Min Value: " + Short.MIN_VALUE + ", Max Value: " + Short.MAX_VALUE);
        System.out.println("Integer Min Value: " + Integer.MIN_VALUE + ", Max Value: " + Integer.MAX_VALUE);
        System.out.println("Long Min Value: " + Long.MIN_VALUE + ", Max Value: " + Long.MAX_VALUE);
        System.out.println("Float Min Value: " + Float.MIN_VALUE + ", Max Value: " + Float.MAX_VALUE);
        System.out.println("Double Min Value: " + Double.MIN_VALUE + ", Max Value: " + Double.MAX_VALUE);
        System.out.println("Character Min CodePoint: " + Character.MIN_CODE_POINT+ ", Max CodePoint: " + Character.MAX_CODE_POINT);
        // Can you extend this to print some strings in your native language ?
        // Mine is Tamil

        System.out.println("Here is a string in Tamil: தமிழ் மொழி அழகானது");
        System.out.println("The length of தமிழ் is " + "தமிழ்".length());
        System.out.println("The number of bytes in தமிழ் is " + "தமிழ்".getBytes().length);
        for (int i = 0; i < "தமிழ்".length(); i++) {
            System.out.println("தமிழ்".codePointAt(i));
        }

        System.out.println("新年快乐".length());
        System.out.println("新年快乐".getBytes().length);
    }
}
