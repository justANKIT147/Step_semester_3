package main.java.systemdesign.assignment_problems;

import java.time.LocalDate;

/**
 * Week 8 - Problem 4: FitZone Membership Desk
 * Manages membership plans, pricing discounts, and lifecycle state transitions.
 */
public class FitzoneMembershipDesk {

    public static class Member {
        private final String id;
        private final String name;

        public Member(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() { return id; }
        public String getName() { return name; }
    }

    public interface MembershipPlan {
        String getPlanName();
        int getDurationMonths();
        int calculateTotalPrice(int baseMonthlyRate);
    }

    public static class MonthlyPlan implements MembershipPlan {
        @Override public String getPlanName() { return "Monthly"; }
        @Override public int getDurationMonths() { return 1; }
        @Override public int calculateTotalPrice(int baseMonthlyRate) { return baseMonthlyRate; }
    }

    public static class QuarterlyPlan implements MembershipPlan {
        @Override public String getPlanName() { return "Quarterly"; }
        @Override public int getDurationMonths() { return 3; }
        @Override public int calculateTotalPrice(int baseMonthlyRate) {
            int gross = baseMonthlyRate * 3;
            return (int) (gross * 0.90); // 10% discount: 2700
        }
    }

    public static class AnnualPlan implements MembershipPlan {
        @Override public String getPlanName() { return "Annual"; }
        @Override public int getDurationMonths() { return 12; }
        @Override public int calculateTotalPrice(int baseMonthlyRate) {
            int gross = baseMonthlyRate * 12;
            return (int) (gross * 0.75); // 25% discount: 9000
        }
    }

    // Extensible Half-Yearly plan
    public static class HalfYearlyPlan implements MembershipPlan {
        @Override public String getPlanName() { return "Half-Yearly"; }
        @Override public int getDurationMonths() { return 6; }
        @Override public int calculateTotalPrice(int baseMonthlyRate) {
            int gross = baseMonthlyRate * 6;
            return (int) (gross * 0.85); // 15% discount: 5100
        }
    }

    public enum MembershipStatus {
        ACTIVE,
        FROZEN,
        EXPIRED
    }

    public static class Membership {
        private final Member member;
        private final MembershipPlan plan;
        private final LocalDate startDate;
        private final int totalAmount;
        private MembershipStatus status;

        public Membership(Member member, MembershipPlan plan, LocalDate startDate, int baseRate) {
            this.member = member;
            this.plan = plan;
            this.startDate = startDate;
            this.totalAmount = plan.calculateTotalPrice(baseRate);
            this.status = MembershipStatus.ACTIVE;
        }

        public Member getMember() { return member; }
        public MembershipPlan getPlan() { return plan; }
        public int getTotalAmount() { return totalAmount; }
        public MembershipStatus getStatus() { return status; }

        public boolean checkIn() {
            if (status == MembershipStatus.ACTIVE) {
                System.out.println("Check-in approved for " + member.getName() + " (" + plan.getPlanName() + ").");
                return true;
            }
            System.out.println("Check-in denied for " + member.getName() + ": membership is " + status + ".");
            return false;
        }

        public boolean freeze() {
            if (status == MembershipStatus.ACTIVE) {
                this.status = MembershipStatus.FROZEN;
                System.out.println("Membership frozen for " + member.getName() + ".");
                return true;
            }
            System.out.println("Freeze rejected for " + member.getName() + ": only ACTIVE memberships can be frozen (current: " + status + ").");
            return false;
        }

        public boolean unfreeze() {
            if (status == MembershipStatus.FROZEN) {
                this.status = MembershipStatus.ACTIVE;
                System.out.println("Membership unfrozen for " + member.getName() + ".");
                return true;
            }
            System.out.println("Unfreeze rejected for " + member.getName() + ": membership is not frozen.");
            return false;
        }

        public void expire() {
            this.status = MembershipStatus.EXPIRED;
            System.out.println("Membership expired for " + member.getName() + ".");
        }
    }

    public static void main(String[] args) {
        int baseMonthly = 1000;
        Member asha = new Member("M1", "Asha");
        Member ravi = new Member("M2", "Ravi");

        // Asha purchases Quarterly (Rs 2700)
        Membership ashaMem = new Membership(asha, new QuarterlyPlan(), LocalDate.now(), baseMonthly);
        System.out.println("Asha plan cost: Rs " + ashaMem.getTotalAmount());

        // Ravi purchases Monthly (Rs 1000)
        Membership raviMem = new Membership(ravi, new MonthlyPlan(), LocalDate.now(), baseMonthly);
        System.out.println("Ravi plan cost: Rs " + raviMem.getTotalAmount());

        // Check-in
        ashaMem.checkIn();
        raviMem.checkIn();

        // Freeze Asha and reject check-in while frozen
        ashaMem.freeze();
        ashaMem.checkIn();

        // Expire Ravi and reject freezing
        raviMem.expire();
        raviMem.freeze();
        raviMem.checkIn();
    }
}
