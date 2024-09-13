package com.example.jasarevic_javatest.services;

import com.example.jasarevic_javatest.customException.EntityNotFoundException;
import com.example.jasarevic_javatest.dto.TeacherRequestDTO;
import com.example.jasarevic_javatest.dto.TeacherResponseDTO;
import com.example.jasarevic_javatest.entity.Lesson;
import com.example.jasarevic_javatest.entity.Teacher;
import com.example.jasarevic_javatest.repository.LessonRepo;
import com.example.jasarevic_javatest.repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TeacherService {

    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private LessonRepo lessonRepo;


    //Hilfsfunktion für Convert
    //Liste auf Null gesetzt weil ich am Anfang noch keine Einträge habe
    public TeacherResponseDTO convertTeacherToTeacherResponseDTO(Teacher teacher) {
        return new TeacherResponseDTO(teacher.getId(), teacher.getName(), teacher.getAge(), teacher.getSubject(), null);
    }


    //Hilfsfunktion einen einzigen Lehrer holen
    public TeacherResponseDTO getOneTeacherById(Integer teacherId) {
        return convertTeacherToTeacherResponseDTO(getTeacherById(teacherId));
    }

    //Lehrer hinzufügen
    public TeacherResponseDTO addTeacher(TeacherRequestDTO teacherRequestDTO) {
        Teacher teacher = new Teacher(teacherRequestDTO.getName(), teacherRequestDTO.getAge(), teacherRequestDTO.getSubject());
        teacherRepo.save(teacher);

        return new TeacherResponseDTO(teacher.getId(), teacher.getName(), teacher.getAge(), teacher.getSubject(), null);
    }

    //alle Lehrer anzeigen
    public List<TeacherResponseDTO> getAllTeachers() {
        Iterable<Teacher> teachers = teacherRepo.findAll();
        List<TeacherResponseDTO> teacherResponseDTOs = new ArrayList<>();
        for (Teacher teacher : teachers) {
            teacherResponseDTOs.add(convertTeacherToTeacherResponseDTO(teacher));
        }
        return teacherResponseDTOs;
    }

    public Teacher getTeacherById(Integer id) {
        Optional<Teacher> teacherOptional = teacherRepo.findById(id);
        if (teacherOptional.isEmpty()) {
            throw new EntityNotFoundException("Kein Trainer gefunden!");
        }
        return teacherOptional.get();
    }

    public TeacherResponseDTO updateTeacherData(Integer teacherId, TeacherRequestDTO teacherRequestDTO) {
        Optional<Teacher> teacherOptional = teacherRepo.findById(teacherId);

        if (teacherOptional.isEmpty()) {
            throw new EntityNotFoundException("Kein Trainer mit der ID " + teacherId + " gefunden.");
        }

        Teacher teacher = teacherOptional.get();

        // Überprüfen, ob dem Lehrer Unterrichtseinheiten zugeordnet sind
        //findByTeacher ist im Lesson-Repo
        List<Lesson> assignedLessons = lessonRepo.findByTeacher(teacher);
        if (!assignedLessons.isEmpty()) {
            if (!teacher.getSubject().equals(teacherRequestDTO.getSubject())) {
                throw new EntityNotFoundException("Das Unterrichtsfach kann nicht geändert werden, da dem Lehrer bereits Unterrichtseinheiten zugeordnet sind.");
            }
        }
        teacher.setName(teacherRequestDTO.getName());
        teacher.setAge(teacherRequestDTO.getAge());
        teacher.setSubject(teacherRequestDTO.getSubject());

        Teacher updatedTeacher = teacherRepo.save(teacher);

        return convertTeacherToTeacherResponseDTO(updatedTeacher);
    }
}
