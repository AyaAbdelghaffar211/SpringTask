package com.example.demo.model;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {

    @Test
    void shouldCreateAuthorWithNonNullFields() {
        String name = "John Doe";
        String email = "john.doe@example.com";
        Date birthdate = new Date(90, 1, 1); // Deprecated way, consider using Calendar or LocalDate for new code

        Author author = new Author(name, email, birthdate);

        assertNotNull(author);
        assertEquals(name, author.getName());
        assertEquals(email, author.getEmail());
        assertEquals(birthdate, author.getBirthdate());
    }

    @Test
    void shouldSetAndGetAuthorFields() {
        Author author = new Author();
        String name = "Jane Doe";
        String email = "jane.doe@example.com";
        Date birthdate = new Date(95, 5, 15); // Deprecated way, consider using Calendar or LocalDate for new code

        author.setName(name);
        author.setEmail(email);
        author.setBirthdate(birthdate);

        assertEquals(name, author.getName());
        assertEquals(email, author.getEmail());
        assertEquals(birthdate, author.getBirthdate());
    }


}