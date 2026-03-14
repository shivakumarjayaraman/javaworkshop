package org.spjain.bds.oop;

public class InheritanceDemo {

    private static void someFunc(Animal x) {
          x.makeSound();
    }

    public static void main(String[] args) {
        /*
        Animal generic = new Animal();
        generic.makeSound(); // Output: Animal of age 0 makes generic sound


        Dog dog = new Dog();
        dog.makeSound(); // Output: Woof!
        dog.fetch();     // Output: The dog is fetching the ball. // Polymorphism
        Animal a1 = new Dog("Max", 20);
        Animal a2 = new Cat(10);
        a1.makeSound(); // Output: Dog of age 20 says Woof!
        a2.makeSound(); // Output: Cat of age 10 says Meow!

         */

        Animal [] animals = new Animal[] {
                new Dog("Buddy", 5),
                new Cat(3),
                new Dog("Charlie", 7),
                new Cat(2)
        };

        for (Animal animal : animals) {
            someFunc(animal);
        }


    }
}
