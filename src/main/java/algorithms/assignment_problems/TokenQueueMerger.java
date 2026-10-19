package main.java.algorithms.assignment_problems;

import java.util.Arrays;

/**
 * Week 9 - Problem 2: Merging Two Token Queues
 *
 * Algorithm Complexity:
 * - Time Complexity: O(N + M), where N and M are the sizes of the two input queues.
 *   Each element is processed exactly once using two pointers.
 * - Space Complexity: O(N + M) auxiliary space for the merged output array.
 * - Method: Two-pointer merge without concatenating and sorting.
 */
public class TokenQueueMerger {

    /**
     * Merges two sorted ascending integer arrays using a two-pointer approach,
     * preserving duplicates.
     *
     * @param queueA first sorted array
     * @param queueB second sorted array
     * @return merged sorted array
     */
    public static int[] mergeTokenQueues(int[] queueA, int[] queueB) {
        if (queueA == null || queueA.length == 0) {
            return queueB == null ? new int[0] : Arrays.copyOf(queueB, queueB.length);
        }
        if (queueB == null || queueB.length == 0) {
            return Arrays.copyOf(queueA, queueA.length);
        }

        int n = queueA.length;
        int m = queueB.length;
        int[] merged = new int[n + m];

        int i = 0; // pointer for queueA
        int j = 0; // pointer for queueB
        int k = 0; // pointer for merged

        while (i < n && j < m) {
            if (queueA[i] <= queueB[j]) {
                merged[k++] = queueA[i++];
            } else {
                merged[k++] = queueB[j++];
            }
        }

        while (i < n) {
            merged[k++] = queueA[i++];
        }

        while (j < m) {
            merged[k++] = queueB[j++];
        }

        return merged;
    }

    public static void main(String[] args) {
        int[] q1 = {3, 8, 15, 20};
        int[] q2 = {5, 8, 12};
        int[] result = mergeTokenQueues(q1, q2);
        System.out.println("Result: " + Arrays.toString(result));
        System.out.println("Expected: [3, 5, 8, 8, 12, 15, 20]");

        // Empty list test
        int[] empty = {};
        int[] q3 = {4, 9};
        System.out.println("Empty test: " + Arrays.toString(mergeTokenQueues(empty, q3)));
    }
}
