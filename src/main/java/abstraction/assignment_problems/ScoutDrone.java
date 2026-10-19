package main.java.abstraction.assignment_problems;

/**
 * Week 7 - Problem 5: ScoutDrone extending Drone only (not Trackable).
 */
public class ScoutDrone extends Drone {

    public ScoutDrone(String droneId) {
        super(droneId);
    }

    @Override
    public String fly() {
        return getDroneId() + " scouting perimeter";
    }
}
