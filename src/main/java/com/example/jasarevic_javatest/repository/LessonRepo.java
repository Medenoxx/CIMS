package com.example.jasarevic_javatest.repository;

import com.example.jasarevic_javatest.entity.Lesson;
import com.example.jasarevic_javatest.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepo extends JpaRepository<Lesson, Integer> {
    List<Lesson> findByTeacher(Teacher teacher);
}
