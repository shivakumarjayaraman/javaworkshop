package org.spjain.bds.fundamentals;

public class InnerClass {
    public static void main(String[] args) {
        // static inner classes
        Outer outer = new Outer(10);
        Outer.Inner inner = outer.new Inner();
        inner.display();

        // local inner classes
        int localVar = 10;
        class LocalInner {
            void show() {
                System.out.println("Inside Local Inner Class " + localVar);
                // localVar++; // localVar must be effectively final
            }
        }
        LocalInner localInner = new LocalInner();
        localInner.show();


        // anonymous inner classes
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Inside Anonymous Inner Class");
            }
        };
    }

    static class Outer {
        private int outerVar = 20;
        public Outer(int outerVar) {
            this.outerVar = outerVar;
        }
        class Inner {
            void display() {
                System.out.println("Inside Inner Class " + outerVar);
                System.out.println("can also access it this way " + Outer.this.outerVar);
            }
        }
    }
}
