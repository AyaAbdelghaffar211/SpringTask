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
        courses.add(new Course(4,"Introduction to Math", "Math101",6));
        courses.add(new Course(5,"Linear Algebra", "Math201",8));
        courses.add(new Course(6,"Calculus", "Math301",8));
        return courses;
    }
}
