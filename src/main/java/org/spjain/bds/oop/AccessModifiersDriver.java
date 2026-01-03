package org.spjain.bds.oop;

public class AccessModifiersDriver {
    public static void main(String[] args) {
        AccessModifiers am = new AccessModifiers();

        // Accessing public field
        System.out.println("Public Field: " + am.publicField);

        // Accessing protected field
        System.out.println("Protected Field: " + am.protectedField);

        // Accessing package-private field
        System.out.println("Package Field: " + am.packageField);

        // Accessing private field via public getter
        // am.orivateField
        System.out.println("Private Field: " + am.getPrivateField());
    }
}
