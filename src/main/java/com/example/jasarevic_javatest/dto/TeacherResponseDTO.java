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

public class TeacherResponseDTO {
    private Integer Id;
    private String name;
    private Integer age;
    private AllowedSubjects subject;
    private List<LessonResponseDTO> lessonsOfTeacherList = new ArrayList<>();
}
