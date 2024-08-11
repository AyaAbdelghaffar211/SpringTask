package com.example.demo.controller;

import com.example.demo.dtos.CourseDTO;
import com.example.demo.exception.CourseNotFoundException;
import com.example.demo.model.Course;
import com.example.demo.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = CourseController.class)
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    private CourseDTO courseDTO = new CourseDTO("CS","Intro to programming",4);

    @BeforeEach
    void setUp() {
        courseDTO.setId(1L);
    }

    @Test
    void shouldReturnCourseWhenCourseExists() throws Exception {
        when(courseService.findByID(courseDTO.getId())).thenReturn(courseDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/courses/view/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(courseDTO.getName()))
                .andExpect(jsonPath("$.description").value(courseDTO.getDescription()))
                .andExpect(jsonPath("$.credit").value(courseDTO.getCredit()));
    }

    @Test
    void shouldReturnNotFoundWhenCourseDoesNotExist() throws Exception {
        when(courseService.findByID(courseDTO.getId())).thenThrow(new CourseNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/courses/view/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldAddCourseWhenArgumentsValid() throws Exception {
        mockMvc.perform(post("/api/courses/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(courseDTO)))
                .andExpect(status().isCreated());

        verify(courseService).addCourse(any());
    }

    @Test
    void shouldReturnBadRequestForInvalidCourseDTO() throws Exception {
        courseDTO.setName("");

        mockMvc.perform(post("/api/courses/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(courseDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldUpdateCourseDescriptionWhenArgumentsValidAndCourseExists() throws Exception {
        String newDescription = "new description";

        mockMvc.perform(put("/api/courses/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newDescription))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturnNotFoundWhenCourseDoesNotExistUpdatingDescription() throws Exception {

        String newDescription = "new description";
        doThrow(new CourseNotFoundException()).when(courseService).updateCourseDescription(courseDTO.getId(), newDescription);

        mockMvc.perform(put("/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newDescription))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnBadRequestWhenDescriptionIsEmpty() throws Exception {
        doThrow(new RuntimeException("Description could not be empty")).when(courseService).updateCourseDescription(courseDTO.getId(), "");

        mockMvc.perform(put("/api/courses/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldDeleteCourseWhenCourseExists() throws Exception {

        mockMvc.perform(delete("/api/courses/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturnNotFoundWhenCourseDoesNotExistWhenDeleting() throws Exception {
        doThrow(new CourseNotFoundException()).when(courseService).deleteCourse(courseDTO.getId());

        mockMvc.perform(delete("/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void recommendCourses() {
    }@Test
    void shouldReturnRecommendedCourses() throws Exception {
        Course course1 = new Course("Course 1", "Description 1", 3);
        Course course2 = new Course("Course 2", "Description 2", 4);
        List<Course> courses = List.of(course1, course2);

        when(courseService.recommendCourses()).thenReturn(courses);

        mockMvc.perform(get("/api/courses/discover")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(course1.getName()))
                .andExpect(jsonPath("$[0].description").value(course1.getDescription()))
                .andExpect(jsonPath("$[0].credit").value(course1.getCredit()))
                .andExpect(jsonPath("$[1].name").value(course2.getName()))
                .andExpect(jsonPath("$[1].description").value(course2.getDescription()))
                .andExpect(jsonPath("$[1].credit").value(course2.getCredit()));
    }

    @Test
    void shouldReturnPageOfCourses() throws Exception {
        List<CourseDTO> courseList = List.of(courseDTO);
        Page<CourseDTO> coursePage = new PageImpl<>(courseList, PageRequest.of(0, 10), courseList.size());

        when(courseService.getAllCourses(0, 10)).thenReturn(coursePage);

        mockMvc.perform(get("/api/courses/")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].id").value(courseDTO.getId()))
                .andExpect(jsonPath("$.content[0].name").value(courseDTO.getName()))
                .andExpect(jsonPath("$.content[0].description").value(courseDTO.getDescription()))
                .andExpect(jsonPath("$.content[0].credit").value(courseDTO.getCredit()))
                .andExpect(jsonPath("$.totalElements").value(courseList.size()));
    }

    @Test
    void shouldReturnEmptyPageWhenNoCourses() throws Exception {
        List<CourseDTO> courseList = List.of();
        Page<CourseDTO> coursePage = new PageImpl<>(courseList, PageRequest.of(0, 10), 0);

        when(courseService.getAllCourses(0, 10)).thenReturn(coursePage);

        // Act & Assert
        mockMvc.perform(get("/api/courses/")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isEmpty())
                .andExpect(jsonPath("$.totalElements").value(0));
    }

    @Test
    void shouldReturnBadRequestWhenFieldsInvalid() throws Exception {
        mockMvc.perform(get("/api/courses/")
                        .param("page", "invalid") // Invalid page parameter
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}