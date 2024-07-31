package com.example.demo.recommender;

import com.example.demo.model.Course;

import java.util.List;

public interface ICourseRecommender {

    public List<Course> recommend();
}
