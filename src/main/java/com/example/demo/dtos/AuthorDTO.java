package com.example.demo.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Schema(description = "Author DTO")
public class AuthorDTO {

    private Long id;
    private String name;
    private String email;
    private Date birthdate;
}
