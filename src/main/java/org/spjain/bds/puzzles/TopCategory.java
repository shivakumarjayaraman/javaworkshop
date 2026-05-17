package org.spjain.bds.puzzles;
import java.util.*;
import java.util.stream.*;

/**
 * INTERVIEW PROBLEM — Java Streams & Functional Programming
 * ---------------------------------------------------------
 * You have a list of Order records returned from a datastore query.
 * Each Order has a customerId, a product category, and a purchase amount.
 *
 * Implement topCategoryPerCustomer() so that it returns, for each customerId,
 * the name of the category they spent the most money on (their "top category").
 *
 * Rules:
 *   - Use the Stream API. Avoid writing explicit loops (for/while/do-while).
 *   - If a customer has equal spending in two categories, return either one.
 *   - An empty list should return an empty map.
 *
 * Hint: look at Collectors.groupingBy and Collectors.toMap.
 */
public class TopCategory {

    record Order(String customerId, String category, double amount) {}

    public static Map<String, String> topCategoryPerCustomer(List<Order> orders) {
        // TODO: implement this
        return null;
    }

    // -------------------------------------------------------------------------
    // Do not modify below this line.
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        var orders = List.of(
            new Order("alice", "electronics", 300.0),
            new Order("alice", "books",       150.0),
            new Order("alice", "electronics", 200.0),
            new Order("bob",   "clothing",     80.0),
            new Order("bob",   "books",        200.0),
            new Order("bob",   "books",         50.0)
        );

        var result = topCategoryPerCustomer(orders);

        check("alice", "electronics", result);
        check("bob",   "books",       result);
        checkEmpty();

        System.out.println("All checks passed.");
    }

    private static void checkEmpty() {
        var result = topCategoryPerCustomer(List.of());
        if (result == null || !result.isEmpty()) {
            throw new AssertionError("Expected empty map for empty input, got: " + result);
        }
    }

    private static void check(String customer, String expectedCategory, Map<String, String> result) {
        if (result == null) {
            throw new AssertionError("topCategoryPerCustomer returned null");
        }
        var actual = result.get(customer);
        if (!expectedCategory.equals(actual)) {
            throw new AssertionError(
                "For customer '" + customer + "': expected '" + expectedCategory + "' but got '" + actual + "'"
            );
        }
    }
}
