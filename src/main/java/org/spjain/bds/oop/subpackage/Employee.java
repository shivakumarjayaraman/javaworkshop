package org.spjain.bds.oop.subpackage;

import org.spjain.bds.oop.Person;

// IS A relationship - Employee is a Person
public class Employee extends Person {
    public Employee(String name, int age) {
        super(name, age); // Call the constructor of the superclass (Person)

    }


     @Override
     public void displayInfo() {
         super.displayInfo(); // Call the displayInfo method of the superclass (Person)
         System.out.println(this.height);
     }
}
