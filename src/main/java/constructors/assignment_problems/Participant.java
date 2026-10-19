package main.java.constructors.assignment_problems;

/**
 * Week 4 - Problem 1: Participant Constructors
 * Demonstrates constructor overloading and chaining via this(name, "Unassigned").
 */
public class Participant {
    private String name;
    private String teamName;
    private boolean registered;

    /**
     * Two-argument constructor: initializes all fields and marks registered as true.
     */
    public Participant(String name, String teamName) {
        this.name = name;
        this.teamName = teamName;
        this.registered = true;
    }

    /**
     * Single-argument constructor: chains to the 2-argument constructor with "Unassigned".
     */
    public Participant(String name) {
        this(name, "Unassigned");
    }

    public void printDetails() {
        System.out.println(name + " | " + teamName + " | Registered: " + registered);
    }

    public static void main(String[] args) {
        String[] names = {"Ravi", "Meera", "Karthik", "Divya"};
        String[] teams = {"ByteBusters", "", "CodeCrafters", ""};

        Participant[] participants = new Participant[names.length];
        for (int i = 0; i < names.length; i++) {
            if (teams[i] == null || teams[i].isEmpty()) {
                participants[i] = new Participant(names[i]);
            } else {
                participants[i] = new Participant(names[i], teams[i]);
            }
        }

        for (Participant p : participants) {
            p.printDetails();
        }
    }
}
