package org.spjain.bds.puzzles;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * INTERVIEW PROBLEM — Exception Handling & Resilience
 * ----------------------------------------------------
 * You are building a resilient layer that calls an external service
 * (a database, an HTTP endpoint, etc.). Calls can fail in two ways:
 *
 *   - Transiently: a network blip, a momentary overload — worth retrying.
 *   - Fatally: bad credentials, invalid request — retrying is pointless.
 *
 * TASKS
 * -----
 * 1. Complete the exception hierarchy below.
 *    AppException is the unchecked base. Add anything it needs.
 *    TransientException and FatalException should extend AppException.
 *
 * 2. Implement execute() so that:
 *      a. It calls operation.get() up to maxRetries times.
 *      b. On TransientException it pauses 100 ms, then retries.
 *      c. On FatalException it stops immediately and rethrows — no retries.
 *      d. Any other unexpected exception must be wrapped in AppException
 *         before rethrowing (never let a raw unexpected exception escape).
 *      e. If every attempt throws TransientException, throw a final
 *         TransientException with a message like "Failed after 3 attempts".
 *
 * 3. DataSource implements AutoCloseable. The main() test below opens one
 *    inside a lambda passed to execute(). Make sure it is always closed,
 *    even when execute() throws. Use try-with-resources (not finally).
 *
 * Do not change execute()'s signature or modify anything below the cut line.
 */
public class RetryExecutor {

    // ---- TODO: complete these exception classes ------------------------------

    static class AppException extends RuntimeException {
        // TODO: add constructors — at minimum: (String message) and (String message, Throwable cause)
        AppException(String message)                  { super(message); }
        AppException(String message, Throwable cause) { super(message, cause); }
    }

    static class TransientException extends AppException {
        // TODO: complete
        TransientException(String message) { super(message); }
    }

    static class FatalException extends AppException {
        // TODO: complete
        FatalException(String message) { super(message); }
    }

    // ---- TODO: implement execute() ------------------------------------------

    public static <T> T execute(Supplier<T> operation, int maxRetries) {
        // TODO
        return null;
    }

    // -------------------------------------------------------------------------
    // Do not modify below this line.
    // -------------------------------------------------------------------------

    static class DataSource implements AutoCloseable {
        final String name;
        boolean closed = false;

        DataSource(String name) { this.name = name; }

        String fetch() { return "data from " + name; }

        @Override
        public void close() {
            closed = true;
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Test 1: succeeds on 3rd attempt ---");
        testEventualSuccess();

        System.out.println("--- Test 2: fatal exception stops retries immediately ---");
        testFatalStopsRetries();

        System.out.println("--- Test 3: exhausts all retries ---");
        testExhaustsRetries();

        System.out.println("--- Test 4: DataSource is always closed ---");
        testDataSourceAlwaysClosed();

        System.out.println("All checks passed.");
    }

    private static void testEventualSuccess() {
        var attempts = new AtomicInteger(0);
        String result = execute(() -> {
            if (attempts.incrementAndGet() < 3) throw new TransientException("not yet");
            return "ok";
        }, 5);
        assertEqual("ok", result, "should return value on eventual success");
        assertEqual(3, attempts.get(), "should have taken exactly 3 attempts");
    }

    private static void testFatalStopsRetries() {
        var attempts = new AtomicInteger(0);
        try {
            execute(() -> {
                attempts.incrementAndGet();
                throw new FatalException("bad credentials");
            }, 5);
            throw new AssertionError("expected FatalException to propagate");
        } catch (FatalException e) {
            // expected
        }
        assertEqual(1, attempts.get(), "FatalException must not trigger retries");
    }

    private static void testExhaustsRetries() {
        final int maxRetries = 3;
        try {
            execute(() -> { throw new TransientException("always fails"); }, maxRetries);
            throw new AssertionError("expected TransientException after exhausting retries");
        } catch (TransientException e) {
            if (!e.getMessage().contains(String.valueOf(maxRetries))) {
                throw new AssertionError(
                    "message should mention attempt count, got: " + e.getMessage());
            }
        }
    }

    private static void testDataSourceAlwaysClosed() {
        // The DataSource is opened inside the lambda using try-with-resources.
        // execute() will exhaust retries and throw. The DataSource must still be closed.
        var ds = new DataSource("test-db");
        try {
            execute(() -> {
                try (var source = ds) {
                    source.fetch();
                    throw new TransientException("simulated failure");
                }
            }, 2);
        } catch (TransientException ignored) {}

        if (!ds.closed) {
            throw new AssertionError("DataSource was not closed after operation failure");
        }
        System.out.println("DataSource properly closed.");
    }

    private static void assertEqual(Object expected, Object actual, String message) {
        if (!expected.equals(actual)) {
            throw new AssertionError(
                message + " — expected: " + expected + ", got: " + actual);
        }
    }
}
