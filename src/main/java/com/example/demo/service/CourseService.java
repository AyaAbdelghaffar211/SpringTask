package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.recommender.ICourseRecommender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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
            System.out.println(c.getId() + " " + c.getName() + " " + c.getDescription() + " " + c.getCredit());
        }
    }
}
