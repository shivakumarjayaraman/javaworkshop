package org.spjain.bds.concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * This class creates two threads (think of them as two tasks running in parallel).
 * The tasks take different amounts of time to get done, so the threads run in an
 * arbitrary order.
 *
 * Can you use the primitives you learnt in the class to make the threads take turns.
 * They go one after the other .. like a choreographed dance ..
 *
 * Hint : You can use BlockingQueues to make this happen.
 */
public class DancingThreads {

    private static void sleep(long millis) {
        try { Thread.sleep(millis); } catch (Exception e) {}
    }

    public static void main(String [] args) throws Exception {
        final int count = 100;

        Dancer d1 = new Dancer("D1", count);
        Dancer d2 = new Dancer("D2", count);

        d1.start(); sleep(5000); d2.start();
        d1.join(); d2.join();

        System.out.println("All Done");
    }

    static class Dancer extends Thread {
        private String name;
        private int count;

        public Dancer(String name, int count) {
            this.name = name;
            this.count = count;
        }

        public void run() {
            for (int i = 0; i < count; i++) {
                try {
                    System.out.println("I am " + name);
                    Thread.sleep(100); // Simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
