package com.example.service;

import com.example.entity.Course;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseService {

    private Map<Integer, Course> courseMap = new HashMap<>();

    public List<Course> getAllCourses() {
        return new ArrayList<>(courseMap.values());
    }

    public Course getCourse(int id) {
        return courseMap.get(id);
    }

    public Course addCourse(Course course) {
        courseMap.put(course.getCourseId(), course);
        return course;
    }

    public Course updateCourse(int id, Course course) {
        if (!courseMap.containsKey(id)) return null;
        courseMap.put(id, course);
        return course;
    }

    public Course deleteCourse(int id) {
        return courseMap.remove(id);
    }

    public List<Course> searchByTitle(String title) {
        List<Course> result = new ArrayList<>();
        for (Course c : courseMap.values()) {
            if (c.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(c);
            }
        }
        return result;
    }
}
