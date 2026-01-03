package org.spjain.bds.oop.subpackage;

import org.spjain.bds.oop.AccessModifiers;

public class SubAccessModifier extends AccessModifiers {
    public void accessFields() {
        AccessModifiers am = new AccessModifiers();

        // Accessing public field
        System.out.println("Public Field: " + am.publicField);

        // Accessing protected field
        System.out.println("Protected Field: " + this.protectedField);

        // Accessing package-private field - Not accessible
        // System.out.println("Package Field: " + am.packageField); // Compile-time error

        // Accessing private field via public getter
        System.out.println("Private Field: " + am.getPrivateField());
    }
}
