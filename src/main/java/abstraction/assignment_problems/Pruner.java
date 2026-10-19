package main.java.abstraction.assignment_problems;

/**
 * Week 7 - Problem 3: Pruner extending CuttingTool.
 * Demonstrates multilevel super.use() chaining.
 */
public class Pruner extends CuttingTool {
    @Override
    public String use() {
        return super.use() + ", then trimming branches precisely";
    }
}
