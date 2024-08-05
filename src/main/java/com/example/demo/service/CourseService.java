package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.recommender.ICourseRecommender;
import com.example.demo.repository.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseService {

    private ICourseRecommender courseRecommender;
    private ICourseRepository courseRepository;

    public CourseService(ICourseRecommender courseRecommender, ICourseRepository courseRepository) {
        this.courseRecommender = courseRecommender;
        this.courseRepository = courseRepository;
    }

    public List<Course> recommendCourses() {
        return courseRecommender.recommend();
    }

    public void addCourse(Course course){
        courseRepository.addCourse(course);
    }

    public void updateCourseDescription(int id, String description){
        courseRepository.updateCourseDescription(id, description);
    }

    public Course findByID(int id){
        return courseRepository.findByID(id);
    }

    public void deleteCourse(int id){
        courseRepository.deleteCourse(id);
    }
}
