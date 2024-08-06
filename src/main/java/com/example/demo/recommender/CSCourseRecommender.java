package com.example.demo.recommender;

import com.example.demo.model.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class CSCourseRecommender implements ICourseRecommender {

    @Override
    public List<Course> recommend() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Introduction to Computer Science", "CS101",4));
        courses.add(new Course("Object Oriented Programming", "CS201",4));
        courses.add(new Course("Data Structures and algorithms", "CS301",4));
        return courses;
    }
}
