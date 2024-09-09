package com.example.demo.recommender;

import com.example.courses.CourseList;
import com.example.demo.client.CourseRecommenderClient;
import com.example.demo.dtos.CourseDTO;
import com.example.demo.model.Course;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.xml.bind.Unmarshaller;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component

public class CSCourseRecommender implements ICourseRecommender {

    private final CourseRecommenderClient courseRecommenderClient;

    @Autowired
    public CSCourseRecommender(CourseRecommenderClient courseRecommenderClient) {
        this.courseRecommenderClient = courseRecommenderClient;
    }

    @Override
    public List<CourseDTO> recommend() throws JAXBException {

        String coursesXML = courseRecommenderClient.recommendCourses();

        JAXBContext jaxbContext = JAXBContext.newInstance(CourseList.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        CourseList courseList = (CourseList) jaxbUnmarshaller.unmarshal(new StringReader(coursesXML));

        System.out.println(coursesXML);

        return courseList.getCourse().stream()
                .map(course -> new CourseDTO(course.getName(), course.getDescription(), course.getCredits()))
                .collect(Collectors.toList());
    }
}
