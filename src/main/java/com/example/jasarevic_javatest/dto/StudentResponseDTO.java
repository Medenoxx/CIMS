package com.example.jasarevic_javatest.dto;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class StudentResponseDTO {
    private Integer Id;
    private String name;
    private Integer age;
    private Integer socialSecurityNumber;
    private Integer lessonId;
}