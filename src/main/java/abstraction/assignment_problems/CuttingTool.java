package main.java.abstraction.assignment_problems;

/**
 * Week 7 - Problem 3: Abstract CuttingTool extending GardenTool.
 * Chains to super.use() and appends blade sharpening behavior.
 */
public abstract class CuttingTool extends GardenTool {
    @Override
    public String use() {
        return super.use() + ", blade sharpened first";
    }
}
