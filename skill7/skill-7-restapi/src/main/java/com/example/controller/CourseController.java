package com.example.controller;

import com.example.entity.Course;
import com.example.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService service;

    @GetMapping
    public ResponseEntity<List<Course>> getAll() {
        return ResponseEntity.ok(service.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        Course c = service.getCourse(id);
        if (c == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        return ResponseEntity.ok(c);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Course course) {
        if (course.getTitle() == null)
            return ResponseEntity.badRequest().body("Invalid data");
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addCourse(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Course course) {
        Course updated = service.updateCourse(id, course);
        if (updated == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Course deleted = service.deleteCourse(id);
        if (deleted == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        return ResponseEntity.ok("Deleted successfully");
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<List<Course>> search(@PathVariable String title) {
        return ResponseEntity.ok(service.searchByTitle(title));
    }
}
