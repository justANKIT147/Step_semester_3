package main.java.abstraction.assignment_problems;

/**
 * Week 7 - Problem 5: Abstract Drone
 */
public abstract class Drone {
    private final String droneId;

    public Drone(String droneId) {
        this.droneId = droneId;
    }

    public String getDroneId() {
        return droneId;
    }

    public abstract String fly();
}
