package org.spjain.bds.oop.subpackage;

import org.spjain.bds.oop.Animal;

public class Cat extends Animal {
    public Cat() {
        super();
    }

    public Cat(int age) {
        super(age);
    }

    @Override
    public void makeSound() {
        System.out.printf("Cat of age %d says Meow!\n", getAge());
    }

    public void scratch() {
        System.out.println("The cat is scratching the furniture.");
    }
}
