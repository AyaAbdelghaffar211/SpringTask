package com.example.demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssessmentTest {

    @Test
    void testAssessmentConstructorAndGettersSetters() {
        Course course = new Course();
        Assessment assessment = new Assessment(1L, "Midterm", course);

        assertEquals(1L, assessment.getId());
        assertEquals("Midterm", assessment.getContent());
        assertEquals(course, assessment.getCourse());

        Course newCourse = new Course();
        assessment.setId(2L);
        assessment.setContent("Final Exam");
        assessment.setCourse(newCourse);

        assertEquals(2L, assessment.getId());
        assertEquals("Final Exam", assessment.getContent());
        assertEquals(newCourse, assessment.getCourse());
    }

}