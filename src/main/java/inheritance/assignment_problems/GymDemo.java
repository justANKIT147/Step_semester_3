package main.java.inheritance.assignment_problems;

import java.util.Arrays;

/**
 * Week 6 - Demonstration and Test Harness for all 5 problems.
 */
public class GymDemo {

    public static void main(String[] args) {
        System.out.println("=== Problem 1: Gym Membership Foundation Batch Sign-Up ===");
        String[] batchIds = {"MEM1", "GM1", "MEM2", " ", "MEM3"};
        String batchResult = GymMember.signUpBatch(batchIds, 1200);
        System.out.println(batchResult); // Expected: Signed Up: 3 | Rejected: 2

        System.out.println("\n=== Problem 2: Three Membership Tiers & Polymorphic Totals ===");
        GymMember gm = new GymMember("MEM10", 1000);
        PremiumMember pm = new PremiumMember("PM20", 1500);
        EliteMember em = new EliteMember("EL30", 2500, "Coach Vikram", 104);
        GroupClassMember gcm = new GroupClassMember("GC40", 1200, "Zumba");

        gm.attendSession();
        gm.attendSession(); // 2
        pm.attendSession();
        pm.attendSession();
        pm.attendSession(); // 3
        em.attendSession();
        em.attendSession(); // 2
        gcm.attendSession();
        gcm.attendSession(); // 2

        GymMember[] members = {gm, pm, em, gcm};
        System.out.println("Elite generation: " + GymMember.classifyGeneration(em));
        System.out.println("GroupClass generation: " + GymMember.classifyGeneration(gcm));
        System.out.println("Total sessions attended: " + GymMember.getTotalSessionsAttended(members)); // 2 + 3 + 2 + 2 = 9

        System.out.println("\n=== Problem 3: Late Fee Discount and History ===");
        pm.chargeLateFee(200);
        System.out.println("Late fee history for PremiumMember (200 charged -> halved to 100): " + Arrays.toString(pm.getLateFeeHistory()));
        System.out.println("Total late fees: " + pm.getTotalLateFees());

        System.out.println("\n=== Problem 4: Monthly Attendance Announcement Batch Print ===");
        System.out.println(GymMember.batchPrint(members));

        System.out.println("\n=== Problem 5: Referral Codes & Check-In Settlement ===");
        System.out.println("G45B valid: " + GymMember.isValidReferralCode("G45B")); // true
        System.out.println("G4B valid: " + GymMember.isValidReferralCode("G4B"));   // false
        System.out.println("X45B valid: " + GymMember.isValidReferralCode("X45B")); // false

        // Settlement with 1 regular, 1 group, 1 null
        GymMember indMember = new GymMember("IND1", 1000);
        GymMember grpMember = new GroupClassMember("GRP1", 1200, "Spinning");
        GymMember[] checkInList = {indMember, null, grpMember};

        String checkInSummary = GymMember.processWeeklyCheckIn(checkInList);
        System.out.println(checkInSummary); // Expected: 2 processed | 1 null skipped | 1 group | 1 individual
    }
}
