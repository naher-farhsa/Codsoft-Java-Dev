package com.naher_farhsa.StudentManagementSystem.Controller;

import com.naher_farhsa.StudentManagementSystem.Enitity.Student;
import com.naher_farhsa.StudentManagementSystem.Exception.StudentNotFoundException;
import com.naher_farhsa.StudentManagementSystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/naher_farhsa/student")
public class StudentController {
    @Autowired
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/save")
    public Student saveStudent(@RequestBody Student student, int PASS){
        return studentService.saveStudent(student, PASS);
    }

    @GetMapping("/search")
    public Student fetchStudentById(@RequestParam Long rollno, int PASS) throws StudentNotFoundException {
        return studentService.fetchStudentById(rollno, PASS);
    }

    @GetMapping("/display")
    public List<Student> fetchAllStudent(int PASS) throws StudentNotFoundException{
        return studentService.fetchAllStudent(PASS);
    }

    @DeleteMapping("/remove")
    public void removeStudentById(@RequestParam Long rollno, int PASS) throws StudentNotFoundException{
        studentService.removeStudentById(rollno, PASS);
    }

    @PutMapping("/update")
    public Student updateStudent(@RequestParam Long rollno, int PASS, @RequestBody Student student){
        return studentService.updateStudent(rollno,PASS,student);
    }

    @PostMapping("/setPASS")
    public ResponseEntity<String> setPASS(@RequestParam int PASS){
        return studentService.setPASS(PASS);
    }
}
