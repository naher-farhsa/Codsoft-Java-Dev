package com.naher_farhsa.StudentManagementSystem.Enitity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@NoArgsConstructor
@Data
@Builder
@Table(name="Student_Details")
public class Student {
    @Id
    private long rollno;
    private String name;
    private String department;
    private int sem;
    private double cgpa;

    public Student(long rollno, String name, String department, int sem, double cgpa) {
        this.rollno = rollno;
        this.name = name;
        this.department = department;
        this.sem = sem;
        this.cgpa = cgpa;
    }
    public Student(String name, String department, double cgpa) {
        this.name=name;
        this.department=department;
        this.cgpa=cgpa;

    }
}



