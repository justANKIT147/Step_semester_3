package main.java.constructors.assignment_problems;

/**
 * Week 4 - Problem 5: Instanceof and Payment Polymorphism (Base Class)
 * Represents a generic cash payment without surcharge.
 */
public class Payment {
    private double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    /**
     * Calculates the charge for a payment using instanceof to check if a 2% card fee applies.
     */
    public static double calculateCharge(Payment payment) {
        if (payment instanceof CardPayment) {
            return payment.getAmount() * 1.02;
        }
        return payment.getAmount();
    }

    public static void main(String[] args) {
        Payment[] payments = {
            new CardPayment(100),
            new Payment(50),
            new CardPayment(200),
            new Payment(75),
            new CardPayment(120)
        };

        double total = 0.0;
        System.out.println("Charges:");
        for (Payment p : payments) {
            double charge = calculateCharge(p);
            total += charge;
            // Format cleanly
            if (charge == (long) charge) {
                System.out.println((long) charge);
            } else {
                System.out.println(charge);
            }
        }

        System.out.println("Total: " + total);
    }
}
