package main.java.algorithms.assignment_problems;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Week 9 - Problem 3: Most Popular Canteen Order
 *
 * Algorithm Complexity:
 * - Time Complexity: O(N), where N is the total number of orders (up to 100,000).
 *   LinkedHashMap provides O(1) average time per insertion and lookup.
 * - Auxiliary Space Complexity: O(U), where U is the number of unique items (U <= N).
 *
 * Complexity Comparison with Naive Approach:
 * - Naive Approach: For each order in the array, scan the entire array to count occurrences.
 *   This requires O(N^2) time. For N = 100,000, N^2 = 10,000,000,000 operations, causing
 *   unacceptable CPU bottleneck.
 * - Optimized Approach: Using LinkedHashMap completes in O(N) time and automatically
 *   preserves order of first appearance to resolve ties in O(1) extra work.
 */
public class PopularCanteenOrderFinder {

    public static class PopularOrderResult {
        public final String item;
        public final int count;

        public PopularOrderResult(String item, int count) {
            this.item = item;
            this.count = count;
        }

        @Override
        public String toString() {
            return "(\"" + item + "\", " + count + ")";
        }
    }

    /**
     * Finds the item with the highest frequency.
     * Ties are broken in favor of the item that appeared first.
     *
     * @param orders array of order strings
     * @return PopularOrderResult
     */
    public static PopularOrderResult findMostPopularOrder(String[] orders) {
        if (orders == null || orders.length == 0) {
            return new PopularOrderResult("", 0);
        }

        // LinkedHashMap preserves insertion order (first appearance)
        Map<String, Integer> freqMap = new LinkedHashMap<>();
        for (String order : orders) {
            if (order != null) {
                freqMap.put(order, freqMap.getOrDefault(order, 0) + 1);
            }
        }

        String mostPopular = null;
        int maxCount = -1;

        // Iterate in insertion order: strictly greater ensures first appearance wins ties
        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostPopular = entry.getKey();
            }
        }

        return new PopularOrderResult(mostPopular, maxCount);
    }

    public static void main(String[] args) {
        String[] orders = {"dosa", "idli", "vada", "dosa", "idli", "dosa", "tea"};
        System.out.println("Result: " + findMostPopularOrder(orders));
        System.out.println("Expected: (\"dosa\", 3)");

        String[] tieOrders = {"tea", "coffee", "coffee", "tea"};
        System.out.println("Tie result: " + findMostPopularOrder(tieOrders));
        System.out.println("Expected: (\"tea\", 2)");
    }
}
