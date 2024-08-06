package com.example.demo.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Course DTO")
public class CourseDTO {

    private Long id;
    private String name;
    private String description;
    private int credit;
}
