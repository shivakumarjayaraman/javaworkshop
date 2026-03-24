package org.spjain.bds.concurrency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Concurrency in Java — managing multiple threads doing work at the same time.
 *
 * We already saw raw Thread / Runnable in the threads package (SimpleThread.java).
 * This class builds on that with the tools you'll actually use day-to-day:
 *
 *   ExecutorService    — a managed pool of threads (Python: ThreadPoolExecutor)
 *   Callable / Future  — tasks that return a value      (Python: executor.submit)
 *   synchronized       — mutual exclusion               (Python: threading.Lock)
 *   AtomicInteger      — lock-free counter              (no direct Python equivalent)
 *   ReentrantLock      — explicit lock with more control(Python: threading.Lock)
 *   CountDownLatch     — wait for N events to complete  (Python: threading.Barrier)
 *   ConcurrentHashMap  — thread-safe map                (Python: dict + lock)
 *   BlockingQueue      — thread-safe producer/consumer  (Python: queue.Queue)
 */
public class Concurrency {

    // -------------------------------------------------------------------------
    // 1. ExecutorService — a managed pool of threads
    //
    //    Raw Thread creation is expensive and hard to control.
    //    An ExecutorService keeps a pool of worker threads ready to go.
    //
    //    Python:
    //      from concurrent.futures import ThreadPoolExecutor
    //      with ThreadPoolExecutor(max_workers=3) as pool:
    //          pool.submit(fn, arg)
    // -------------------------------------------------------------------------
    private static void demoThreadPool() throws InterruptedException {
        System.out.println("\n--- Thread Pool (ExecutorService) ---");

        // 3 worker threads shared across all submitted tasks
        ExecutorService pool = Executors.newFixedThreadPool(3);

        // Submit 6 tasks — the pool queues extras until a worker is free
        for (int i = 0; i < 6; i++) {
            final int taskId = i;
            pool.submit(() -> {
                System.out.println("Task " + taskId + " running on " + Thread.currentThread().getName());
                try { Thread.sleep(300); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            });
        }

        pool.shutdown();                              // stop accepting new tasks
        pool.awaitTermination(5, TimeUnit.SECONDS);  // wait for running tasks to finish
        System.out.println("All tasks done");
    }

    // -------------------------------------------------------------------------
    // 2. Callable + Future — submit work that returns a value
    //
    //    Runnable: void run()          — fire and forget
    //    Callable: V    call()         — returns a result (or throws)
    //    Future<V>                     — a handle to a result not yet computed
    //
    //    Python:
    //      future = pool.submit(fn)
    //      result = future.result()   # blocks until done
    // -------------------------------------------------------------------------
    private static void demoCallableAndFuture() throws Exception {
        System.out.println("\n--- Callable + Future ---");

        ExecutorService pool = Executors.newFixedThreadPool(2);

        // Callable is like Runnable but returns a value
        Callable<Integer> task = () -> {
            Thread.sleep(300);
            return 42;
        };

        Future<Integer> future = pool.submit(task);
        System.out.println("Task submitted — main thread doing other work...");

        // future.get() blocks until the result is ready
        int result = future.get();
        System.out.println("Got result: " + result);

        pool.shutdown();
    }

    // -------------------------------------------------------------------------
    // 3. Race condition — what goes wrong WITHOUT synchronization
    //
    //    counter++ looks like one operation but is actually three:
    //      read counter
    //      add 1
    //      write counter
    //    Two threads can interleave those steps and lose an update.
    // -------------------------------------------------------------------------
    private static int unsafeCounter = 0;

    private static void demoRaceCondition() throws InterruptedException {
        System.out.println("\n--- Race Condition (deliberately broken) ---");
        unsafeCounter = 0;

        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            pool.submit(() -> unsafeCounter++);  // NOT atomic — race condition here
        }
        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);

