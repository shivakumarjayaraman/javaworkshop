package org.spjain.bds.fundamentals;

public class TheStringClass {
    public static void main(String[] args) {
        // Creating strings
        String str1 = "Hello, World!";
        String str2 = new String("Hello, Java!");

        // String concatenation
        String concatenated = str1 + " " + str2;
        System.out.println("Concatenated String: " + concatenated);

        // String methods
        int length = concatenated.length();
        String upperCase = concatenated.toUpperCase();
        String lowerCase = concatenated.toLowerCase();
        String substring = concatenated.substring(7, 12);
        int indexOfJava = concatenated.indexOf("Java");

        System.out.println("Length: " + length);
        System.out.println("Upper Case: " + upperCase);
        System.out.println("Lower Case: " + lowerCase);
        System.out.println("Substring (7-12): " + substring);
        System.out.println("Index of 'Java': " + indexOfJava);

        // String comparison
        String str3 = "hello, world!";
        boolean isEqual = str1.equals(str3);
        boolean isEqualIgnoreCase = str1.equalsIgnoreCase(str3);

        System.out.println("Is Equal: " + isEqual);
        System.out.println("Is Equal Ignore Case: " + isEqualIgnoreCase);

        // == vs .equals()
        String str4 = new String("Hello, World!");
        boolean isSameReference = (str1 == str4);
        boolean isSameValue = str1.equals(str4);
        System.out.println("Is Same Reference (==): " + isSameReference);
        System.out.println("Is Same Value (.equals()): " + isSameValue);

        // string immutability
        String original = "Immutable";
        String modified = original.replace("Immutable", "Mutable");
        System.out.println("Original String: " + original);

        // conversion between strings and other data types
        int number = 100;
        String numberStr = Integer.toString(number);
        String strNumber = "200";
        int parsedNumber = Integer.parseInt(strNumber);
        System.out.println("Number to String: " + numberStr);
        System.out.println("String to Number: " + parsedNumber);

        // string interning
        String internedStr1 = "Interned String";
        String internedStr2 = new String("Interned String").intern();
        String internedStr3 = "Interned String";
        System.out.println("Interned Strings are same reference: " + (internedStr1 == internedStr2));
        System.out.println("Interned Strings are same reference: " + (internedStr1 == internedStr3));

    }
}
