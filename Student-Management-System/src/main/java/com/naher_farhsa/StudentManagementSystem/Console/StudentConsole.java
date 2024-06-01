package com.naher_farhsa.StudentManagementSystem.Console;

import com.naher_farhsa.StudentManagementSystem.Enitity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Scanner;

@Component
public class StudentConsole implements CommandLineRunner {
    private boolean isRunning;
    private final Scanner scanner = new Scanner(System.in);
    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final ConfigurableApplicationContext applicationContext;
    private int PASS;

    @Autowired
    public StudentConsole(RestTemplate restTemplate, ConfigurableApplicationContext applicationContext) {
        this.restTemplate = restTemplate;
        this.baseUrl = "http://localhost:8080/naher_farhsa/student";
        this.applicationContext = applicationContext;
        this.isRunning = true;
    }

    @Override
    public void run(String... args) {
        System.out.println("⭐⭐ Welcome to Student Management System ⭐⭐");
        try {
            System.out.print("Please set student PASS (in digits): ");
            PASS = Integer.parseInt(scanner.nextLine());
            setPASS(PASS);
            while (isRunning) {
                displayOptions();

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> saveStudent();
                    case 2 -> fetchStudentById();
                    case 3 -> fetchAllStudents();
                    case 4 -> removeStudentById();
                    case 5 -> updateStudent();
                    case 6 -> exit();
                    default -> System.out.println("Invalid choice! Please enter a valid option.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid PASS entered! ");
            isRunning = false;
            applicationContext.close();
        }
    }

    private void displayOptions() {
        System.out.println("Please select an option:");
        System.out.println("1. Add Student");
        System.out.println("2. Check Student");
        System.out.println("3. Check All Students");
        System.out.println("4. Remove Student");
        System.out.println("5. Update Student");
        System.out.println("6. Exit");
    }

    private void setPASS(int PASS) {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                baseUrl + "/setPASS?PASS=" + PASS, null, String.class
        );
        System.out.println(responseEntity.getBody());
    }

    private void saveStudent() {
        promptPASS();
        System.out.println("Enter student details: ");
        System.out.print("Enter rollno: ");
        int rollno = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter department: ");
        String department = scanner.nextLine();
        System.out.print("Enter semester: ");
        int sem = scanner.nextInt();
        System.out.print("Enter CGPA: ");
        double cgpa = scanner.nextDouble();
        Student student = new Student(rollno, name, department, sem, cgpa);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                baseUrl + "/save?PASS=" + PASS, student, String.class
        );
        System.out.println(responseEntity.getBody());
    }


    private void fetchStudentById() {
        promptPASS();
        System.out.print("Enter rollno of student: ");
        int rollno = scanner.nextInt();
        ResponseEntity<Student> responseEntity = restTemplate.getForEntity(
                baseUrl + "/search?rollno=" + rollno + "&PASS=" + PASS, Student.class
        );
        System.out.println(responseEntity.getBody());
    }

    private void fetchAllStudents() {
        promptPASS();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                baseUrl + "/display?PASS=" + PASS, String.class
        );
        System.out.println(responseEntity.getBody());
    }

    private void removeStudentById() {
        promptPASS();
        System.out.print("Enter student rollno to remove: ");
        int rollno = scanner.nextInt();
        scanner.nextLine();

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                baseUrl + "/remove?rollno=" + rollno + "&PASS=" + PASS,
                HttpMethod.DELETE, null, String.class
        );
        System.out.println(responseEntity.getBody());
    }


    private void updateStudent() {
        promptPASS();
        System.out.print("Enter Student rollno to update: ");
        int rollno = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter updated details: ");
        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new department: ");
        String department = scanner.nextLine();
        System.out.print("Enter new cgpa: ");
        double cgpa = scanner.nextDouble();
        scanner.nextLine();
        Student student = new Student(name, department, cgpa);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                baseUrl + "/update?rollno=" + rollno + "&PASS=" + PASS,
                HttpMethod.PUT, new HttpEntity<>(student), String.class
        );
        System.out.println(responseEntity.getBody());
    }



    private void promptPASS() {
        System.out.print("Enter your PASS: ");
        PASS = scanner.nextInt();
    }

    private void exit() {
        System.out.println("Exiting the Student Management System\n⭐⭐ Goodbye ⭐⭐");
        isRunning = false;
        applicationContext.close();
    }
}
