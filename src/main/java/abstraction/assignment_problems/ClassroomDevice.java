package main.java.abstraction.assignment_problems;

/**
 * Week 7 - Problem 4: Abstract ClassroomDevice
 */
public abstract class ClassroomDevice {
    private final String assetTag;

    public ClassroomDevice(String assetTag) {
        this.assetTag = assetTag;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public abstract String operate();
}
