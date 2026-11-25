
import java.io.*;
import java.util.Scanner;

public class Main {

    // Employee class
    static class Employee {
        String employeeId;
        String name;
        double salary;

        Employee(String employeeId, String name, double salary) {
            this.employeeId = employeeId;
            this.name = name;
            this.salary = salary;
        }

        String toFileLine() {
            return employeeId + "," + name.replace(",", " ") + "," + salary;
        }

        public String toString() {
            return employeeId + " | " + name + " | "
                    + String.format("%.2f", salary);
        }
    }

    static Employee[] employees = new Employee[1000];
    static int count = 0;
    static int nextId = 101;
    static final String FILE_NAME = "salary.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        loadFromFile();

        while (true) {
            System.out.println("\n===== Employee Salary Analyzer =====");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All");
            System.out.println("3. Sort by Salary");
            System.out.println("4. Search by Name");
            System.out.println("5. Salary Statistics");
            System.out.println("6. Exit and Save");
            System.out.print("Enter choice: ");

            String input = sc.nextLine().trim();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addEmployee(sc);
                    break;

                case 2:
                    displayAll();
                    break;

                case 3:
                    sortBySalary();
                    break;

                case 4:
                    searchByName(sc);
                    break;

                case 5:
                    showStatistics();
                    break;

                case 6:
                    saveToFile();
                    System.out.println("Program closed.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Enter 1 to 6.");
            }
        }
    }

    // Add Employee
    static void addEmployee(Scanner sc) {
        if (count >= employees.length) {
            System.out.println("Employee array is full.");
            return;
        }

        System.out.print("Enter employee name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be blank.");
            return;
        }

        System.out.print("Enter salary: ");
        double salary;

        try {
            salary = Double.parseDouble(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid salary. Enter a number.");
            return;
        }

        if (salary <= 0 || Double.isInfinite(salary)
                || Double.isNaN(salary)) {
            System.out.println("Salary must be a positive number.");
            return;
        }

        String id = "E" + nextId++;

        employees[count] = new Employee(id, name, salary);
        System.out.println("Employee added: " + employees[count]);

        count++;
    }

    // Display all employees
    static void displayAll() {
        if (count == 0) {
            System.out.println("No employee records found.");
            return;
        }

        System.out.println("\n--- Employee Records ---");

        for (int i = 0; i < count; i++) {
            System.out.println(employees[i]);
        }
    }

    // Sort salary from highest to lowest
    static void sortBySalary() {
        if (count == 0) {
            System.out.println("No records to sort.");
            return;
        }

        // Bubble Sort
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - 1 - i; j++) {

                if (employees[j].salary < employees[j + 1].salary) {
                    Employee temp = employees[j];
                    employees[j] = employees[j + 1];
                    employees[j + 1] = temp;
                }
            }
        }

        System.out.println("Sorted by salary (highest to lowest):");
        displayAll();
    }

    // Search employee by name
    static void searchByName(Scanner sc) {
        if (count == 0) {
            System.out.println("No employee records found.");
            return;
        }

        System.out.print("Enter name to search: ");
        String searchName = sc.nextLine().trim().toLowerCase();

        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (employees[i].name.toLowerCase().contains(searchName)) {
                System.out.println("Found: " + employees[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Employee not found.");
        }
    }

    // Salary statistics
    static void showStatistics() {
        if (count == 0) {
            System.out.println("No employee records available.");
            return;
        }

        Employee highest = employees[0];
        Employee lowest = employees[0];

        double total = 0;

        for (int i = 0; i < count; i++) {
            Employee current = employees[i];

            if (current.salary > highest.salary) {
                highest = current;
            }

            if (current.salary < lowest.salary) {
                lowest = current;
            }

            total += current.salary;
        }

        double average = total / count;

        System.out.printf("Highest: %s (%.2f)%n",
                highest.name, highest.salary);

        System.out.printf("Lowest: %s (%.2f)%n",
                lowest.name, lowest.salary);

        System.out.printf("Average: %.2f%n", average);
    }

    // Load employee records from file
    static void loadFromFile() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader br =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = br.readLine()) != null
                    && count < employees.length) {

                String[] parts = line.split(",", 3);

                if (parts.length != 3) {
                    continue;
                }

                try {
                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    double salary = Double.parseDouble(parts[2].trim());

                    if (!id.isEmpty() && !name.isEmpty() && salary > 0) {
                        employees[count++] =
                                new Employee(id, name, salary);

                        if (id.startsWith("E")) {
                            try {
                                int number =
                                        Integer.parseInt(id.substring(1));

                                nextId = Math.max(nextId, number + 1);
                            } catch (NumberFormatException ignored) {
                                // Ignore invalid employee ID.
                            }
                        }
                    }
                } catch (NumberFormatException ignored) {
                    // Skip invalid records.
                }
            }

            System.out.println(count + " employee record(s) loaded.");

        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    // Save employee records to file
    static void saveToFile() {
        try (PrintWriter writer =
                     new PrintWriter(new FileWriter(FILE_NAME))) {

            for (int i = 0; i < count; i++) {
                writer.println(employees[i].toFileLine());
            }

            System.out.println("Records saved to " + FILE_NAME);

        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
