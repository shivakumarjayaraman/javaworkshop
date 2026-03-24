package org.spjain.bds.concurrency;

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
        Thread t1 = new Thread() {
            public void run() {
                for (int i = 0; i < count; i++) {
                    System.out.println("I am thread 1");
                    DancingThreads.sleep(300);
                }
            }
        };
        Thread t2 = new Thread() {
            public void run() {
                for (int i = 0; i < count; i++) {
                    System.out.println("I am thread 2");
                    DancingThreads.sleep(100);
                }
            }
        };

        t1.start();
        t2.start();


        t1.join(); t2.join();
        System.out.println("Both threads done dancing");
    }
}
