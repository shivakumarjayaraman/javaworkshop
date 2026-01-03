package org.spjain.bds.oop.subpackage;
import org.spjain.bds.oop.AccessModifiers;

public class SomeGeneralClass {
    // Access of AccessModifiers class's package-private members is not allowed here
    public static void main(String[] args) {
        AccessModifiers am = new AccessModifiers();
        System.out.println(am.publicField);

        // System.out.println(am.packageField); // Compile-time error
        // System.out.println(am.protectedField); // Compile-time error
        // System.out.println(am.privateField); // Compile-time error
        System.out.println(am.getPrivateField());

    }
}
