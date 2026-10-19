package main.java.inheritance.assignment_problems;

/**
 * Week 6 - Problem 1 & 3: PremiumMember subclass
 * Extends GymMember, receiving a 50% discount on late fees via super delegation.
 */
public class PremiumMember extends GymMember {

    public PremiumMember(String memberId, int monthlyFee) {
        super(memberId, monthlyFee);
    }

    @Override
    public void chargeLateFee(int amount) {
        // Halves the fee and calls super.chargeLateFee without duplicating recording logic
        int discountedFee = amount / 2;
        super.chargeLateFee(discountedFee);
    }

    @Override
    public String displayInfo() {
        return "PremiumMember #" + getMembershipNumber() + " [" + getMemberId() + "] - Fee: " + getMonthlyFee() + " | Sessions: " + getSessionsAttended();
    }
}
