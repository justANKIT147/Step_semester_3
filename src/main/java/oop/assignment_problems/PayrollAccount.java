package main.java.oop.assignment_problems;

/**
 * Week 3 - Problem 2: Encapsulated Payroll Account
 * Encapsulates salary state, validating credit bonus, tax deductions, and net salary.
 */
public class PayrollAccount {
    private double basicSalary;
    private double bonus;

    public PayrollAccount(double basicSalary) {
        if (basicSalary < 0) {
            System.out.println("Warning: opening salary cannot be negative; set to 0.0");
            this.basicSalary = 0.0;
        } else {
            this.basicSalary = basicSalary;
        }
        this.bonus = 0.0;
    }

    public void creditBonus(double amount) {
        if (amount <= 0) {
            System.out.println("Error: bonus amount must be greater than zero.");
        } else {
            this.bonus += amount;
        }
    }

    public void deductTax(double percent) {
        if (percent < 0 || percent > 100) {
            System.out.println("Error: tax percentage must be between 0 and 100.");
        } else {
            this.basicSalary -= this.basicSalary * (percent / 100.0);
        }
    }

    public double getNetSalary() {
        return basicSalary + bonus;
    }

    public static void main(String[] args) {
        PayrollAccount account = new PayrollAccount(50000);
        account.creditBonus(5000);
        account.deductTax(10);
        System.out.println("Expected net salary: Rs " + account.getNetSalary());

        // Edge case checks
        PayrollAccount negativeAccount = new PayrollAccount(-1000);
        negativeAccount.creditBonus(-50);
        negativeAccount.deductTax(150);
    }
}
