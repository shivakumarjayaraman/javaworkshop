package org.spjain.bds.oop;

import java.util.Objects;

public class Person {
    private static int population = 0; // Static variable to keep track of the population

    private String name;
    private int age;

    protected float height; // in cm


    public Person(String name, int age) {
        population++;
        this.name = name;
        this.age = age;
    }

    public static int getPopulation() {
        return population;
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }


    public void celebrateBirthday() {
        this.age++;
    }

    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

   @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
