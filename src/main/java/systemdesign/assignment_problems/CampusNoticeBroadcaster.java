package main.java.systemdesign.assignment_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Week 8 - Problem 5: Campus Notice Broadcaster
 * Polymorphic notification dispatching targeted by department.
 */
public class CampusNoticeBroadcaster {

    public interface NotificationChannel {
        String getChannelName();
        void send(Student student, Notice notice);
    }

    public static class EmailChannel implements NotificationChannel {
        @Override public String getChannelName() { return "Email"; }
        @Override
        public void send(Student student, Notice notice) {
            System.out.println("[EMAIL] To " + student.getName() + " (" + student.getDepartment() + "): " + notice.getTitle());
        }
    }

    public static class SmsChannel implements NotificationChannel {
        @Override public String getChannelName() { return "SMS"; }
        @Override
        public void send(Student student, Notice notice) {
            System.out.println("[SMS] To " + student.getName() + " (" + student.getDepartment() + "): " + notice.getTitle());
        }
    }

    public static class AppChannel implements NotificationChannel {
        @Override public String getChannelName() { return "App"; }
        @Override
        public void send(Student student, Notice notice) {
            System.out.println("[APP PUSH] To " + student.getName() + " (" + student.getDepartment() + "): " + notice.getTitle());
        }
    }

    // Extensible WhatsApp channel added without modifying broadcaster logic
    public static class WhatsAppChannel implements NotificationChannel {
        @Override public String getChannelName() { return "WhatsApp"; }
        @Override
        public void send(Student student, Notice notice) {
            System.out.println("[WHATSAPP] To " + student.getName() + " (" + student.getDepartment() + "): " + notice.getTitle());
        }
    }

    public static class Student {
        private final String id;
        private final String name;
        private final String department;
        private final List<NotificationChannel> preferredChannels;

        public Student(String id, String name, String department, List<NotificationChannel> preferredChannels) {
            if (preferredChannels == null || preferredChannels.isEmpty()) {
                throw new IllegalArgumentException("Student must have at least one preferred channel.");
            }
            this.id = id;
            this.name = name;
            this.department = department;
            this.preferredChannels = new ArrayList<>(preferredChannels);
        }

        public String getId() { return id; }
        public String getName() { return name; }
        public String getDepartment() { return department; }
        public List<NotificationChannel> getPreferredChannels() { return preferredChannels; }
    }

    public static class Notice {
        private final String title;
        private final String content;
        private final Set<String> targetDepartments;

        public Notice(String title, String content, Set<String> targetDepartments) {
            if (title == null || title.trim().isEmpty()) {
                throw new IllegalArgumentException("Notice title cannot be empty.");
            }
            if (targetDepartments == null || targetDepartments.isEmpty()) {
                throw new IllegalArgumentException("Notice must target at least one department.");
            }
            this.title = title.trim();
            this.content = content;
            this.targetDepartments = new HashSet<>(targetDepartments);
        }

        public String getTitle() { return title; }
        public String getContent() { return content; }
        public Set<String> getTargetDepartments() { return targetDepartments; }
    }

    public static class NoticeBoard {
        private final List<Student> students = new ArrayList<>();

        public void registerStudent(Student student) {
            students.add(student);
        }

        public void postNotice(Notice notice) {
            System.out.println("\n--- Broadcasting Notice: '" + notice.getTitle() + "' to departments " + notice.getTargetDepartments() + " ---");
            for (Student student : students) {
                if (notice.getTargetDepartments().contains(student.getDepartment())) {
                    for (NotificationChannel channel : student.getPreferredChannels()) {
                        channel.send(student, notice);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Student asha = new Student(
                "S101",
                "Asha",
                "CSE",
                Arrays.asList(new EmailChannel(), new AppChannel())
        );

        Student ravi = new Student(
                "S102",
                "Ravi",
                "ECE",
                Arrays.asList(new SmsChannel())
        );

        Student sneha = new Student(
                "S103",
                "Sneha",
                "CSE",
                Arrays.asList(new WhatsAppChannel())
        );

        NoticeBoard noticeBoard = new NoticeBoard();
        noticeBoard.registerStudent(asha);
        noticeBoard.registerStudent(ravi);
        noticeBoard.registerStudent(sneha);

        // Broadcast CSE only notice
        Notice cseNotice = new Notice(
                "Hackathon Registration Open",
                "Register your teams by Friday.",
                new HashSet<>(Arrays.asList("CSE"))
        );
        noticeBoard.postNotice(cseNotice);

        // Broadcast All-campus notice
        Notice allNotice = new Notice(
                "Campus Maintenance Closure",
                "Library closed Sunday.",
                new HashSet<>(Arrays.asList("CSE", "ECE"))
        );
        noticeBoard.postNotice(allNotice);
    }
}
