package com.example.demo.recommender;

import com.example.demo.model.Course;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class MathCourseRecommender implements ICourseRecommender {

    @Override
    public List<Course> recommend() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Introduction to Math", "Math101",6));
        courses.add(new Course("Linear Algebra", "Math201",8));
        courses.add(new Course("Calculus", "Math301",8));
        return courses;
    }
}
