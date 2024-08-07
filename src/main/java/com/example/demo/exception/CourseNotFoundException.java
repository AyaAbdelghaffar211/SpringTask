package com.example.demo.exception;

public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException() {
        super("Course Not Found");
    }
}
