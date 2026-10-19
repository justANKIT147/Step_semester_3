package main.java.abstraction.assignment_problems;

/**
 * Week 7 - Problem 4: Tablet extending ClassroomDevice and implementing Chargeable.
 */
public class Tablet extends ClassroomDevice implements Chargeable {

    public Tablet(String assetTag) {
        super(assetTag);
    }

    @Override
    public String operate() {
        return "Tablet " + getAssetTag() + " displaying lesson";
    }

    @Override
    public String charge() {
        return getAssetTag() + " charging";
    }

    @Override
    public String charge(int minutes) {
        return getAssetTag() + " charging for " + minutes + " minutes";
    }
}
