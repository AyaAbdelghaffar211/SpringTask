package com.example.demo.dtos.mappers;

import com.example.demo.dtos.CourseDTO;
import com.example.demo.model.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CourseMapperTest {

    CourseMapper courseMapper = CourseMapper.INSTANCE;

    @Test
    void shouldReturnCourseGivenCourseDTO() {
        Course course = new Course("CS", "Intro to cs", 4);
        course.setId(1L);

        CourseDTO coursedto = courseMapper.courseToCourseDTO(course);

        assertThat(coursedto.getId()).isEqualTo(1L);
        assertThat(coursedto.getName()).isEqualTo("CS");
        assertThat(coursedto.getDescription()).isEqualTo("Intro to cs");
        assertThat(coursedto.getCredit()).isEqualTo(4);
    }

    @Test
    void shouldReturnNullgivenCourseDTONull(){
        Course course = courseMapper.courseDTOToCourse(null);
        assertThat(course).isNull();
    }

    @Test
    void shouldReturnCourseDTOGivenCourse() {
        CourseDTO coursedto = new CourseDTO("CS", "Intro to cs", 4);
        coursedto.setId(1L);

        Course course = courseMapper.courseDTOToCourse(coursedto);

        assertThat(course.getId()).isEqualTo(1L);
        assertThat(course.getName()).isEqualTo("CS");
        assertThat(course.getDescription()).isEqualTo("Intro to cs");
        assertThat(course.getCredit()).isEqualTo(4);
    }

    @Test
    void shouldReturnNullgivenCourseNull(){
        CourseDTO coursedto = courseMapper.courseToCourseDTO(null);
        assertThat(coursedto).isNull();
    }
}