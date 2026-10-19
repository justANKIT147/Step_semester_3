package main.java.systemdesign.assignment_problems;

/**
 * Week 8 - Problem 1: Hostel Laundry Queue System
 * Extensible wash cycle design with machine availability and state tracking.
 */
public class LaundrySystem {

    public static class Student {
        private final String id;
        private final String name;

        public Student(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() { return id; }
        public String getName() { return name; }
    }

    public interface WashCycle {
        String getName();
        int getDurationMinutes();
        int getCost();
    }

    public static class QuickWash implements WashCycle {
        @Override public String getName() { return "Quick"; }
        @Override public int getDurationMinutes() { return 30; }
        @Override public int getCost() { return 20; }
    }

    public static class NormalWash implements WashCycle {
        @Override public String getName() { return "Normal"; }
        @Override public int getDurationMinutes() { return 45; }
        @Override public int getCost() { return 30; }
    }

    public static class HeavyWash implements WashCycle {
        @Override public String getName() { return "Heavy"; }
        @Override public int getDurationMinutes() { return 60; }
        @Override public int getCost() { return 45; }
    }

    // Extensible wash type added without modifying core booking logic
    public static class DelicateWash implements WashCycle {
        @Override public String getName() { return "Delicate"; }
        @Override public int getDurationMinutes() { return 40; }
        @Override public int getCost() { return 35; }
    }

    public static class WashingMachine {
        private final String machineId;
        private boolean busy;
        private Student currentStudent;
        private WashCycle currentCycle;

        public WashingMachine(String machineId) {
            this.machineId = machineId;
            this.busy = false;
        }

        public String getMachineId() { return machineId; }
        public boolean isBusy() { return busy; }

        public boolean book(Student student, WashCycle cycle) {
            if (busy) {
                System.out.println("Booking failed: " + machineId + " is currently busy.");
                return false;
            }
            this.busy = true;
            this.currentStudent = student;
            this.currentCycle = cycle;
            System.out.println(student.getName() + " successfully booked " + cycle.getName() + " on " + machineId + " (Rs " + cycle.getCost() + ").");
            return true;
        }

        public void completeWash() {
            if (busy) {
                System.out.println("Wash completed on " + machineId + " for " + currentStudent.getName() + ". Machine is now free.");
                this.busy = false;
                this.currentStudent = null;
                this.currentCycle = null;
            }
        }
    }

    public static void main(String[] args) {
        Student asha = new Student("S1", "Asha");
        Student ravi = new Student("S2", "Ravi");
        Student neha = new Student("S3", "Neha");

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        // 1. Asha books Quick on M1
        m1.book(asha, new QuickWash());

        // 2. Ravi's Heavy booking on busy M1 fails
        m1.book(ravi, new HeavyWash());

        // 3. Ravi books Heavy on M2
        m2.book(ravi, new HeavyWash());

        // 4. M1 completes and becomes available
        m1.completeWash();

        // 5. Neha books Normal on M1
        m1.book(neha, new NormalWash());
    }
}
