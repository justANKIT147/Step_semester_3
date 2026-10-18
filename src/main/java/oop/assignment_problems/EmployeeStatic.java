package main.java.oop.assignment_problems;

/**
 * Week 3 - Problem 5: Instance versus Static Members
 * Demonstrates static state tracking, class-level methods, and distinction from instance fields.
 */
public class EmployeeStatic {
    private String empName;
    private double salary;
    private static String companyName = "Bright Horizon Technologies";
    private static int employeeCount = 0;

    public EmployeeStatic(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }

    public static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }

    public static void main(String[] args) {
        EmployeeStatic e1 = new EmployeeStatic("Employee1", 40000);
        EmployeeStatic e2 = new EmployeeStatic("Employee2", 42000);
        EmployeeStatic e3 = new EmployeeStatic("Employee3", 45000);

        EmployeeStatic.printCompanyInfo();
    }
}
