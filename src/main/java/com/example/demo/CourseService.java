package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class CourseService {

    private ICourseRecommender courseRecommender;

    public CourseService(@Qualifier("CS") ICourseRecommender courseRecommender) {
        this.courseRecommender = courseRecommender;
    }

    @Autowired
    public void setCourseRecommender(@Qualifier("CS") ICourseRecommender courseRecommender) {
        this.courseRecommender = courseRecommender;
    }

    public void printRecommendedCourses() {
        List<Course> courses = courseRecommender.recommend();
        for(Course c:courses){
            System.out.println(c.getCourseCode() + " " + c.getCourseName());
        }
    }
}
