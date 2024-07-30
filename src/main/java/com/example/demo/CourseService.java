package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private ICourseRecommender courseRecommender;

    public CourseService(ICourseRecommender courseRecommender) {
        this.courseRecommender = courseRecommender;
    }

    @Autowired
    public void setCourseRecommender(ICourseRecommender courseRecommender) {
        this.courseRecommender = courseRecommender;
    }

    public void printRecommendedCourses() {
        List<Course> courses = courseRecommender.recommend();
        for(Course c:courses){
            System.out.println(c.getCourseCode() + " " + c.getCourseName());
        }
    }
}
