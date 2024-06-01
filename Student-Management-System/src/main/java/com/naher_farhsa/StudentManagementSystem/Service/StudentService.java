package com.naher_farhsa.StudentManagementSystem.Service;

import com.naher_farhsa.StudentManagementSystem.Enitity.Student;
import com.naher_farhsa.StudentManagementSystem.Exception.StudentNotFoundException;
import org.springframework.http.ResponseEntity;
import java.util.List;


public interface StudentService {

   Student saveStudent(Student student,int PASS);

   Student  fetchStudentById(Long rollno,int PASS) throws StudentNotFoundException;

   List<Student> fetchAllStudent(int PASS) throws StudentNotFoundException;

   void removeStudentById(Long rollno,int PASS) throws StudentNotFoundException;

   Student updateStudent(Long rollno, int PASS, Student student);

  ResponseEntity<String> setPASS(int pass);
}
