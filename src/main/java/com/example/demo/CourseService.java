package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class CourseService {

    private ICourseRecommender courseRecommender;

    public CourseService(ICourseRecommender CSCourseRecommender) {
        this.courseRecommender = CSCourseRecommender;
    }

    @Autowired
    public void setCourseRecommender(ICourseRecommender CSCourseRecommender) {
        this.courseRecommender = CSCourseRecommender;
    }

    public void printRecommendedCourses() {
        List<Course> courses = courseRecommender.recommend();
        for(Course c:courses){
            System.out.println(c.getCourseCode() + " " + c.getCourseName());
        }
    }
}
