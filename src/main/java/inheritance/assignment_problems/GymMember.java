package main.java.inheritance.assignment_problems;

import java.util.Arrays;

/**
 * Week 6 - Base class for Gym Membership hierarchy.
 * Handles validation, fee payments, session tracking, late fee recording,
 * and batch processing utilities.
 */
public class GymMember {
    private static int membershipCounter = 1000;
    private static int membersEnrolled = 0;

    private final int membershipNumber;
    private final String memberId;
    private final int monthlyFee;
    private int sessionsAttended;
    private int feesPaid;

    private final int[] lateFees = new int[10];
    private int lateFeeCount = 0;

    public GymMember(String memberId, int monthlyFee) {
        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid memberId: must not be blank and must have at least 4 characters.");
        }
        if (monthlyFee <= 0) {
            throw new IllegalArgumentException("Monthly fee must be positive.");
        }

        this.memberId = memberId.trim();
        this.monthlyFee = monthlyFee;
        this.membershipNumber = ++membershipCounter;
        membersEnrolled++;
    }

    public String getMemberId() {
        return memberId;
    }

    public int getMonthlyFee() {
        return monthlyFee;
    }

    public int getMembershipNumber() {
        return membershipNumber;
    }

    public static int getMembersEnrolled() {
        return membersEnrolled;
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public void payFee(int amount) {
        if (amount > 0) {
            feesPaid += amount;
        }
    }

    public void payFee(int amount, String mode) {
        // Delegate to single-argument method to avoid duplication
        payFee(amount);
    }

    public int getFeesPaid() {
        return feesPaid;
    }

    protected void chargeLateFee(int amount) {
        if (amount > 0 && lateFeeCount < lateFees.length) {
            lateFees[lateFeeCount++] = amount;
        }
    }

    public int[] getLateFeeHistory() {
        return Arrays.copyOf(lateFees, lateFeeCount);
    }

    public int getTotalLateFees() {
        int sum = 0;
        for (int i = 0; i < lateFeeCount; i++) {
            sum += lateFees[i];
        }
        return sum;
    }

    public String displayInfo() {
        return "GymMember #" + membershipNumber + " [" + memberId + "] - Fee: " + monthlyFee + " | Sessions: " + sessionsAttended;
    }

    // Static helper methods for assignment problems

    /**
     * Problem 1: Batch sign-up using try/catch without prevalidation.
     */
    public static String signUpBatch(String[] memberIds, int monthlyFee) {
        int signedUp = 0;
        int rejected = 0;

        if (memberIds != null) {
            for (String id : memberIds) {
                try {
                    new GymMember(id, monthlyFee);
                    signedUp++;
                } catch (IllegalArgumentException e) {
                    rejected++;
                }
            }
        }

        return "Signed Up: " + signedUp + " | Rejected: " + rejected;
    }

    /**
     * Problem 2: Classify generation depth using instanceof.
     */
    public static String classifyGeneration(GymMember member) {
        if (member instanceof EliteMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof GroupClassMember) {
            return "Hierarchical sibling (independent branch)";
        } else if (member instanceof PremiumMember) {
            return "Direct subclass (2 generations deep)";
        } else if (member != null) {
            return "Root member (1 generation deep)";
        }
        return "Unknown";
    }

    /**
     * Problem 2: Polymorphic total sessions attended.
     */
    public static int getTotalSessionsAttended(GymMember[] members) {
        int total = 0;
        if (members != null) {
            for (GymMember m : members) {
                if (m != null) {
                    total += m.getSessionsAttended();
                }
            }
        }
        return total;
    }

    /**
     * Problem 4: Monthly attendance announcement batch print.
     */
    public static String batchPrint(GymMember[] members) {
        StringBuilder sb = new StringBuilder();
        if (members != null) {
            for (int i = 0; i < members.length; i++) {
                GymMember m = members[i];
                if (m != null) {
                    sb.append(m.displayInfo());
                    if (m instanceof PremiumMember) {
                        sb.append(" [Premium Benefits Active]");
                    }
                    if (i < members.length - 1) {
                        sb.append("\n");
                    }
                }
            }
        }
        return sb.toString();
    }

    /**
     * Problem 5: Validates referral code format: 'G' + 2 digits + 1 uppercase letter without regex.
     */
    public static boolean isValidReferralCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        if (code.charAt(0) != 'G') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2))) {
            return false;
        }
        if (!Character.isUpperCase(code.charAt(3))) {
            return false;
        }
        return true;
    }

    /**
     * Problem 5: Weekly check-in settlement batch processor.
     */
    public static String processWeeklyCheckIn(GymMember[] members) {
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        if (members != null) {
            for (GymMember m : members) {
                if (m == null) {
                    nullSkipped++;
                } else if (m instanceof GroupClassMember) {
                    group++;
                } else {
                    individual++;
                }
            }
        }

        int processed = group + individual;
        return processed + " processed | " + nullSkipped + " null skipped | " + group + " group | " + individual + " individual";
    }
}
