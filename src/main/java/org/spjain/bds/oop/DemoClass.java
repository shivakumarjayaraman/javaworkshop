package org.spjain.bds.oop;

public class DemoClass {

    public static void main(String[] args) {
        Person person = new Person("Alice", 30);
        person.displayInfo();

        //System.out.println("Name is " + person.name);

        // Using default constructor
        Blob blob = new Blob();
        blob.setData("Sample Blob Data");

        // default cons is generated for you (IF YOU DONT HAVE OTHER CONSTRUCTORS)


    }

}
