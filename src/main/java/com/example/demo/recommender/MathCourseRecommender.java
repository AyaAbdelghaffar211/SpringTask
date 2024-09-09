package com.example.demo.recommender;

import com.example.demo.dtos.CourseDTO;
import com.example.demo.model.Course;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class MathCourseRecommender implements ICourseRecommender {

    @Override
    public List<CourseDTO> recommend() {
        List<CourseDTO> courses = new ArrayList<>();
        courses.add(new CourseDTO("Introduction to Math", "Math101",6));
        courses.add(new CourseDTO("Linear Algebra", "Math201",8));
        courses.add(new CourseDTO("Calculus", "Math301",8));
        return courses;
    }
}
