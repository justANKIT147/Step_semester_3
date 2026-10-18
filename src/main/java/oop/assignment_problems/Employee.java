package main.java.oop.assignment_problems;

/**
 * Week 3 - Problem 3: Overloaded Employee Constructors
 * Demonstrates constructor overloading and constructor chaining using this(...).
 */
public class Employee {
    private String empId;
    private String empName;
    private double salary;
    private boolean isIntern;

    /**
     * Three-argument constructor for permanent employees.
     */
    public Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
        this.isIntern = false;
    }

    /**
     * Two-argument constructor for interns, chaining to the 3-arg constructor.
     */
    public Employee(String empId, String empName) {
        this(empId, empName, 0.0);
        this.isIntern = true;
    }

    public void printProfile() {
        System.out.println(empId + " | " + empName + " | Rs " + salary + " | Intern: " + isIntern);
    }

    public static void main(String[] args) {
        Employee permanent = new Employee("E-101", "Divya", 65000.0);
        Employee intern = new Employee("E-102", "Arjun");

        permanent.printProfile();
        intern.printProfile();
    }
}
