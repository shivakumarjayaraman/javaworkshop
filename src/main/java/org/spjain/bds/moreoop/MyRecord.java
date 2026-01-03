package org.spjain.bds.moreoop;

public record MyRecord(String name, int age) {
    public void someMethod() {
        System.out.println("method inside the record");
    }
}
