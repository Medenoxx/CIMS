package com.example.jasarevic_javatest.dto;

import com.example.jasarevic_javatest.enums.AllowedSubjects;
import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class LessonRequestDTO {
    private String startDate;
    private Float duration;
    private AllowedSubjects subject;
    private Integer teacherId;
}
