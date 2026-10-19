package main.java.inheritance.assignment_problems;

/**
 * Week 6 - Problem 2: EliteMember subclass (3 generations deep)
 * Extends PremiumMember, adding dedicated trainer and locker assignment.
 */
public class EliteMember extends PremiumMember {
    private final String trainerName;
    private final int lockerNumber;

    public EliteMember(String memberId, int monthlyFee, String trainerName, int lockerNumber) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
        this.lockerNumber = lockerNumber;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public int getLockerNumber() {
        return lockerNumber;
    }

    @Override
    public String displayInfo() {
        return "EliteMember #" + getMembershipNumber() + " [" + getMemberId() + "] - Fee: " + getMonthlyFee()
                + " | Trainer: " + trainerName + " | Locker: " + lockerNumber + " | Sessions: " + getSessionsAttended();
    }
}
