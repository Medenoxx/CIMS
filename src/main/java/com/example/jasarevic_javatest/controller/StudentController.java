package com.example.jasarevic_javatest.controller;

import com.example.jasarevic_javatest.dto.StudentRequestDTO;
import com.example.jasarevic_javatest.dto.StudentResponseDTO;
import com.example.jasarevic_javatest.services.StudentService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/student")
@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public StudentResponseDTO addStudent(@RequestBody StudentRequestDTO studentRequestDTO) {
        return studentService.addStudent(studentRequestDTO);
    }

    @PutMapping("/{studentId}")
    public StudentResponseDTO updateStudentData(@PathVariable Integer studentId, @RequestBody StudentRequestDTO studentRequestDTO) {
        return studentService.updateStudentData(studentId, studentRequestDTO);
    }
}
