package com.example.demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    void shouldCreateCourseWithNonNullFields() {
        String name = "Introduction to Java";
        String description = "Learn the basics of Java programming.";
        int credit = 3;

        Course course = new Course(name, description, credit);

        assertNotNull(course);
        assertEquals(name, course.getName());
        assertEquals(description, course.getDescription());
        assertEquals(credit, course.getCredit());
    }

    @Test
    void shouldSetAndGetCourseFields() {
        Course course = new Course();
        String name = "Advanced Java";
        String description = "In-depth Java programming concepts.";
        int credit = 4;

        course.setName(name);
        course.setDescription(description);
        course.setCredit(credit);

        assertEquals(name, course.getName());
        assertEquals(description, course.getDescription());
        assertEquals(credit, course.getCredit());
    }

    @Test
    void shouldReturnCorrectToString() {
        String name = "Introduction to Python";
        String description = "Basics of Python programming.";
        int credit = 3;
        Course course = new Course(name, description, credit);

        String toStringResult = course.toString();

        assertTrue(toStringResult.contains("id="));
        assertTrue(toStringResult.contains("name=" + name));
        assertTrue(toStringResult.contains("description=" + description));
        assertTrue(toStringResult.contains("credit=" + credit));
    }

}