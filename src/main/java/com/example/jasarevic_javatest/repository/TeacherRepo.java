package com.example.jasarevic_javatest.repository;

import com.example.jasarevic_javatest.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepo extends JpaRepository<Teacher, Integer> {
}
