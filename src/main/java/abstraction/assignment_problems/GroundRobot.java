package main.java.abstraction.assignment_problems;

/**
 * Week 7 - Problem 5: GroundRobot implementing Trackable (non-Drone vehicle).
 */
public class GroundRobot implements Trackable {
    private final String robotId;
    private final String location;

    public GroundRobot(String robotId, String location) {
        this.robotId = robotId;
        this.location = location;
    }

    public String getRobotId() {
        return robotId;
    }

    @Override
    public String getLocation() {
        return robotId + " at " + location;
    }
}
