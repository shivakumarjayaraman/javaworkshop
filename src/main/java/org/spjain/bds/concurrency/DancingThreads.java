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

    static BlockingQueue<String> t1q = new LinkedBlockingQueue(1);
    static BlockingQueue<String> t2q = new LinkedBlockingQueue(1);

    public static void main(String [] args) throws Exception {
        final int count = 100;
        Thread t1 = new Thread() {
            public void run() {
                for (int i = 0; i < count; i++) {
                    try { t2q.take(); } catch (Exception e) {}
                    System.out.println("I am thread 1");
                    try { t1q.put("hello"); } catch (Exception e) {}
                    DancingThreads.sleep(300);
                }
            }
        };
        Thread t2 = new Thread() {
            public void run() {
                for (int i = 0; i < count; i++) {
                    try { t1q.take(); } catch (Exception e) {}
                    System.out.println("I am thread 2");
                    try { t2q.put("hello"); } catch (Exception e) {}
                    DancingThreads.sleep(100);
                }
            }
        };

        t1.start();
        t2.start();

        t2q.put("hello"); // start the dance by giving the first signal to t1

        t1.join(); t2.join();
        System.out.println("Both threads done dancing");
    }
}
