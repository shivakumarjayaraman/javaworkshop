package org.spjain.bds.oop;

import java.util.HashSet;
import java.util.Set;

public class TheObject {
    public static void main(String[] args) {
        // Equals and HashCode demonstration
        Person person1 = new Person("Alice", 30);
        Person person2 = new Person("Alice", 30);
        Person person3 = person1;

        System.out.println(person1.getClass().getSuperclass().getName());

        System.out.println("person1 equals person2: " + person1.equals(person2)); // false
        System.out.println("person1 equals person3: " + person1.equals(person3)); // true
        System.out.println("person1 hashCode: " + person1.hashCode());
        System.out.println("person2 hashCode: " + person2.hashCode());

        Set<Person> personSet = new HashSet<>();
        personSet.add(person1);
        personSet.add(person2);
        System.out.println("Set size (should be 2 if hashCodes differ): " + personSet.size());

        System.out.println(person1);
    }
}
