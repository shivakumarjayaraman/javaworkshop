package org.spjain.bds.moreoop;

import org.spjain.bds.oop.Animal;

import java.util.ArrayList;
import java.util.List;

public class Interfaces {
    public static void main(String[] args) {
        System.out.println("Interfaces in Java");

        // Interfaces define methods that a class must implement
        // The following example uses the Runnable interface which
        // has a single method run()
        // The below is an anonymous class implementing Runnable
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Running in a separate thread");
            }
        };

        r.run();

        // We could do the same with lambda expressions since
        // Runnable is a functional interface (single abstract method)
        Runnable r2 = () -> System.out.println("I am a lambda expression");
        r2.run();

        // Interfaces can be used to implement a **safe** form of multiple inheritance
        MyComparableAndRunnableClass obj1 = new MyComparableAndRunnableClass(10);
        MyComparableAndRunnableClass obj2 = new MyComparableAndRunnableClass(20);
        obj1.run();
        List<MyComparableAndRunnableClass> list = new ArrayList<>();
        list.add(obj2);
        list.add(obj1);
        for (int i = 0; i < 5; i++) {
            list.add(new MyComparableAndRunnableClass((int)(Math.random() * 100)));
        }
        System.out.println(list);
        list.sort(null); // Uses compareTo method
        System.out.println(list);

        // Interfaces can also have default methods with implementation
        MyInterface myInterfaceImpl = new MyInterface() {
            @Override
            public int compute(int a, int b) {
                return a * b;
            }

            /*
            @Override
            public int doStuff(int a, int b) {
                return a - b; // overriding default method
            }
             */
        };

        System.out.println(myInterfaceImpl.compute(5, 10));
        System.out.println(myInterfaceImpl.doStuff(5, 10)); // default method

    }

    private static class MyComparableAndRunnableClass implements Runnable, Comparable<MyComparableAndRunnableClass> {
        private int value;
        public MyComparableAndRunnableClass(int value) {
            this.value = value;
        }

        @Override
        public void run() {
            System.out.println("Running with value: " + value);
        }

        @Override
        public int compareTo(MyComparableAndRunnableClass other) {
            return Integer.compare(this.value, other.value);
        }

        @Override
        public String toString() {
            return "MyComparableAndRunnableClass{" +
                    "value=" + value +
                    '}';
        }
    }
}
