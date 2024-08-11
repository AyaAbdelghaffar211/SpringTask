package com.example.demo.controller;

import com.example.demo.dtos.AuthorDTO;
import com.example.demo.exception.AuthorNotFoundException;
import com.example.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
public class AuthorController{

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping( "/view/{email}")
    public AuthorDTO viewAuthor(@PathVariable String email) {
        return authorService.findByEmail(email);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAuthor(@RequestBody @Valid AuthorDTO newAuthor) {
        authorService.addAuthor(newAuthor);
    }
}
