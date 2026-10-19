package main.java.constructors.assignment_problems;

/**
 * Week 4 - Problem 3: Parking Ticket and Final Methods
 * Demonstrates final methods preventing override while enforcing rate calculation logic.
 */
public class ParkingTicket {
    private String vehicleNo;
    private int ratePerMinute;

    public ParkingTicket(String vehicleNo, int ratePerMinute) {
        this.vehicleNo = vehicleNo;
        this.ratePerMinute = ratePerMinute;
    }

    public final int calculateFine(int overstayMinutes) {
        if (overstayMinutes <= 0) {
            return 0;
        }
        return ratePerMinute * overstayMinutes;
    }

    public final void printReceipt(int overstayMinutes) {
        int fine = calculateFine(overstayMinutes);
        if (fine > 0) {
            System.out.println(fine);
        } else {
            System.out.println("No fine");
        }
    }

    public static void main(String[] args) {
        ParkingTicket[] tickets = {
            new ParkingTicket("TN09AB1234", 2),
            new ParkingTicket("TN22CD5678", 2),
            new ParkingTicket("TN09EF9012", 3),
            new ParkingTicket("TN10GH3456", 2)
        };

        int[] overstays = {15, 0, -5, 8};

        for (int i = 0; i < tickets.length; i++) {
            tickets[i].printReceipt(overstays[i]);
        }
    }
}
