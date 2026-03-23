package com.example.controller;

import com.example.exception.*;
import com.example.model.Student;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable String id) {

        int studentId;

        try {
            studentId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("ID must be a number");
        }

        if (studentId != 1) {
            throw new StudentNotFoundException("Student not found with ID: " + studentId);
        }

        return new Student(1, "Sai");
    }
}
