package com.example.demo.service;

import com.example.demo.dtos.AuthorDTO;
import com.example.demo.exception.AuthorNotFoundException;
import com.example.demo.model.Author;
import com.example.demo.repository.IAuthorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    private AuthorService authorService;

    @Mock
    private IAuthorRepository authorRepository;

    @BeforeEach
    void setUp() {
        authorService = new AuthorService(authorRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldReturnAuthorWhenEmailExists() {
        String name = "John";
        String email = "John@gmail.com";
        Date birthdate = new Date(2000,02,11);
        Author author = new Author(name,email,birthdate);
        when(authorRepository.findByEmail(email)).thenReturn(author);

        AuthorDTO authorRetreived = authorService.findByEmail(email);

        assertNotNull(authorRetreived);
        assertThat(authorRetreived.getName()).isEqualTo(name);
        assertThat(authorRetreived.getEmail()).isEqualTo(email);
        assertThat(authorRetreived.getBirthdate()).isEqualTo(birthdate);
        verify(authorRepository).findByEmail(email);
    }

    @Test
    void shouldThrowAuthorNotFoundExceptionWhenEmailNotExists() {
        when(authorRepository.findByEmail(any())).thenReturn(null);

        assertThatThrownBy(() -> authorService.findByEmail("some nonexistent email"))
                .isInstanceOf(AuthorNotFoundException.class)
                .hasMessageContaining("Author Not Found");
    }

    @Test
    void shouldThrowRuntimeExceptionWhenEmailIsEmpty() {
        assertThatThrownBy(() -> authorService.findByEmail(""))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Email could not be empty");
    }

    @Test
    void shouldThrowRuntimeExceptionWhenEmailIsNull() {
        assertThatThrownBy(() -> authorService.findByEmail(null))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Email could not be empty");
    }

    @Test
    void shouldAddAuthorWhenFieldsAreSatisfied() {
        String name = "John";
        String email = "John@gmail.com";
        Date birthdate = new Date(2000,02,11);
        AuthorDTO authordto = new AuthorDTO(name,email,birthdate);

        authorService.addAuthor(authordto);
        ArgumentCaptor<Author> authorArgumentCapture = ArgumentCaptor.forClass(Author.class);

        verify(authorRepository).save(authorArgumentCapture.capture());
        Author authorSaved = authorArgumentCapture.getValue();
        assertNotNull(authorSaved);
        assertThat(authorSaved.getName()).isEqualTo(name);
        assertThat(authorSaved.getEmail()).isEqualTo(email);
        assertThat(authorSaved.getBirthdate()).isEqualTo(birthdate);
}

    @Test
    void shouldThrowIllegalArgumentExceptionWhenEmailIsTaken() {
        String name = "John";
        String email = "John@gmail.com";
        Date birthdate = new Date(200,02,11);
        Author author = new Author(name,email,birthdate);
        AuthorDTO authordto = new AuthorDTO(name,email,birthdate);

        when(authorRepository.findByEmail(any())).thenReturn(author);

        assertThatThrownBy(() -> authorService.addAuthor(authordto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("An author with this email already exists.");

        verify(authorRepository,never()).save(any());
    }
}