package com.example.demo.dtos.mappers;

import com.example.demo.dtos.CourseDTO;
import com.example.demo.model.Course;
//import demo.target.generated-sources.ja
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    CourseDTO courseToCourseDTO(Course course);

    Course courseDTOToCourse(CourseDTO courseDTO);


}
