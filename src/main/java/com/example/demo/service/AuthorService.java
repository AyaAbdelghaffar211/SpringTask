package com.example.demo.service;

import com.example.demo.dtos.AuthorDTO;
import com.example.demo.dtos.mappers.AuthorMapper;
import com.example.demo.exception.AuthorNotFoundException;
import com.example.demo.model.Author;
import com.example.demo.repository.IAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorMapper authorMapper = AuthorMapper.INSTANCE;
    private IAuthorRepository authorRepository;

    @Autowired
    public AuthorService(IAuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorDTO findByEmail(String email) {

        if (email == null || email.isEmpty()) {
            throw new RuntimeException("Email could not be empty");
        }

        Author author = authorRepository.findByEmail(email);
        if (author == null) {
            throw new AuthorNotFoundException();
        }
        return authorMapper.authorToAuthorDTO(author);
    }

    public void addAuthor(AuthorDTO authorDTO){
        Author author = authorMapper.authorDTOToAuthor(authorDTO);
        if (authorRepository.findByEmail(author.getEmail()) != null) {
            throw new IllegalArgumentException("An author with this email already exists.");
        }
        authorRepository.save(author);
    }
}
