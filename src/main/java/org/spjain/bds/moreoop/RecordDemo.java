package org.spjain.bds.moreoop;

public class RecordDemo {
    public static void main(String [] args) {
        MyRecord record = new MyRecord("John Doe", 30);
        System.out.println(record.name());
        System.out.println(record.age());
        MyRecord record2 = new MyRecord("John Doe", 30);

        // Records automatically implement equals, hashCode, and toString
        System.out.println(record.equals(record2)); // true
        System.out.println(record2); // MyRecord[name=John Doe, age=30]
        System.out.println(record.hashCode() == record2.hashCode()); // true

        // You can define methods inside records
        record.someMethod();
    }
}
