package main.java.algorithms.assignment_problems;

/**
 * Week 9 - Problem 1: Class Topper Finder
 *
 * Algorithm Complexity:
 * - Time Complexity: O(N * M), where N is the number of students (1 <= N <= 500)
 *   and M is the number of subjects (1 <= M <= 10). Each mark is visited exactly once.
 * - Auxiliary Space Complexity: O(1). Only a few primitive variables are maintained.
 */
public class ClassTopperFinder {

    public static class TopperResult {
        public final int studentIndex;
        public final int highestTotal;

        public TopperResult(int studentIndex, int highestTotal) {
            this.studentIndex = studentIndex;
            this.highestTotal = highestTotal;
        }

        @Override
        public String toString() {
            return "(" + studentIndex + ", " + highestTotal + ")";
        }
    }

    /**
     * Finds the topper's 0-based row index and total marks.
     * In case of ties, the lowest row index is chosen.
     *
     * @param marks 2D array where each row represents a student and each column a subject.
     * @return TopperResult containing (rowIndex, highestTotal)
     */
    public static TopperResult findClassTopper(int[][] marks) {
        if (marks == null || marks.length == 0) {
            return new TopperResult(-1, 0);
        }

        int topperIndex = 0;
        int maxTotal = -1;

        for (int i = 0; i < marks.length; i++) {
            int currentTotal = 0;
            if (marks[i] != null) {
                for (int mark : marks[i]) {
                    currentTotal += mark;
                }
            }

            // Strictly greater than to break ties in favor of lower row index
            if (currentTotal > maxTotal) {
                maxTotal = currentTotal;
                topperIndex = i;
            }
        }

        return new TopperResult(topperIndex, maxTotal);
    }

    public static void main(String[] args) {
        // Students with totals:
        // Student 0: 80 + 85 + 88 = 253
        // Student 1: 90 + 80 + 89 = 259
        // Student 2: 70 + 75 + 85 = 230
        int[][] marks = {
            {80, 85, 88},
            {90, 80, 89},
            {70, 75, 85}
        };

        TopperResult result = findClassTopper(marks);
        System.out.println("Expected: (1, 259) | Actual: " + result);

        // Tie test case: Student 0 and Student 2 both have 200 -> ties select lowest index 0
        int[][] ties = {
            {100, 100},
            {90, 90},
            {100, 100}
        };
        System.out.println("Tie test: " + findClassTopper(ties));
    }
}
