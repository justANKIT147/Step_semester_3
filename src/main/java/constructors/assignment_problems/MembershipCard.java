package main.java.constructors.assignment_problems;

/**
 * Week 4 - Problem 4: Static Block and Membership Card
 * Demonstrates static block execution during class initialization
 * and shared static state across multiple instances.
 */
public class MembershipCard {
    public static String libraryName;
    public static String validUntil;

    private String memberName;

    static {
        libraryName = "SRM Central Library";
        validUntil = "May 2027";
        System.out.println("Library info loaded");
    }

    public MembershipCard(String memberName) {
        this.memberName = memberName;
    }

    public void displayCard() {
        System.out.println("Member: " + memberName + " | Library: " + libraryName + " | Valid Until: " + validUntil);
    }

    public static void main(String[] args) {
        String[] members = {"Ananya", "Rohan", "Priya", "Arjun", "Sneha"};
        MembershipCard[] cards = new MembershipCard[members.length];

        for (int i = 0; i < members.length; i++) {
            cards[i] = new MembershipCard(members[i]);
            cards[i].displayCard();
        }
    }
}
