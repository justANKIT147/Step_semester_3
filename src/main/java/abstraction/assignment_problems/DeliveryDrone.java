package main.java.abstraction.assignment_problems;

/**
 * Week 7 - Problem 5: DeliveryDrone extending Drone and implementing Trackable.
 */
public class DeliveryDrone extends Drone implements Trackable {
    private final String location;

    public DeliveryDrone(String droneId, String location) {
        super(droneId);
        this.location = location;
    }

    @Override
    public String fly() {
        return getDroneId() + " delivering payload";
    }

    @Override
    public String getLocation() {
        return getDroneId() + " at " + location;
    }
}
