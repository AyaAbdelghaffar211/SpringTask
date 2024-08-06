package com.example.demo.dtos.mappers;

import com.example.demo.dtos.CourseDTO;
import com.example.demo.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "credit", target = "credit")
    CourseDTO courseToCourseDTO(Course course);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "credit", target = "credit")
    Course courseDTOToCourse(CourseDTO courseDTO);
}
