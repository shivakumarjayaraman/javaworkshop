package org.spjain.bds.puzzles;
import java.util.*;

/**
 * INTERVIEW PROBLEM — OOP & Design Patterns
 * ------------------------------------------
 * We have a search service that needs to look up products.
 * Different request types are handled by different backends:
 *
 *   - An ID lookup (searching by a known product ID) should go to MongoProvider.
 *   - A full-text search (searching by a keyword phrase) should go to ElasticProvider.
 *
 * Your task:
 *   1. Define a SearchRequest type that carries enough information to distinguish
 *      the two cases above (you decide its shape).
 *   2. Define a SearchProvider interface with two methods:
 *        - boolean canHandle(SearchRequest request)
 *        - List<String> search(SearchRequest request)
 *      The return type List<String> represents product IDs found (stubs are fine).
 *   3. Implement MongoProvider and ElasticProvider as stubs — they do not need
 *      real DB calls. Print which provider ran and return a dummy list.
 *   4. Implement the search(SearchRequest) method on SearchService so that it:
 *        - Finds the first provider that can handle the request.
 *        - Delegates to it and returns the result.
 *        - Throws an IllegalArgumentException if no provider can handle it.
 *   5. Wire everything together in main() and demonstrate both providers being
 *      called with the correct request type.
 *
 * Constraints:
 *   - SearchService must NOT contain an if/else chain that mentions MongoProvider
 *     or ElasticProvider by name. It should work with any provider you add later.
 *   - Adding a third provider in the future should require zero changes to SearchService.
 */
public class SearchService {

    // TODO: define SearchRequest

    // TODO: define SearchProvider interface

    // TODO: implement MongoProvider

    // TODO: implement ElasticProvider

    // TODO: implement search() here
    public List<String> search(/* SearchRequest request */) {
        return null;
    }

    // -------------------------------------------------------------------------
    // Do not modify main() — wire up your classes so these calls work as-is.
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        // Uncomment and adapt once you have defined SearchRequest:
        //
        // var service = new SearchService(List.of(new MongoProvider(), new ElasticProvider()));
        //
        // var idResult   = service.search(SearchRequest.byId("product-42"));
        // System.out.println("ID lookup result:        " + idResult);
        //
        // var textResult = service.search(SearchRequest.fullText("noise cancelling headphones"));
        // System.out.println("Full-text search result: " + textResult);
        //
        // Expected console output (stub values are fine, routing must be correct):
        //   [MongoProvider] handling id lookup for: product-42
        //   ID lookup result:        [product-42]
        //   [ElasticProvider] handling full-text search for: noise cancelling headphones
        //   Full-text search result: [product-es-1, product-es-2]

        System.out.println("Uncomment the lines in main() once your implementation is ready.");
    }
}
