package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private ICourseRecommender csCourseRecommender;

    public CourseService(ICourseRecommender csCourseRecommender) {
        this.csCourseRecommender = csCourseRecommender;
    }

    @Autowired
    public void setCourseRecommender(ICourseRecommender csCourseRecommender) {
        this.csCourseRecommender = csCourseRecommender;
    }

    public void printRecommendedCourses() {
        List<Course> courses = csCourseRecommender.recommend();
        for(Course c:courses){
            System.out.println(c.getCourseCode() + " " + c.getCourseName());
        }
    }
}
