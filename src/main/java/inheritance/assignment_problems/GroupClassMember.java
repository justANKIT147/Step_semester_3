package main.java.inheritance.assignment_problems;

/**
 * Week 6 - Problem 2: GroupClassMember subclass (hierarchical sibling)
 * Extends GymMember directly, participating in group studio classes.
 */
public class GroupClassMember extends GymMember {
    private final String className;

    public GroupClassMember(String memberId, int monthlyFee, String className) {
        super(memberId, monthlyFee);
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String displayInfo() {
        return "GroupClassMember #" + getMembershipNumber() + " [" + getMemberId() + "] - Fee: " + getMonthlyFee()
                + " | Class: " + className + " | Sessions: " + getSessionsAttended();
    }
}
