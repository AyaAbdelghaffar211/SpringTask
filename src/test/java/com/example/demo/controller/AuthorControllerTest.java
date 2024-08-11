package com.example.demo.controller;

import com.example.demo.dtos.AuthorDTO;
import com.example.demo.exception.AuthorNotFoundException;
import com.example.demo.service.AuthorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.text.SimpleDateFormat;

import java.util.Date;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    private AuthorDTO authorDTO = new AuthorDTO("John","John@gmail.com",new Date());

    @BeforeEach
    void setUp() {
        authorDTO.setId(1L);
    }

    @Test
    void shouldReturnAuthorWhenAuthorEmailExists() throws Exception {

        when(authorService.findByEmail("John@gmail.com")).thenReturn(authorDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/authors/view/John@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(authorDTO.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(authorDTO.getEmail()));
    }

    @Test
    void shouldThrowAuthorNotFoundExceptionWhenEmailDoesNotExist() throws Exception {
        when(authorService.findByEmail(anyString())).thenThrow(new AuthorNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/authors/view/nonexistentemail@example.com"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldAddAuthorWhenValidInput() throws Exception {
        mockMvc.perform(post("/api/authors/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(authorDTO)))
                .andExpect(status().isCreated());

        verify(authorService).addAuthor(any());
    }

    @Test
    void shouldReturnConflictWhenAuthorEmailAlreadyExists() throws Exception {
        doThrow(new IllegalArgumentException("An author with this email already exists."))
                .when(authorService).addAuthor(any(AuthorDTO.class));

        mockMvc.perform(post("/api/authors/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new AuthorDTO())))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestForInvalidAuthorDTO() throws Exception {
        authorDTO.setName("");

        mockMvc.perform(post("/api/authors/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(authorDTO)))
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