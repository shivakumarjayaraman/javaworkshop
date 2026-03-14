package org.spjain.bds.exceptions;

public class A {
    private B b;
    public A(B b) {
        this.b = b;
    }

    public void demoExceptions() {
        try {
            System.out.println(b.doSomething(null));
            System.out.println("done");
        } catch (MyException e) {
            System.out.println("Caught Exception");
        } catch (Exception e) {
            System.out.println("Caught MyException");
        } finally {
            System.out.println("This will always execute");
        }
    }

    public static void main(String[] args) {
        B b = new B();
        A a = new A(b);
        a.demoExceptions();
    }
}
