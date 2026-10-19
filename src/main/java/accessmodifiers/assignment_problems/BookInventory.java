package main.java.accessmodifiers.assignment_problems;

/**
 * Week 5 - Problem 3: Encapsulated Book Inventory
 * Encapsulates total and available inventory count, guaranteeing class invariants.
 */
public class BookInventory {
    private int copiesTotal;
    private int copiesAvailable;

    public BookInventory(int copiesTotal) {
        if (copiesTotal < 0) {
            throw new IllegalArgumentException("Total copies cannot be negative");
        }
        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    public BookInventory(int copiesTotal, int copiesAvailable) {
        if (copiesTotal < 0 || copiesAvailable < 0 || copiesAvailable > copiesTotal) {
            throw new IllegalArgumentException("Invalid inventory counts: available must be within [0, total]");
        }
        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesAvailable;
    }

    /**
     * Checks out one copy if available.
     *
     * @return true if successful, false if no copies are available
     */
    public boolean checkOut() {
        if (copiesAvailable > 0) {
            copiesAvailable--;
            return true;
        }
        System.out.println("Checkout rejected: no copies available.");
        return false;
    }

    /**
     * Checks in one copy if under the total limit.
     *
     * @return true if successful, false if already at maximum capacity
     */
    public boolean checkIn() {
        if (copiesAvailable < copiesTotal) {
            copiesAvailable++;
            return true;
        }
        System.out.println("Check-in rejected: inventory already at full capacity.");
        return false;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public int getCopiesTotal() {
        return copiesTotal;
    }

    public static void main(String[] args) {
        BookInventory inv = new BookInventory(5);
        System.out.println("Initial available: " + inv.getCopiesAvailable());

        inv.checkOut();
        inv.checkOut();
        System.out.println("After 2 checkouts: " + inv.getCopiesAvailable());

        inv.checkIn();
        System.out.println("After 1 checkin: " + inv.getCopiesAvailable());

        // Boundary checks
        inv.checkIn();
        inv.checkIn();
        inv.checkIn(); // reaches 5
        inv.checkIn(); // should reject
    }
}
