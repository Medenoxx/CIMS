package com.example.jasarevic_javatest.services;

import com.example.jasarevic_javatest.dto.StudentRequestDTO;
import com.example.jasarevic_javatest.dto.StudentResponseDTO;
import com.example.jasarevic_javatest.entity.Lesson;
import com.example.jasarevic_javatest.entity.Student;
import com.example.jasarevic_javatest.repository.LessonRepo;
import com.example.jasarevic_javatest.repository.StudentRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private LessonRepo lessonRepo;
    @Autowired
    private StudentRepo studentRepo;

    //Hilfsfunktion, musste ich auch in Lesson schreiben weil es hier nicht übernommen hat
    public StudentResponseDTO convertStudentToStudentDTO(Student student) {
        return new StudentResponseDTO(student.getId(), student.getName(),student.getAge(),student.getSocialSecurityNumber(),student.getLesson().getLessonId());
    }

    //Optional weil es "kein Wert vorhanden einschließt"
    public StudentResponseDTO addStudent(StudentRequestDTO studentRequestDTO) {
        Optional<Lesson> lessonOptional = lessonRepo.findById(studentRequestDTO.getLessonId());
        Lesson lesson = lessonOptional.get();
        Student student = new Student();
        student.setName(studentRequestDTO.getName());
        student.setAge(studentRequestDTO.getAge());
        student.setLesson(lesson);
        studentRepo.save(student);
        return convertStudentToStudentDTO(student);
    }

    public StudentResponseDTO updateStudentData(Integer studentId, StudentRequestDTO studentRequestDTO) {
        Optional<Student> studentOptional = studentRepo.findById(studentId);


        if (studentOptional.isEmpty()) {
            throw new EntityNotFoundException("Kein Student mit der angefragten ID gefunden.");
        }

        Student student = studentOptional.get();
        studentRepo.save(student);
        return convertStudentToStudentDTO(student);
    }
}
