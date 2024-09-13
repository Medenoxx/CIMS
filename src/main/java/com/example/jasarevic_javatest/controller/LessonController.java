package com.example.jasarevic_javatest.controller;

import com.example.jasarevic_javatest.dto.LessonRequestDTO;
import com.example.jasarevic_javatest.dto.LessonResponseDTO;
import com.example.jasarevic_javatest.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/lesson")
@RestController
public class LessonController {
    @Autowired
    private LessonService lessonService;

    @PostMapping
    public LessonResponseDTO addLesson(@RequestBody LessonRequestDTO lessonRequestDTO) {
        return lessonService.addLesson(lessonRequestDTO);
    }

    @GetMapping
    public List<LessonResponseDTO> getAllLessons() {
        return lessonService.getAllLessons();
    }

    @GetMapping("/{lessonId}")
    public LessonResponseDTO getLessonById(@PathVariable Integer lessonId) {
        return lessonService.getLessonById(lessonId);
    }

    @DeleteMapping("/{lessonId}")
    public String deleteLessonById(@PathVariable Integer lessonId) {
        return lessonService.deleteLessonById(lessonId);
    }
}