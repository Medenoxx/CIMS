package com.example.jasarevic_javatest.controller;

import com.example.jasarevic_javatest.dto.TeacherRequestDTO;
import com.example.jasarevic_javatest.dto.TeacherResponseDTO;
import com.example.jasarevic_javatest.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/teacher")
@RestController
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @PostMapping
    public TeacherResponseDTO addTeacher(@RequestBody TeacherRequestDTO teacherRequestDTO) {
        return teacherService.addTeacher(teacherRequestDTO);
    }


    @GetMapping
    public List<TeacherResponseDTO> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{teacherId}")
    public TeacherResponseDTO getTeacherById (@PathVariable Integer teacherId) {
        return teacherService.getOneTeacherById(teacherId);
    }

    @PutMapping("/{teacherId}")
    public TeacherResponseDTO updateTeacherData(@PathVariable Integer teacherId, @RequestBody TeacherRequestDTO teacherRequestDTO) {
        return teacherService.updateTeacherData(teacherId, teacherRequestDTO);
    }
}