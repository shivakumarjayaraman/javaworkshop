package org.spjain.bds.puzzles;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * INTERVIEW PROBLEM — JVM Memory & Object Lifecycle
 * --------------------------------------------------
 * Web frameworks like Spring/Tomcat store per-request data in a ThreadLocal
 * so it is accessible anywhere in the call stack without passing it as a
 * parameter. Spring's RequestContextHolder and SecurityContextHolder both
 * use this pattern.
 *
 * The implementation below works when each request runs on a brand-new thread,
 * but it has a memory leak that only surfaces under real server load, where a
 * fixed thread pool (e.g., Tomcat's 200 threads) handles millions of requests
 * over the server's lifetime.
 *
 * TASKS
 * -----
 * 1. In a comment block, explain:
 *      a. Exactly what leaks and why. Be specific about the relationship
 *         between ThreadLocal, the thread-pool threads, and GC roots.
 *      b. Why the leak does NOT appear when each request gets a fresh thread.
 *
 * 2. Fix the leak with a minimal change to processRequest(). The fix must
 *    guarantee cleanup even if the request processing throws an exception.
 *
 * 3. Implement verify() so that it:
 *      a. Sets a context on the current thread, then clears it via your fix.
 *      b. Uses a WeakReference to prove the RequestData object can be GC'd.
 *      c. Triggers GC and asserts the WeakReference has been cleared.
 *      d. Prints "PASS: context was garbage collected" or throws AssertionError.
 *
 * Do not change set(), get(), clear(), or processRequest()'s signature.
 * Do not modify anything below the cut line.
 */
public class RequestContext {

    static class RequestData {
        final String userId;
        final String traceId;

        RequestData(String userId, String traceId) {
            this.userId  = userId;
            this.traceId = traceId;
        }

        @Override
        public String toString() {
            return "RequestData{userId=" + userId + ", traceId=" + traceId + "}";
        }
    }

    // ---- Context holder (this is the leaky part) ----------------------------

    private static final ThreadLocal<RequestData> CONTEXT = new ThreadLocal<>();

    public static void set(RequestData data) { CONTEXT.set(data); }
    public static RequestData get()          { return CONTEXT.get(); }
    public static void clear()               { CONTEXT.remove(); }

    // ---- TODO: fix this method ----------------------------------------------

    public static void processRequest(String userId, String traceId) {
        set(new RequestData(userId, traceId));

        // Simulate the request doing some work
        System.out.println("Handling request: " + get());

        // TODO: where and how should cleanup happen?
    }

    // ---- TODO: implement verify() -------------------------------------------

    public static void verify() throws Exception {
        // Hint: WeakReference<RequestData> ref = new WeakReference<>(...)
        // TODO
        throw new AssertionError("verify() not implemented");
    }

    // -------------------------------------------------------------------------
    // Do not modify below this line.
    // -------------------------------------------------------------------------

    public static void main(String[] args) throws Exception {

        System.out.println("--- Simulating thread-pool server (5 threads, 20 requests) ---");

        ExecutorService pool = Executors.newFixedThreadPool(5);
        var futures = new ArrayList<Future<?>>();

        for (int i = 0; i < 20; i++) {
            final int reqNum = i;
            futures.add(pool.submit(() -> {
                processRequest("user-" + reqNum, "trace-" + reqNum);

                // After each request the context must be gone.
                // Stale context is both a memory leak AND a correctness bug:
                // the next request on this thread will inherit the wrong user.
                if (get() != null) {
                    throw new AssertionError(
                        "Context not cleared after request " + reqNum +
                        " on thread " + Thread.currentThread().getName()
                    );
                }
            }));
        }

        pool.shutdown();
        pool.awaitTermination(10, TimeUnit.SECONDS);

        // Surface any AssertionErrors thrown inside the pool threads.
        for (Future<?> f : futures) {
            try {
                f.get();
            } catch (ExecutionException e) {
                throw new AssertionError("Thread-pool task failed: " + e.getCause().getMessage(), e.getCause());
            }
        }

        System.out.println("\n--- Verifying GC eligibility after clear() ---");
        verify();

        System.out.println("\nAll checks passed.");
    }
}
