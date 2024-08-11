package com.example.demo.service;

import com.example.demo.dtos.AuthorDTO;
import com.example.demo.dtos.CourseDTO;
import com.example.demo.dtos.mappers.CourseMapper;
import com.example.demo.exception.AuthorNotFoundException;
import com.example.demo.exception.CourseNotFoundException;
import com.example.demo.model.Author;
import com.example.demo.model.Course;
import com.example.demo.recommender.ICourseRecommender;
import com.example.demo.repository.ICourseRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    private CourseService courseService;

    @Mock
    private ICourseRepository courseRepository;

    @Mock
    private ICourseRecommender courseRecommender;

    @Mock
    private CourseMapper courseMapper;

    CourseDTO courseDTO = new CourseDTO("CS101","Intro to programming",10);;
    Course course = new Course("CS101","Intro to programming",10);

    @BeforeEach
    void setUp() {
        courseDTO.setId(1L);
        course.setId(1L);

        courseService = new CourseService(courseRecommender,courseRepository);
        courseService.setCourseMapper(courseMapper);
    }
    @AfterEach
    void tearDown() {
    }

    @Test
    void recommendCourses() {
        when(courseRecommender.recommend()).thenReturn(List.of(course));

        List<Course> coursesRetrievd = courseService.recommendCourses();

        assertThat(coursesRetrievd.get(0)).isEqualTo(course);
    }

    @Test
    void shouldAddCourseWhenFieldsAreSatisfied() {
        when(courseMapper.courseDTOToCourse(courseDTO)).thenReturn(course);

        courseService.addCourse(courseDTO);
        ArgumentCaptor<Course> courseArgumentCapture = ArgumentCaptor.forClass(Course.class);

        verify(courseRepository).save(courseArgumentCapture.capture());
        Course courseSaved = courseArgumentCapture.getValue();
        assertNotNull(courseSaved);
        assertThat(courseSaved.getName()).isEqualTo(courseDTO.getName());
        assertThat(courseSaved.getDescription()).isEqualTo(courseDTO.getDescription());
        assertThat(courseSaved.getCredit()).isEqualTo(courseDTO.getCredit());
    }

    @Test
    void shouldUpdateCourseDescriptionIfCourseExistsAndFieldsAreSatisfied() {
        String newDescription = "Updated Course Description";
        when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));

        courseService.updateCourseDescription(course.getId(), newDescription);

        assertThat(course.getDescription()).isEqualTo(newDescription);
        verify(courseRepository).save(course);
    }

    @Test
    void shouldThrowCourseNotFoundExceptionWhenCourseNotExists() {
        when(courseRepository.findById(course.getId())).thenReturn(null);

        assertThatThrownBy(() -> courseService.updateCourseDescription(course.getId(), "Some Description"))
                .isInstanceOf(CourseNotFoundException.class)
                .hasMessageContaining("Course Not Found");
    }

    @Test
    void shouldThrowRuntimeExceptionWhenDescriptionIsNull() {

        assertThatThrownBy(() -> courseService.updateCourseDescription(course.getId(), null))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Description could not be empty");
    }

    @Test
    void shouldThrowRuntimeExceptionWhenDescriptionIsEmpty() {

        assertThatThrownBy(() -> courseService.updateCourseDescription(course.getId(), ""))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Description could not be empty");
    }

    @Test
    void shouldReturnCourseWhenIdExists() {
        when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));
        when(courseMapper.courseToCourseDTO(course)).thenReturn(courseDTO);

        CourseDTO courseRetreived = courseService.findByID(course.getId());

        assertNotNull(courseRetreived);
        assertThat(courseRetreived.getName()).isEqualTo(course.getName());
        assertThat(courseRetreived.getDescription()).isEqualTo(course.getDescription());
        assertThat(courseRetreived.getCredit()).isEqualTo(course.getCredit());
        verify(courseRepository).findById(course.getId());
    }

    @Test
    void shouldThrowCourseNotFoundExceptionWhenIdNotExists() {
        when(courseRepository.findById(any())).thenReturn(null);

        assertThatThrownBy(() -> courseService.findByID(any()))
                .isInstanceOf(CourseNotFoundException.class)
                .hasMessageContaining("Course Not Found");
    }

    @Test
    void shouldDeleteCourseWhenIdExists() {
        when(courseRepository.existsById(course.getId())).thenReturn(true);

        courseService.deleteCourse(course.getId());

        verify(courseRepository).deleteById(course.getId());
    }

    @Test
    void shouldThrowCourseNotFoundExceptionWhenIdNotExistsWhenDeleting() {
        when(courseRepository.existsById(any())).thenReturn(false);

        assertThatThrownBy(() -> courseService.deleteCourse(any()))
                .isInstanceOf(CourseNotFoundException.class)
                .hasMessageContaining("Course Not Found");
    }

    @Test
    void shouldReturnPageOfCoursesWhenFieldsAreValid() {
        int page = 0;
        int size = 5;
        Pageable pageable = PageRequest.of(page, size);
        List<Course> courses = List.of(course);
        Page<Course> coursesPage = new PageImpl<>(courses, pageable, courses.size());
        when(courseRepository.findAll(pageable)).thenReturn(coursesPage);
        when(courseMapper.courseToCourseDTO(course)).thenReturn(courseDTO);

        Page<CourseDTO> result = courseService.getAllCourses(page, size);

        assertThat(result.getContent().get(0)).isEqualTo(courseDTO);
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getNumber()).isEqualTo(page);
        assertThat(result.getSize()).isEqualTo(size);
    }

    @Test
    void shouldReturnEmptyPageWhenNoCourses() {
        int page = 0;
        int size = 5;
        Pageable pageable = PageRequest.of(page, size);
        Page<Course> emptyPage = new PageImpl<>(Collections.emptyList(), pageable, 0);
        when(courseRepository.findAll(pageable)).thenReturn(emptyPage);

        Page<CourseDTO> result = courseService.getAllCourses(page, size);

        assertThat(result.getTotalElements()).isEqualTo(0);
        assertThat(result.getNumber()).isEqualTo(page);
        assertThat(result.getSize()).isEqualTo(size);
    }

    @Test
    void shouldThrowExceptionWhenInvalidPageOrSize() {
        int invalidPage = -1;
        int invalidSize = -5;

        assertThatThrownBy(() -> courseService.getAllCourses(invalidPage, invalidSize))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Page and Size must be greater than 0");
    }
}