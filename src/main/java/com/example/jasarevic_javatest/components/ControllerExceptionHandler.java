package com.example.jasarevic_javatest.components;

import com.example.jasarevic_javatest.customException.EntityBadRequestException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


//habe die exceptions ziemlich allgemein gehalten, damit ich am Ende nur die Message ändern muss und es wiederverwendbar wird
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    public ProblemDetail entityNotFound(EntityNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Id nicht gefunden!");
        problemDetail.setDetail(e.getMessage());
        return problemDetail;
    }

    @ExceptionHandler
    public ProblemDetail entityBadRequest(EntityBadRequestException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Fehlerhafter Request!");
        problemDetail.setDetail(e.getMessage());
        return problemDetail;
    }
}
