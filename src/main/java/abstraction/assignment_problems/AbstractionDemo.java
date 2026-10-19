package main.java.abstraction.assignment_problems;

/**
 * Week 7 - Demonstration and Verification Test Harness for all 5 problems.
 */
public class AbstractionDemo {

    /**
     * Problem 1: Ring all devices implementing Ringable.
     */
    public static void ringAll(Ringable[] devices) {
        if (devices != null) {
            for (Ringable device : devices) {
                if (device != null) {
                    System.out.println(device.ring());
                }
            }
        }
    }

    /**
     * Problem 5: Check if object implements Trackable using instanceof and safely cast.
     */
    public static String getLocationIfTrackable(Object o) {
        if (o instanceof Trackable) {
            return ((Trackable) o).getLocation();
        }
        return "Tracking not available";
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 1: Ringable Devices ===");
        Ringable[] devices = {
            new AlarmClock("7:00 AM"),
            new Doorbell("Front Door")
        };
        ringAll(devices);

        System.out.println("\n=== Problem 2: Abstract Art Pieces ===");
        ArtPiece painting = new Painting("Sunset Fields", "framed on canvas");
        ArtPiece sculpture = new Sculpture("The Thinker II", "carved from stone");
        System.out.println(painting.describe());
        System.out.println(sculpture.describe());

        System.out.println("\n=== Problem 3: Garden Tool Super.use() Chaining ===");
        CuttingTool cuttingTool = new CuttingTool() {}; // anonymous subclass of abstract CuttingTool to verify its use() output
        System.out.println(cuttingTool.use());
        Pruner pruner = new Pruner();
        System.out.println(pruner.use());

        System.out.println("\n=== Problem 4: Classroom Device and Chargeable ===");
        Tablet tablet = new Tablet("TAB-5");
        System.out.println(tablet.operate());
        System.out.println(tablet.charge());
        System.out.println(tablet.charge(30));

        System.out.println("\n=== Problem 5: Drone and Trackable ===");
        Object d1 = new DeliveryDrone("DR-1", "Sector 4");
        Object d2 = new ScoutDrone("SD-9");
        Object r1 = new GroundRobot("GR-1", "Sector 4");

        System.out.println(getLocationIfTrackable(d1));
        System.out.println(getLocationIfTrackable(d2));
        System.out.println(getLocationIfTrackable(r1));
    }
}
