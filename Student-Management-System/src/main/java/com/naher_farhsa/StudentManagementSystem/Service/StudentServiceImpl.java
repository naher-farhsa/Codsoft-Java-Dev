package com.naher_farhsa.StudentManagementSystem.Service;

import com.naher_farhsa.StudentManagementSystem.Enitity.Student;
import com.naher_farhsa.StudentManagementSystem.Exception.StudentNotFoundException;
import com.naher_farhsa.StudentManagementSystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private int PASS;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student saveStudent(Student student, int PASS){
        if(this.PASS==PASS) {
            System.out.println("Student details saved successfully!");
            return studentRepository.save(student);
        }
        else{
            System.out.println("Incorrect PASS: "+PASS);
        }
        return null;
    }


    @Override
    public Student fetchStudentById(Long rollno, int PASS) throws StudentNotFoundException {
        if(this.PASS==PASS) {
            Optional<Student> student = studentRepository.findById(rollno);
            if (student.isEmpty()) {
                  throw new StudentNotFoundException("Student details not found");
            }
            return student.get();
        }
        else {
            System.out.println("Incorrect PASS: "+PASS);
        }
        return null;
    }

    @Override
    public List<Student> fetchAllStudent(int PASS) throws StudentNotFoundException {
        if(this.PASS==PASS){
            if(studentRepository.findAll().isEmpty()){
                throw new StudentNotFoundException("Student details not found");
            }
            else{
                return studentRepository.findAll();
            }
        }
        else{
            System.out.println("Incorrect PASS: "+PASS);
        }
        return null;
    }

    @Override
    public void removeStudentById(Long rollno,int PASS) throws StudentNotFoundException {
        if(this.PASS==PASS){
            if(studentRepository.findById(rollno).isEmpty()){
                throw new StudentNotFoundException("Student details not found");
            }
            else{
                studentRepository.deleteById(rollno);
                System.out.println("Student details deleted successfully!");
            }

        }
        else {
            System.out.println("Incorrect PASS: " + PASS);
        }
    }

    @Override
    public Student updateStudent(Long rollno,int PASS, Student student)  {
        if(this.PASS==PASS) {
            Student std = studentRepository.findById(rollno).orElse(null);
            if (std != null) {
                if (Objects.nonNull(student.getName()) && !"".equalsIgnoreCase(student.getName())) {
                    std.setName(student.getName());
                }
                if (Objects.nonNull(student.getDepartment()) && !"".equalsIgnoreCase(student.getDepartment())) {
                    std.setDepartment(student.getDepartment());
                }
                std.setCgpa(student.getCgpa());
                std.setSem(student.getSem());
                System.out.println("Student details updated successfully!");
                return studentRepository.save(std);
            }
            else {
                System.out.println("Student with ID " + rollno + " not found");
            }
        }
        else {
            System.out.println("Incorrect PASS: "+PASS);
        }
        return null;
    }

    @Override
    public ResponseEntity<String> setPASS(int PASS) {
        this.PASS = PASS;
        return ResponseEntity.ok("Your PASS : " + PASS);
    }
}
