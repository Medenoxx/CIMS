package com.example.jasarevic_javatest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Random;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Schüler")

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String name;
    private Integer age;
    @Column(unique = true, nullable = false)
    private Integer socialSecurityNumber;
    //Random 10-stellige Zahl generieren
    //hab die vorgegebene SSN aus der Postman-Abfrage gelöscht weil meine random generierte nicht funktioniert hätte
    @PrePersist
    public void generatedSSN(){
        this.socialSecurityNumber = new Random().nextInt(900000000) + 100000000;
    }

    //Mehrere Students eine Lesson
    @ManyToOne
    @JoinColumn(name ="lessonId")
    private Lesson lesson;
}
