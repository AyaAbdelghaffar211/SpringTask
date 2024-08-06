package com.example.demo.dtos.mappers;

import com.example.demo.dtos.AuthorDTO;
import com.example.demo.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "birthdate", target = "birthdate")
    AuthorDTO authorToAuthorDTO(Author author);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "birthdate", target = "birthdate")
    Author authorDTOToAuthor(AuthorDTO authorDTO);
}
