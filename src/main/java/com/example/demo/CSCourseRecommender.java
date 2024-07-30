package com.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("CS")
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
