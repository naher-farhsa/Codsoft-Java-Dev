package com.naher_farhsa.StudentManagementSystem.Repository;

import com.naher_farhsa.StudentManagementSystem.Enitity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
