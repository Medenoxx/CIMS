package com.example.jasarevic_javatest.services;


import com.example.jasarevic_javatest.customException.EntityBadRequestException;
import com.example.jasarevic_javatest.dto.LessonRequestDTO;
import com.example.jasarevic_javatest.dto.LessonResponseDTO;
import com.example.jasarevic_javatest.dto.StudentResponseDTO;
import com.example.jasarevic_javatest.entity.Lesson;
import com.example.jasarevic_javatest.entity.Student;
import com.example.jasarevic_javatest.entity.Teacher;
import com.example.jasarevic_javatest.repository.LessonRepo;
import com.example.jasarevic_javatest.repository.StudentRepo;
import com.example.jasarevic_javatest.repository.TeacherRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LessonService {
    @Autowired
    private LessonRepo lessonRepo;
    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private StudentRepo studentRepo;

    //Hilfsfunktionen convert
    public LessonResponseDTO convertLessonToLessonDTO(Lesson lesson) {
        return new LessonResponseDTO(lesson.getLessonId(), lesson.getStartDate(), lesson.getDuration(), lesson.getSubjects(), lesson.getTeacher().getId(), getStudentsByLesson(lesson.getLessonId()));
    }

    public StudentResponseDTO convertStudentToStudentDTO(Student student) {
        return new StudentResponseDTO(student.getId(), student.getName(), student.getAge(), student.getSocialSecurityNumber(), student.getLesson().getLessonId());
    }

    public LessonResponseDTO addLesson (LessonRequestDTO lessonRequestDTO){
        Optional<Teacher> teacherOptional = teacherRepo.findById(lessonRequestDTO.getTeacherId());

        Teacher teacher = teacherOptional.get();
        if (teacher.getSubject().equals(lessonRequestDTO.getSubject())) {
            Lesson lesson = new Lesson();
            lesson.setStartDate(lessonRequestDTO.getStartDate());
            lesson.setDuration(lessonRequestDTO.getDuration());
            lesson.setSubjects(lessonRequestDTO.getSubject());
            lesson.setTeacher(teacher);
            lessonRepo.save(lesson);
            return convertLessonToLessonDTO(lesson);
        } else
            throw new EntityBadRequestException("Der angegebene Lehrer unterrichtet das Fach " + lessonRequestDTO.getSubject() + " nicht.");
    }

    public List<LessonResponseDTO> getAllLessons() {
        Iterable<Lesson> lessons = lessonRepo.findAll();
        List<LessonResponseDTO> lessonResponseDTOS = new ArrayList<>();
        for (Lesson lesson : lessons) {
            //Hier wird die Liste befüllt und am Ende der Zeile wird die Liste der StudentResponseDTOS angehängt
            lessonResponseDTOS.add(new LessonResponseDTO(lesson.getLessonId(), lesson.getStartDate(), lesson.getDuration(), lesson.getSubjects(), lesson.getTeacher().getId(), getStudentsByLesson(lesson.getLessonId())));
        }
        return lessonResponseDTOS;
    }

    //Habe dies Funktion "getStudentsByLesson" geschrieben weil ich eine Endlosschleife als Response bekommen habe, als ich nur eine Liste von Students zurückgeben wollte
    //(ist passiert weil Klasse Student ein Objekt Lesson beinhaltet und das wiederrum die Liste der Teilnehmer beinhaltet usw usw.
    public List<StudentResponseDTO> getStudentsByLesson(Integer lessonId) {
        List<StudentResponseDTO> studentsInLesson = new ArrayList<>();
        for (Student student : studentRepo.findAll()) {
            if (Objects.equals(student.getLesson().getLessonId(), lessonId)) {
                studentsInLesson.add(convertStudentToStudentDTO(student));
            }
        }
        return studentsInLesson;
    }

    public LessonResponseDTO getLessonById(Integer lessonId) {

        Optional<Lesson> lessonOptional = lessonRepo.findById(lessonId);

        if (lessonOptional.isEmpty()) {
            throw new EntityNotFoundException("Es konnte kein Kurs mit der angegebenen ID gefunden werden.");
        }
        return convertLessonToLessonDTO(lessonOptional.get());
    }


    public String deleteLessonById(Integer lessonId) {
        Optional<Lesson> lessonOptional = lessonRepo.findById(lessonId);
        if (lessonOptional.isPresent()) {
            Lesson lesson = lessonOptional.get();
            //Prüfung ob Student Lesson zugeordnet ist
            for (Student student : lesson.getStudentList()) {
                if (student != null)
                    throw new EntityBadRequestException("Kurs dann nicht gelöscht werden, da Teilnehmer zugebucht sind!");
            }
            // Löscht Kurs, wenn es keine zugebuchten Teilnehmer gibt
            lessonRepo.deleteById(lessonId);
            return "Kurs mit der ausgewählten ID wurde erfolgreich gelöscht.";
        } else {
            throw new EntityNotFoundException("Kurs mit der ID " + lessonId + "wurde nicht gefunden.");
        }
    }
}


