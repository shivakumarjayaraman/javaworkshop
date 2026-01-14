package org.spjain.bds.oop;

public class Dog extends Animal {
    private String name = "Dog";

    public Dog() {
        super();
    }

    public Dog(String name, int age) {
        super(age);
        this.name = name;
    }

    @Override
    public void makeSound() {
        System.out.printf("Dog %s of age %d says Woof!\n", this.name, getAge());
    }

    public void fetch() {
        System.out.println("The dog is fetching the ball.");
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }
}
