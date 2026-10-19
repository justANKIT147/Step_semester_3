package main.java.algorithms.assignment_problems;

/**
 * Week 9 - Problem 4: Hot Weather Alert Windows
 *
 * Algorithm Complexity:
 * - Time Complexity: O(N), where N is the number of temperature readings (up to 100,000).
 *   The sliding window computes the sum of the first k elements in O(k), then slides across
 *   the remaining N - k elements in O(1) per step.
 * - Auxiliary Space Complexity: O(1). Only a running sum and counter are maintained.
 *
 * Numerical Stability:
 * - Compares windowSum >= (long) k * threshold to avoid integer overflow and floating-point errors.
 */
public class HotWeatherAlert {

    /**
     * Counts the number of contiguous windows of length k with average >= threshold.
     *
     * @param readings  array of temperature readings
     * @param k         window length
     * @param threshold average threshold
     * @return count of alert windows
     */
    public static int countHotWeatherWindows(int[] readings, int k, double threshold) {
        if (readings == null || k <= 0 || readings.length < k) {
            return 0;
        }

        long requiredSum = (long) Math.ceil(k * threshold);
        long currentWindowSum = 0;

        // Calculate initial window sum
        for (int i = 0; i < k; i++) {
            currentWindowSum += readings[i];
        }

        int count = 0;
        if (currentWindowSum >= requiredSum) {
            count++;
        }

        // Slide window
        for (int i = k; i < readings.length; i++) {
            currentWindowSum += readings[i] - readings[i - k];
            if (currentWindowSum >= requiredSum) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] readings = {2, 2, 2, 2, 5, 5, 5, 8};
        int k = 3;
        double threshold = 4.0;

        int result = countHotWeatherWindows(readings, k, threshold);
        System.out.println("Result: " + result);
        System.out.println("Expected: 3");
    }
}
