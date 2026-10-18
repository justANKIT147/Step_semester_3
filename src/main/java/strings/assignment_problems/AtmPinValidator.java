package main.java.strings.assignment_problems;

/**
 * Week 2 - Problem 1: ATM PIN Length Validator
 * Checks whether an ATM PIN is exactly 4 digits long.
 */
public class AtmPinValidator {

    /**
     * Validates the length of the given PIN using one if/else statement.
     *
     * @param pin the PIN string to check
     */
    public static void checkPinLength(String pin) {
        if (pin == null || pin.length() != 4) {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }

    public static void main(String[] args) {
        // Demonstration with example inputs
        checkPinLength("482");
        checkPinLength("4820");

        // Edge cases
        checkPinLength("");
        checkPinLength("12345");
    }
}
