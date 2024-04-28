import java.io.*;
import java.util.*;

public class StudentManagementSystem {
    private static final String DATA_FILE = "students.txt";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        loadData(students);

        boolean exit = false;
        while (!exit) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addStudent(students);
                    break;
                case 2:
                    editStudent(students);
                    break;
                case 3:
                    searchStudent(students);
                    break;
                case 4:
                    displayAllStudents(students);
                    break;
                case 5:
                    saveData(students);
                    System.out.println("Exiting the application. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addStudent(List<Student> students) {
        System.out.println("\nAdding a new student:");
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        students.add(new Student(id, name, age));
        System.out.println("Student added successfully.");
    }

    private static void editStudent(List<Student> students) {
        System.out.println("\nEditing student information:");
        System.out.print("Enter student ID to edit: ");
        String id = scanner.nextLine();
        boolean found = false;

        for (Student student : students) {
            if (student.getId().equals(id)) {
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                System.out.print("Enter new age: ");
                int newAge = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                student.setName(newName);
                student.setAge(newAge);
                System.out.println("Student information updated.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    private static void searchStudent(List<Student> students) {
        System.out.print("\nEnter student ID to search: ");
        String id = scanner.nextLine();
        boolean found = false;

        for (Student student : students) {
            if (student.getId().equals(id)) {
                System.out.println("Student found:");
                System.out.println("ID: " + student.getId());
                System.out.println("Name: " + student.getName());
                System.out.println("Age: " + student.getAge());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    private static void displayAllStudents(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("\nNo students found.");
        } else {
            System.out.println("\nAll students:");
            for (Student student : students) {
                System.out.println("ID: " + student.getId() + ", Name: " + student.getName() + ", Age: " + student.getAge());
            }
        }
    }

    private static void loadData(List<Student> students) {
        try (Scanner fileScanner = new Scanner(new File(DATA_FILE))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                students.add(new Student(parts[0], parts[1], Integer.parseInt(parts[2])));
            }
        } catch (FileNotFoundException e) {
            // File not found, ignore (can create a new file when saving data)
        }
    }

    private static void saveData(List<Student> students) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            for (Student student : students) {
                writer.println(student.getId() + "," + student.getName() + "," + student.getAge());
            }
        } catch (IOException e) {
            System.out.println("Error occurred while saving data: " + e.getMessage());
        }
    }
}

class Student {
    private String id;
    private String name;
    private int age;

    public Student(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
