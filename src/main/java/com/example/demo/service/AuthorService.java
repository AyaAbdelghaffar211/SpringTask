package com.example.demo.service;

import com.example.demo.dtos.AuthorDTO;
import com.example.demo.dtos.CourseDTO;
import com.example.demo.dtos.mappers.AuthorMapper;
import com.example.demo.dtos.mappers.CourseMapper;
import com.example.demo.model.Author;
import com.example.demo.model.Course;
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

    public Optional<Author> findByEmail(String email) {
        return authorRepository.findByEmail(email);
    }

    public void addAuthor(AuthorDTO authorDTO){
        Author author = authorMapper.authorDTOToAuthor(authorDTO);
        authorRepository.save(author);
    }
}
