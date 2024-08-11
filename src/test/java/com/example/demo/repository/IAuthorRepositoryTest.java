package com.example.demo.repository;

import com.example.demo.model.Author;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class IAuthorRepositoryTest {

    @Autowired
    IAuthorRepository authorRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        authorRepository.deleteAll();
    }

    @Test
    void shouldGetAuthorIfEmailExists() {

        String name = "John";
        String email = "John@gmail.com";
        Date birthdate = new Date(200,02,11);
        Author author = new Author(name,email,birthdate);
        authorRepository.save(author);

        Author authorRetreived = authorRepository.findByEmail(email);

        assertThat(authorRetreived).isNotNull();
        assertThat(authorRetreived.getName()).isEqualTo(name);
        assertThat(authorRetreived.getEmail()).isEqualTo(email);
        assertThat(authorRetreived.getBirthdate()).isEqualTo(birthdate);
    }

    @Test
    void shouldGetNullAuthorIfEmailNotExists(){

        String nonExistentEmail = "nonexistentemail@gmail.com";

        Author authorRetrieved = authorRepository.findByEmail(nonExistentEmail);

        assertThat(authorRetrieved).isNull();
    }
}