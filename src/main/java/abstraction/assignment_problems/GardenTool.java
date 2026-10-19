package main.java.abstraction.assignment_problems;

/**
 * Week 7 - Problem 3: Abstract GardenTool
 * Base abstract class providing default behavior for use() that subclasses chain through super.use().
 */
public abstract class GardenTool {
    public String use() {
        return "Using the tool in the garden";
    }
}
