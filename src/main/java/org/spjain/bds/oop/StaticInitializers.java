package org.spjain.bds.oop;

public class StaticInitializers {
    static {
        System.out.println("First Static Initializer Block Executed");
        // System.out.println(staticVar);
    }

    {
        System.out.println("First Instance Initializer Block Executed");
        // System.out.println(instanceVar);
    }
    int instanceVar = 20;

    static int staticVar = 10;
    {
        System.out.println("Another Instance Initializer Block Executed");
        System.out.println(instanceVar);
    }

    int anotherInstanceVar = initializeFromAMethod();

    static {
        System.out.println("Another Static Initializer Block Executed");
        System.out.println(staticVar);
    }

    public StaticInitializers() {
        System.out.println("Constructor Executed");
        System.out.println("Inside constructor instanceVar " + instanceVar);
        System.out.println("Inside constructor staticVar " + staticVar);
        System.out.println("Inside constructor variable initialized from a function" + anotherInstanceVar);
    }

    int initializeFromAMethod() {
        System.out.println("Method to initialize instance variable called");
        return 30;
    }

    public static void main(String[] args) {
        System.out.println("Static Initializers Demo");
        StaticInitializers si = new StaticInitializers();
        System.out.println(si);
    }
}
