
# Employee Salary Analyzer

A console-based Java application to manage employee salary records using Data Structures and Algorithms.

## Project Information

- **Project Name:** Employee Salary Analyzer
- **Project No.:** 15
- **Subject:** Data Structures and Algorithms (DSA)
- **Language:** Java
- **Developer:** Shraddha Dewangan

## Features

- Add employee details
- Automatically generate employee ID
- Display all employee records
- Sort employees by salary (highest to lowest)
- Search employee by name
- Calculate highest salary
- Calculate lowest salary
- Calculate average salary
- Validate employee name and salary
- Save employee records in a text file
- Load employee records when the program starts

## Technologies Used

- Java
- Arrays
- Bubble Sort
- Linear Searching
- File Handling
- Scanner
- Methods and Classes

## Project Structure

```text
Employee-Salary-Analyzer/
│
├── Main.java
├── salary.txt
└── README.md
```

## System Requirements

- Java JDK 21 (recommended)
- Visual Studio Code or any Java IDE
- Git
- GitHub account

## How to Run the Project

### Step 1: Compile the Java file

```bash
javac Main.java
```

### Step 2: Run the program

```bash
java Main
```

## Menu Options

| Option | Operation |
|--------|-----------|
| 1 | Add Employee |
| 2 | Display All Employees |
| 3 | Sort by Salary |
| 4 | Search by Name |
| 5 | Salary Statistics |
| 6 | Exit and Save |

## Data File Format

Employee records are stored in `salary.txt`.

Format:

```text
EmployeeID,EmployeeName,Salary
```

Example:

```text
E101,Amit Sharma,45000
E102,Neha Verma,52000
```

## Data Structures and Algorithms

- **Array:** Stores employee records.
- **Bubble Sort:** Sorts employees by salary in descending order.
- **Linear Search:** Searches employees by name.
- **File Handling:** Loads and saves employee records.

## Time Complexity

| Operation | Time Complexity |
|-----------|-----------------|
| Add Employee | O(1) |
| Display Employees | O(n) |
| Sort by Salary | O(n²) |
| Search by Name | O(n) |
| Salary Statistics | O(n) |
| Load/Save File | O(n) |

Here, `n` represents the number of employees.

## Future Scope

- Update and delete employee records
- Add employee department and designation
- Generate salary slips
- Add tax and bonus calculations
- Database connectivity
- Graphical user interface

## Conclusion

The Employee Salary Analyzer demonstrates the practical use of arrays, sorting, searching, methods, and file handling in Java. It provides basic employee record management and salary analysis through a menu-driven console application.

## Author

**Shraddha Dewangan**

B.Tech CSE (AI & ML)

Rungta International Skills University
