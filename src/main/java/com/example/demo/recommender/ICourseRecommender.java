package com.example.demo.recommender;

import com.example.demo.dtos.CourseDTO;
import com.example.demo.model.Course;
import jakarta.xml.bind.JAXBException;

import java.util.List;

public interface ICourseRecommender {

    public List<CourseDTO> recommend() throws JAXBException;
}
