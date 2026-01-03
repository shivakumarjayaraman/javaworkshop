package org.spjain.bds.oop;

public class OverloadingAndOverriding {
    public static void main(String[] args) {
        Animal myAnimal = new Animal(5);
        myAnimal.makeSound();

        Dog myDog = new Dog("Buddy", 3);
        myDog.makeSound();
        myDog.fetch();

        Cat talkingCat = new Cat(2);
        talkingCat.makeSound();
        talkingCat.makeSound("Hello");

        // static methods can only be overloaded, not overridden
        doSomething();
        doSomething(10);

        // up and down casting
        // RHS is a LHS type (Liskov's Substitution Principle)
        Animal animalRef = new Dog("Rex", 4); // upcasting
        animalRef.makeSound(); // calls Dog's makeSound due to polymorphism
        if (animalRef instanceof Dog) {
            Dog dogRef = (Dog) animalRef; // downcasting
            dogRef.fetch();
        }

        // Problem .. Will throw an exception
        // Dog wrongDogRef = (Dog) new Animal(); // This would throw ClassCastException at runtime
    }

    private static void doSomething() {
        System.out.println("Doing something without parameters");
    }

    private static void doSomething(int number) {
        System.out.println("Doing something with an integer: " + number);
    }


}
