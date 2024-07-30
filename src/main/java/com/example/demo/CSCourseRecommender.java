package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class CSCourseRecommender implements ICourseRecommender {

    @Override
    public List<Course> recommend() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Introduction to Computer Science", "CS101"));
        courses.add(new Course("Object Oriented Programming", "CS201"));
        courses.add(new Course("Data Structures and algorithms", "CS301"));
        return courses;
    }
}
