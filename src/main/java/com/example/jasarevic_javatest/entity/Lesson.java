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
@Table(name = "Fach")

public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lessonId;
    private String startDate;
    private Float duration;
    private AllowedSubjects subjects;

    //mehrere Lessons zu einem Teacher möglich
    @ManyToOne
    @JoinColumn (name = "teacherId")
    private Teacher teacher;

    //eine Lesson mehrere Students
    //orphanRemoval: Students die nicht mehr zu einer Lesson gehören werden gelöscht
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> studentList = new ArrayList<>();


}