        // Set a breakpoint here and run a few times — the answer changes!
        System.out.println("Expected 1000, got: " + unsafeCounter + "  (probably less)");
    }

    // -------------------------------------------------------------------------
    // 4. synchronized — fix the race with mutual exclusion
    //
    //    Only one thread may execute a synchronized method on the same object
    //    at a time. Others block until the lock is released.
    //
    //    Python:
    //      lock = threading.Lock()
    //      with lock:
    //          counter += 1
    // -------------------------------------------------------------------------
    private static int syncCounter = 0;

    // The 'synchronized' keyword locks on the Class object (because it's static)
    private static synchronized void incrementSync() {
        syncCounter++;
    }

    private static void demoSynchronized() throws InterruptedException {
        System.out.println("\n--- synchronized (fixed) ---");
        syncCounter = 0;

        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            pool.submit(() -> incrementSync());
        }
        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("Expected 1000, got: " + syncCounter + "  (always correct)");
    }

    // -------------------------------------------------------------------------
    // 5. AtomicInteger — lock-free thread-safe counter
    //
    //    For simple numeric operations, AtomicInteger is faster than
    //    synchronized because it uses CPU-level compare-and-swap instructions
    //    instead of OS-level locks.
    // -------------------------------------------------------------------------
    private static void demoAtomicInteger() throws InterruptedException {
        System.out.println("\n--- AtomicInteger ---");

        AtomicInteger counter = new AtomicInteger(0);

        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            pool.submit(() -> counter.incrementAndGet());  // atomic — no race
        }
        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("Expected 1000, got: " + counter.get());
    }

    // -------------------------------------------------------------------------
    // 6. ReentrantLock — explicit lock with more control than synchronized
    //
    //    Advantages over synchronized:
    //      - tryLock()  — try to acquire but don't block forever
    //      - lockInterruptibly() — give up if thread is interrupted
    //      - Multiple Condition objects (like multiple wait queues)
    //
    //    Rule: always unlock() in a finally block so the lock is released
    //    even if an exception is thrown inside the critical section.
    // -------------------------------------------------------------------------
    private static void demoReentrantLock() throws InterruptedException {
        System.out.println("\n--- ReentrantLock ---");

        int[] counter = {0};       // array lets the lambda capture and mutate it
        ReentrantLock lock = new ReentrantLock();

        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            pool.submit(() -> {
                lock.lock();
                try {
                    counter[0]++;
                } finally {
                    lock.unlock();  // MUST be in finally — never skip this
                }
            });
        }
        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("Expected 1000, got: " + counter[0]);
    }

    // -------------------------------------------------------------------------
    // 7. CountDownLatch — wait until N things have happened
    //
    //    Common uses:
    //      - Wait for N workers to finish before proceeding
    //      - Wait for N services to start before firing requests
    //
    //    Python:
    //      barrier = threading.Barrier(n_workers)
    //      # each thread calls barrier.wait() when done
    // -------------------------------------------------------------------------
    private static void demoCountDownLatch() throws InterruptedException {
        System.out.println("\n--- CountDownLatch ---");

        int numWorkers = 5;
        CountDownLatch latch = new CountDownLatch(numWorkers);  // count starts at 5

        ExecutorService pool = Executors.newFixedThreadPool(numWorkers);
        for (int i = 0; i < numWorkers; i++) {
            final int workerId = i;
            pool.submit(() -> {
                try {
                    Thread.sleep(100 + workerId * 100);
                    System.out.println("  Worker " + workerId + " finished");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    latch.countDown();  // decrement the count; unblocks await() when it hits 0
                }
            });
        }

        System.out.println("Main thread waiting for all workers...");
        latch.await();  // blocks here until count reaches 0
        System.out.println("All workers done — main thread unblocked");
        pool.shutdown();
    }

    // -------------------------------------------------------------------------
    // 8. ConcurrentHashMap — thread-safe map without a global lock
    //
    //    A regular HashMap is not safe for concurrent writes — you'll get
    //    corrupted data or an infinite loop.
    //    ConcurrentHashMap uses fine-grained locks (one per bucket) so
    //    threads rarely block each other.
    //
    //    Python: dict is not thread-safe for concurrent writes either;
    //    you need a Lock around every read-modify-write.
    // -------------------------------------------------------------------------
    private static void demoConcurrentHashMap() throws InterruptedException {
        System.out.println("\n--- ConcurrentHashMap ---");

        ConcurrentHashMap<String, Integer> wordCount = new ConcurrentHashMap<>();
        //HashMap<String, Integer> wordCount = new HashMap<>();
        List<String> words = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            int rand = (int) (Math.random() * 10000) %3;
            words.add(switch (rand) {
                case 0 -> "Apple";
                case 1 -> "Banana";
                default -> "Cherry";
            });
        }
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (String word : words) {
            pool.submit(() -> {
                // merge: if key absent, put 1; otherwise apply Integer::sum to old+new
                wordCount.merge(word, 1, Integer::sum);  // atomic operation
            });
        }
        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);

        int total = wordCount.values().stream().reduce((x,y) -> x+y).get();
        System.out.println(total);
        System.out.println("Word counts: " + wordCount);

    }

    // -------------------------------------------------------------------------
    // 9. BlockingQueue — thread-safe producer / consumer channel
    //
    //    put()  — add an item; blocks if the queue is full
    //    take() — remove an item; blocks if the queue is empty
    //
    //    This is the classic way to hand work off between threads without
    //    writing your own synchronization.
    //
    //    Python:
    //      import queue
    //      q = queue.Queue(maxsize=5)
    //      q.put(item)   # blocks if full
    //      q.get()       # blocks if empty
    // -------------------------------------------------------------------------
    private static void demoBlockingQueue() throws InterruptedException {
        System.out.println("\n--- BlockingQueue (producer / consumer) ---");

        BlockingQueue<String> queue = new LinkedBlockingQueue<>(5);  // max 5 items

        // Producer — creates items and puts them on the queue
        Thread producer = new Thread(() -> {
            String[] items = {"item-1", "item-2", "item-3", "item-4", "DONE"};
            for (String item : items) {
                try {
                    queue.put(item);         // blocks if queue is full
                    System.out.println("Produced: " + item);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Consumer — takes items off the queue and processes them
        Thread consumer = new Thread(() -> {
            while (true) {
                try {
                    String item = queue.take();  // blocks if queue is empty
                    if ("DONE".equals(item)) break;
                    System.out.println("  Consumed: " + item);
                    Thread.sleep(250);           // consumer is slower than producer
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            System.out.println("Consumer done");
        });

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }

    // -------------------------------------------------------------------------
    // main — step through each section in the debugger
    // -------------------------------------------------------------------------
    public static void main(String[] args) throws Exception {
        demoThreadPool();
        demoCallableAndFuture();
        demoRaceCondition();
        demoSynchronized();
        demoAtomicInteger();
        demoReentrantLock();
        demoCountDownLatch();
        demoConcurrentHashMap();
        demoBlockingQueue();
    }
}
