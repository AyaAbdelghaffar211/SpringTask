package com.example.demo.controller;

import com.example.demo.dtos.CourseDTO;
import com.example.demo.exception.CourseNotFoundException;
import com.example.demo.exception.InvalidCourseException;
import com.example.demo.model.Course;
import com.example.demo.service.CourseService;
import jakarta.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController{

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping( "/view/{id}")
    public CourseDTO viewCourseDetails(@PathVariable Long id) {
        return courseService.findByID(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCourse(@RequestBody @Valid CourseDTO newCourse) {
        courseService.addCourse(newCourse);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCourse(@RequestBody String newDescription, @PathVariable Long id) {
        courseService.updateCourseDescription(id,newDescription);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }

    @GetMapping( "/discover")
    public List<CourseDTO> recommendCourses() throws JAXBException {
        return courseService.recommendCourses();
    }

    @GetMapping("/")
    public Page<CourseDTO> viewAllCourses(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return courseService.getAllCourses(page,size);
    }
}
