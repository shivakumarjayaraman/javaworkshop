package org.spjain.bds.oop;

public class Animal {
    int age = 0;
    public Animal() {
        System.out.println();
    }

    public Animal(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void makeSound() {
        System.out.printf("Animal of age %d makes generic sound\n", age);
    }
}
