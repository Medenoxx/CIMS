package com.example.jasarevic_javatest.entity;

import com.example.jasarevic_javatest.enums.AllowedSubjects;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Trainer")

public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String name;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private AllowedSubjects subject;

    //ein Lehrer mehrere Lessons
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessonList = new ArrayList<>();

    public Teacher(String name, Integer age, AllowedSubjects subject) {
        this.name = name;
        this.age = age;
        this.subject = subject;
    }

//    public void addLesson(Lesson lesson) {
//
//    }
}
