package com.example.demo.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Getter
@Setter
@Schema(description = "Author DTO")
@RequiredArgsConstructor
@NoArgsConstructor
public class AuthorDTO {

    private Long id;

    @NotNull(message = "Name cannot be null")
    @NonNull
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Email cannot be null")
    @NonNull
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotNull(message = "Birthdate cannot be null")
    @NonNull
    @Past(message = "Birthdate cannot be in the future")
    private Date birthdate;
}
