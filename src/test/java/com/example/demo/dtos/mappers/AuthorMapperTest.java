package com.example.demo.dtos.mappers;

import com.example.demo.dtos.AuthorDTO;
import com.example.demo.model.Author;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AuthorMapperTest {

    AuthorMapper authorMapper = AuthorMapper.INSTANCE;

    @Test
    void shouldReturnAuthorWhenGivenAuthorDTO() {
        Author author = new Author("John", "john@gmail.com", new Date(2000, 2, 11));
        author.setId(1L);

        AuthorDTO authorDTO = authorMapper.authorToAuthorDTO(author);

        assertThat(authorDTO.getId()).isEqualTo(1L);
        assertThat(authorDTO.getName()).isEqualTo("John");
        assertThat(authorDTO.getEmail()).isEqualTo("john@gmail.com");
        assertThat(authorDTO.getBirthdate()).isEqualTo(new Date(2000, 2, 11));
    }

    @Test
    void shouldReturnNullWhenAuthorDTONull(){
        Author author = authorMapper.authorDTOToAuthor(null);
        assertThat(author).isNull();
    }

    @Test
    void shouldReturnAuthorDTOWhenGivenAuthor() {
        AuthorDTO authorDTO = new AuthorDTO("John", "john@gmail.com", new Date(2000, 2, 11));
        authorDTO.setId(1L);

        Author author = authorMapper.authorDTOToAuthor(authorDTO);

        assertThat(author.getId()).isEqualTo(1L);
        assertThat(author.getName()).isEqualTo("John");
        assertThat(author.getEmail()).isEqualTo("john@gmail.com");
        assertThat(author.getBirthdate()).isEqualTo(new Date(2000, 2, 11));
    }

    @Test
    void shouldReturnNullWhenAuthorNull(){
        AuthorDTO authorDTO = authorMapper.authorToAuthorDTO(null);
        assertThat(authorDTO).isNull();
    }
}