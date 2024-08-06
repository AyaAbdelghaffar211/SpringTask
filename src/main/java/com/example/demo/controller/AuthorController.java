package com.example.demo.controller;

import com.example.demo.dtos.AuthorDTO;
import com.example.demo.dtos.CourseDTO;
import com.example.demo.model.Author;
import com.example.demo.model.Course;
import com.example.demo.service.AuthorService;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping( "/view/{email}")
    public Optional<Author> viewAuthor(@PathVariable String email) {
        return authorService.findByEmail(email);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAuthor(@RequestBody AuthorDTO newAuthor) {
        authorService.addAuthor(newAuthor);
    }
}
