package org.spjain.bds.oop;

public class AccessModifiers {
    private String privateField = "Private Field";

    protected String protectedField = "Protected Field";
    String packageField = "Package Field"; // default access

    public String publicField = "Public Field";

    public String getPrivateField() {
        return privateField;
    }
}
