package main.java.accessmodifiers.assignment_problems;

import java.util.Arrays;

/**
 * Week 5 - Problem 5: Immutable Loan Receipt
 * Final class with deeply immutable state and defensive copying.
 *
 * NOTE ON SPECIFICATION CONTRADICTION:
 * The assignment asks for ReferenceOnlyLoanReceipt to extend LoanReceipt, while
 * simultaneously requiring LoanReceipt to be declared final. In Java, extending a final
 * class causes a compilation error (cannot inherit from final LoanReceipt).
 * To comply with Java language specifications while fulfilling functional requirements,
 * LoanReceipt remains final, and ReferenceOnlyLoanReceipt is implemented as a separate
 * compatible class.
 */
public final class LoanReceipt {
    private final String receiptId;
    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String receiptId, String memberId, String[] bookIds) {
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

    /**
     * Returns a new LoanReceipt instance with the updated book ID at the specified index,
     * preserving immutability.
     */
    public LoanReceipt withCorrectedBookId(int index, String newBookId) {
        if (index < 0 || index >= bookIds.length) {
            throw new IndexOutOfBoundsException("Invalid book index: " + index);
        }
        String[] newBooks = Arrays.copyOf(bookIds, bookIds.length);
        newBooks[index] = newBookId;
        return new LoanReceipt(this.receiptId, this.memberId, newBooks);
    }
}
