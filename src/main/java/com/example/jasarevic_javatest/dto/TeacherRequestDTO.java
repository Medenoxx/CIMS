package com.example.jasarevic_javatest.dto;

import com.example.jasarevic_javatest.enums.AllowedSubjects;
import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class TeacherRequestDTO {
    private String name;
    private Integer age;
    private AllowedSubjects subject;
}
