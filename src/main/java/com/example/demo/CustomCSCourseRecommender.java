package com.example.demo;

import course.project.CSCourseRecommender;
import course.project.Course;

import java.util.ArrayList;
import java.util.List;

public class CustomCSCourseRecommender extends CSCourseRecommender {

    @Override
    public List<Course> recommend() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Custom course 1", "Math101"));
        courses.add(new Course("Custom course 2", "Math201"));
        courses.add(new Course("Custom course 3", "Math301"));
        return courses;
    }
}
