package org.spjain.bds.moreoop;

public interface MyInterface {
    public int compute(int a, int b);

    default int doStuff(int a, int b) {
        return a + b;
    }
}
