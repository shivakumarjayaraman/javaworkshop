package org.spjain.bds.oop;

public class DemoClass {

    public static void main(String[] args) {
        System.out.println(Person.getPopulation());

        System.out.println(Math.PI);

        Person al = new Person("Alice", 30);
        Person a2 = new Person("Alice", 30);

        System.out.println(al.equals(a2));

        Person suvrat = new Person("Suvat", 17);

        System.out.println(al);





        al.displayInfo();
        al.celebrateBirthday();

        al.displayInfo();
        suvrat.displayInfo();

        //System.out.println("Name is " + person.name);

        // Using default constructor
        Blob blob = new Blob();
        System.out.println(blob.getData()); // null
        blob.setData("Sample Blob Data");

        // default cons is generated for you (IF YOU DONT HAVE OTHER CONSTRUCTORS)


    }

}
