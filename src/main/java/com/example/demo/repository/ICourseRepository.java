package com.example.demo.repository;

import com.example.demo.model.Course;

public interface ICourseRepository {

    public void addCourse(Course course);
    public void updateCourseDescription(int id, String description);
    public Course findByID(int id);
    public void deleteCourse(int id);
}
