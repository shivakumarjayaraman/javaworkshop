package org.spjain.bds.exceptions;

public class B {
    public int doSomething(String s) throws MyException {
        if (s == null) {
            throw new MyException();
        }
        return s.length();
    }
}
