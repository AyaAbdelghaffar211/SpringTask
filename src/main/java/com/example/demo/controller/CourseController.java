package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.repository.ICourseRepository;
import com.example.demo.repository.JdbcCourseRepository;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping( "/view/{id}")
    public Course viewCourseDetails(@PathVariable int id) {
        return courseService.findByID(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCourse(@RequestBody Course newCourse) {
       courseService.addCourse(newCourse);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCourse(@RequestBody String newDescription, @PathVariable int id) {
        courseService.updateCourseDescription(id,newDescription);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
    }

    @GetMapping( "/discover")
    public List<Course> viewAllCourses() {
        return courseService.recommendCourses();
    }
}
