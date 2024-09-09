package com.example.demo.recommender;

import com.example.demo.model.Course;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSCourseRecommenderTest {

    private CSCourseRecommender csCourseRecommender;

    @BeforeEach
    void setUp() {
        //csCourseRecommender = new CSCourseRecommender();
    }

    @Test
    void shouldReturnRecommendedCourses() throws JAXBException {
        List<Course> expectedCourses = List.of(
                new Course("Introduction to Computer Science", "CS101", 4),
                new Course("Object Oriented Programming", "CS201", 4),
                new Course("Data Structures and algorithms", "CS301", 4)
        );

        List<Course> recommendedCourses = csCourseRecommender.recommend();

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