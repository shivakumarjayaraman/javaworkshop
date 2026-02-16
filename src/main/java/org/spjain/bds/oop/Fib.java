package org.spjain.bds.oop;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Fib {
    // 0 1 1 2 3 5 8 13 21 34

    static Map<Integer, BigInteger> memo = new HashMap<>();
    public static BigInteger fib(int n) {
        if (n == 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;

        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        memo.put(n, fib(n-1).add(fib(n-2)));
        return memo.get(n);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(i + " => " + fib(i));
        }
    }
}
