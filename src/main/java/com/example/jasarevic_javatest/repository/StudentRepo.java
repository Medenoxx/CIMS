package com.example.jasarevic_javatest.repository;

import com.example.jasarevic_javatest.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Integer> {
}
