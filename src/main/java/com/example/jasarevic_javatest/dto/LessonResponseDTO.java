package com.example.jasarevic_javatest.dto;

import com.example.jasarevic_javatest.enums.AllowedSubjects;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class LessonResponseDTO {
    private Integer lessonId;
    private String startDate;
    private Float duration;
    private AllowedSubjects subject;
    private Integer teacherId;
    private List<StudentResponseDTO> studentsInLesson = new ArrayList<>();
}
