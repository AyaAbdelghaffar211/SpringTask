package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class MathCourseRecommender implements ICourseRecommender{

    @Override
    public List<Course> recommend() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Introduction to Math", "Math101"));
        courses.add(new Course("Linear Algebra", "Math201"));
        courses.add(new Course("Calculus", "Math301"));
        return courses;
    }
}
