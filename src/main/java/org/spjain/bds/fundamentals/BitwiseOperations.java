package org.spjain.bds.fundamentals;

public class BitwiseOperations {
    public static void main(String[] args) {
        int a = 5;  // Binary: 0101
        int b = 3;  // Binary: 0011

        // print the numbers in binary format
        System.out.println("a in binary: " + String.format("%4s", Integer.toBinaryString(a)).replace(' ', '0'));
        System.out.println("b in binary: " + String.format("%4s", Integer.toBinaryString(b)).replace(' ', '0'));

        // Bitwise AND
        int andResult = a & b; // Result: 0001 (1 in decimal)
        System.out.println("Bitwise AND: " + a + " & " + b + " = " + andResult);

        // Bitwise OR
        int orResult = a | b; // Result: 0111 (7 in decimal)
        System.out.println("Bitwise OR: " + a + " | " + b + " = " + orResult);

        // Bitwise XOR
        int xorResult = a ^ b; // Result: 0110 (6 in decimal)
        System.out.println("Bitwise XOR: " + a + " ^ " + b + " = " + xorResult);

        // Bitwise NOT
        int notResult = ~a; // Result: 1010 (inverted bits)
        System.out.println("Bitwise NOT: ~" + a + " = " + notResult);

        // Left Shift
        int leftShiftResult = a << 1; // Result: 1010 (10 in decimal)
        System.out.println("Left Shift: " + a + " << 1 = " + leftShiftResult);

        // Right Shift
        int rightShiftResult = a >> 1; // Result: 0010 (2 in decimal)
        System.out.println("Right Shift: " + a + " >> 1 = " + rightShiftResult);

        // Interesting operations with bitwise operators. We can swap two numbers without using a temporary variable.
        int x = 10;
        int y = 20;
        System.out.println("\nBefore Swapping: x = " + x + ", y = " + y);
        // print in binary before swapping
        System.out.println("x in binary: " + String.format("%8s", Integer.toBinaryString(x)).replace(' ', '0'));
        System.out.println("y in binary: " + String.format("%8s", Integer.toBinaryString(y)).replace(' ', '0'));

        x = x ^ y; // Step 1: x now holds the XOR of x and y
        y = x ^ y; // Step 2: y becomes the original x
        x = x ^ y; // Step 3: x becomes the original y
        System.out.println("After Swapping: x = " + x + ", y = " + y);



    }
}
