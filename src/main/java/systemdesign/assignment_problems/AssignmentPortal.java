package main.java.systemdesign.assignment_problems;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Week 8 - Problem 2: Assignment Submission Portal
 * Implements strategy pattern for extensible late penalties and enforces lifecycle states.
 */
public class AssignmentPortal {

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

    public interface PenaltyStrategy {
        double calculatePenaltyPercentage(long lateDays);
    }

    public static class CodingPenaltyStrategy implements PenaltyStrategy {
        @Override
        public double calculatePenaltyPercentage(long lateDays) {
            if (lateDays <= 0) return 0.0;
            return Math.min(1.0, lateDays * 0.10); // 10% per day capped at 100%
        }
    }

    public static class WrittenPenaltyStrategy implements PenaltyStrategy {
        @Override
        public double calculatePenaltyPercentage(long lateDays) {
            if (lateDays <= 0) return 0.0;
            return Math.min(1.0, lateDays * 0.20); // 20% per day capped at 100%
        }
    }

    public static class Assignment {
        private final String title;
        private final LocalDate dueDate;
        private final int maxMarks;
        private final PenaltyStrategy penaltyStrategy;

        public Assignment(String title, LocalDate dueDate, int maxMarks, PenaltyStrategy penaltyStrategy) {
            this.title = title;
            this.dueDate = dueDate;
            this.maxMarks = maxMarks;
            this.penaltyStrategy = penaltyStrategy;
        }

        public String getTitle() { return title; }
        public LocalDate getDueDate() { return dueDate; }
        public int getMaxMarks() { return maxMarks; }
        public PenaltyStrategy getPenaltyStrategy() { return penaltyStrategy; }
    }

    public enum SubmissionStatus {
        SUBMITTED,
        GRADED
    }

    public static class Submission {
        private final Student student;
        private final Assignment assignment;
        private LocalDate submissionDate;
        private SubmissionStatus status;
        private double finalMarks;

        public Submission(Student student, Assignment assignment, LocalDate submissionDate) {
            this.student = student;
            this.assignment = assignment;
            this.submissionDate = submissionDate;
            this.status = SubmissionStatus.SUBMITTED;
        }

        public boolean resubmit(LocalDate newDate) {
            if (status == SubmissionStatus.GRADED) {
                System.out.println("Resubmission rejected: " + student.getName() + "'s submission for '"
                        + assignment.getTitle() + "' has already been graded.");
                return false;
            }
            this.submissionDate = newDate;
            System.out.println("Resubmission accepted for " + student.getName() + " on " + newDate);
            return true;
        }

        public void grade(double awardedMarks) {
            if (status != SubmissionStatus.SUBMITTED) {
                System.out.println("Grading failed: submission is not in SUBMITTED state.");
                return;
            }

            long lateDays = ChronoUnit.DAYS.between(assignment.getDueDate(), submissionDate);
            if (lateDays < 0) lateDays = 0;

            double penaltyRate = assignment.getPenaltyStrategy().calculatePenaltyPercentage(lateDays);
            double penalty = awardedMarks * penaltyRate;
            this.finalMarks = Math.max(0.0, awardedMarks - penalty);
            this.status = SubmissionStatus.GRADED;

            long penaltyPct = Math.round(penaltyRate * 100);
            if (penaltyPct > 0) {
                System.out.println(student.getName() + ": " + (long) finalMarks + "/" + assignment.getMaxMarks()
                        + " after " + penaltyPct + "% penalty");
            } else {
                System.out.println(student.getName() + ": " + (long) finalMarks + "/" + assignment.getMaxMarks());
            }
        }

        public double getFinalMarks() { return finalMarks; }
        public SubmissionStatus getStatus() { return status; }
    }

    public static void main(String[] args) {
        Student asha = new Student("S1", "Asha");
        Student ravi = new Student("S2", "Ravi");

        Assignment linkedListLab = new Assignment(
                "Linked List Lab",
                LocalDate.of(2026, 3, 10),
                50,
                new CodingPenaltyStrategy()
        );

        // Asha submits on due date (March 10)
        Submission ashaSub = new Submission(asha, linkedListLab, LocalDate.of(2026, 3, 10));
        ashaSub.grade(45);

        // Ravi submits 4 days late (March 14)
        Submission raviSub = new Submission(ravi, linkedListLab, LocalDate.of(2026, 3, 14));
        raviSub.grade(40);

        // Reject Asha's resubmission after grading
        ashaSub.resubmit(LocalDate.of(2026, 3, 15));
    }
}
