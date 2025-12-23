package org.spjain.bds.fundamentals;

public class FunctionsAndParameterCallingConventions {
    public static void main(String[] args) {
        int a = 5;
        int b = 10;

        // Call by Value
        System.out.println("Before Call by Value: a = " + a + ", b = " + b);
        callByValue(a, b);
        System.out.println("After Call by Value: a = " + a + ", b = " + b);

        // Call by Reference using Wrapper Class
        IntegerWrapper aRef = new IntegerWrapper(5);
        IntegerWrapper bRef = new IntegerWrapper(10);
        System.out.println("\nBefore Call by Reference: aRef.value = " + aRef.value + ", bRef.value = " + bRef.value);
        callByReference(aRef, bRef);
        System.out.println("After Call by Reference: aRef.value = " + aRef.value + ", bRef.value = " + bRef.value);
    }

    private static void callByValue(int x, int y) {
        x += 10;
        y += 20;
        System.out.println("Inside Call by Value: x = " + x + ", y = " + y);
    }

    private static void callByReference(IntegerWrapper xRef, IntegerWrapper yRef) {
        xRef.value += 10;
        yRef.value += 20;
        System.out.println("Inside Call by Reference: xRef.value = " + xRef.value + ", yRef.value = " + yRef.value);
    }

    static class IntegerWrapper {
        int value;

        IntegerWrapper(int value) {
            this.value = value;
        }
    }
}
