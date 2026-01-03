package org.spjain.bds.oop;

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

    public void makeSound(String sound) {
        System.out.printf("Cat says %s\n", sound);
    }
}
