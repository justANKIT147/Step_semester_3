package main.java.algorithms.assignment_problems;

/**
 * Week 9 - Problem 5: Ticket Price Slot Finder
 *
 * Algorithm Complexity:
 * - Time Complexity: O(log N), where N is the length of the sorted price array (0 <= N <= 100,000).
 *   Halves the search range in each iteration.
 * - Auxiliary Space Complexity: O(1). Uses two pointer bounds (low, high).
 *
 * Comparison with Linear Search:
 * - Linear search takes O(N) comparisons (up to 100,000 iterations in the worst case).
 * - Binary search takes O(log2 N) comparisons (at most 17 iterations for 100,000 elements).
 */
public class TicketPriceSlotFinder {

    /**
     * Finds the index of the target price if found, or the insertion slot index
     * where it should be placed to maintain sorted order.
     *
     * @param prices sorted array of distinct ticket prices
     * @param target target price to locate or insert
     * @return index of target or insertion slot index
     */
    public static int findPriceSlot(int[] prices, int target) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int low = 0;
        int high = prices.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (prices[mid] == target) {
                return mid;
            } else if (prices[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // When not found, low points to insertion position
        return low;
    }

    public static void main(String[] args) {
        int[] prices = {120, 150, 200, 260};

        System.out.println("Target 150 -> " + findPriceSlot(prices, 150) + " (Expected: 1)");
        System.out.println("Target 210 -> " + findPriceSlot(prices, 210) + " (Expected: 3)");
        System.out.println("Target 300 -> " + findPriceSlot(prices, 300) + " (Expected: 4)");
        System.out.println("Target 100 -> " + findPriceSlot(prices, 100) + " (Expected: 0)");

        // Empty array edge case
        System.out.println("Empty array target 50 -> " + findPriceSlot(new int[0], 50) + " (Expected: 0)");
    }
}
