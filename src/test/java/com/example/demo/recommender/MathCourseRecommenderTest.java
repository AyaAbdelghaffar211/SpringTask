package com.example.demo.recommender;

import com.example.demo.model.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MathCourseRecommenderTest {

    private MathCourseRecommender mathCourseRecommender;

    @BeforeEach
    void setUp() {
        mathCourseRecommender = new MathCourseRecommender();
    }

    @Test
    void shouldReturnRecommendedCourses() {
        List<Course> expectedCourses = List.of(
                new Course("Introduction to Math", "Math101",6),
                new Course("Linear Algebra", "Math201",8),
                new Course("Calculus", "Math301",8)
        );

        List<Course> recommendedCourses = mathCourseRecommender.recommend();

        assertEquals(expectedCourses.size(), recommendedCourses.size());
        for (int i = 0; i < expectedCourses.size(); i++) {
            Course expectedCourse = expectedCourses.get(i);
            Course actualCourse = recommendedCourses.get(i);
            assertEquals(expectedCourse.getName(), actualCourse.getName());
            assertEquals(expectedCourse.getDescription(), actualCourse.getDescription());
            assertEquals(expectedCourse.getCredit(), actualCourse.getCredit());
        }
    }
}