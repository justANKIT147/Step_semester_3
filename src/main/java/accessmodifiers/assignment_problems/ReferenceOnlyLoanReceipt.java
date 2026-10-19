package main.java.accessmodifiers.assignment_problems;

import java.util.Arrays;

/**
 * Week 5 - Problem 5: Reference Only Loan Receipt
 * Represents a reference-only loan receipt. Due to Java's restriction against extending
 * the final class LoanReceipt, this class is modeled independently to preserve language validity.
 */
public class ReferenceOnlyLoanReceipt {
    private final String receiptId;
    private final String memberId;
    private final String[] bookIds;

    public ReferenceOnlyLoanReceipt(String receiptId, String memberId, String[] bookIds) {
        this.receiptId = receiptId;
        this.memberId = memberId;
        if (bookIds != null) {
            this.bookIds = Arrays.copyOf(bookIds, bookIds.length);
        } else {
            this.bookIds = new String[0];
        }
    }

    public String getReceiptId() {
        return receiptId;
    }

    public String getMemberId() {
        return memberId;
    }

    public String[] getBookIds() {
        return Arrays.copyOf(bookIds, bookIds.length);
    }
}
