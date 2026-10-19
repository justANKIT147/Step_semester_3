package main.java.accessmodifiers.assignment_problems;

/**
 * Week 5 - Problem 5: Circulation Ledger
 * Performs nightly processing of loan receipts in a single pass with O(1) extra space.
 */
public class CirculationLedger {

    /**
     * Processes receipts array in a single pass, distinguishing types via instanceof
     * and counting processed, null, reference-only, and regular receipts.
     *
     * @param receipts array of receipt objects (may contain LoanReceipt, ReferenceOnlyLoanReceipt, or null)
     * @return summary string in required format:
     *         "X processed | Y null skipped | Z reference-only | W regular"
     */
    public static String processNightlyCirculation(Object[] receipts) {
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        if (receipts != null) {
            for (Object r : receipts) {
                if (r == null) {
                    nullSkipped++;
                } else if (r instanceof ReferenceOnlyLoanReceipt) {
                    referenceOnly++;
                } else if (r instanceof LoanReceipt) {
                    regular++;
                }
            }
        }

        int processed = referenceOnly + regular;
        return processed + " processed | " + nullSkipped + " null skipped | " + referenceOnly + " reference-only | " + regular + " regular";
    }

    /**
     * Type-safe overload for LoanReceipt[] arrays.
     */
    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        return processNightlyCirculation((Object[]) receipts);
    }

    public static void main(String[] args) {
        LoanReceipt regular = new LoanReceipt("REC-01", "MEM-101", new String[]{"B1", "B2"});
        ReferenceOnlyLoanReceipt refOnly = new ReferenceOnlyLoanReceipt("REC-02", "MEM-102", new String[]{"REF-1"});

        Object[] nightlyBatch = {regular, null, refOnly};
        String summary = processNightlyCirculation(nightlyBatch);
        System.out.println(summary);

        // Verify defensive copying and withCorrectedBookId
        LoanReceipt corrected = regular.withCorrectedBookId(1, "B99");
        System.out.println("Original book 1: " + regular.getBookIds()[1]);
        System.out.println("Corrected book 1: " + corrected.getBookIds()[1]);
    }
}
