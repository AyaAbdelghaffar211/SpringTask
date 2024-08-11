package com.example.demo.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Schema(description = "Course DTO")
@RequiredArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    private Long id;

    @NotNull(message = "Name cannot be null")
    @NonNull
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Description cannot be null")
    @NonNull
    @NotBlank(message = "Description cannot be empty")
    private String description;

    @NonNull
    @Positive(message = "Credit must be greater than 0")
    private int credit;
}
