package com.example.demo.service;

import com.example.demo.controller.GlobalExceptionHandler;
import com.example.demo.dtos.CourseDTO;
import com.example.demo.dtos.mappers.CourseMapper;
import com.example.demo.exception.CourseNotFoundException;
import com.example.demo.exception.InvalidCourseException;
import com.example.demo.model.Course;
import com.example.demo.recommender.ICourseRecommender;
import com.example.demo.repository.ICourseRepository;
import jakarta.xml.bind.JAXBException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
@Setter
@Getter
public class CourseService {

    private ICourseRecommender courseRecommender;
    private ICourseRepository courseRepository;

    private CourseMapper courseMapper = CourseMapper.INSTANCE;

    public CourseService(ICourseRecommender courseRecommender, ICourseRepository courseRepository) {
        this.courseRecommender = courseRecommender;
        this.courseRepository = courseRepository;
    }

    public List<CourseDTO> recommendCourses() throws JAXBException {
        return courseRecommender.recommend();
    }

    public void addCourse(CourseDTO courseDTO){
        Course course = courseMapper.courseDTOToCourse(courseDTO);
        courseRepository.save(course);
    }

    public void updateCourseDescription(Long id, String description){
        if(description == null || description.isEmpty()){
            throw new RuntimeException("Description could not be empty");
        }

        Optional<Course> course = courseRepository.findById(id);

        if(course == null || !course.isPresent()){
            throw new CourseNotFoundException();
        } else{
            Course coursePresent = course.get();
            coursePresent.setDescription(description);
            courseRepository.save(coursePresent);
        }
    }

    public CourseDTO findByID(Long id){
        Optional<Course> course = courseRepository.findById(id);
        if(course == null || !course.isPresent()){
            throw new CourseNotFoundException();
        }
        return courseMapper.courseToCourseDTO(course.get());
    }

    public void deleteCourse(Long id){
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException();
        }
        courseRepository.deleteById(id);
    }

    public Page<CourseDTO> getAllCourses(int page, int size){
        if(page<0 || size < 0){
            throw new IllegalArgumentException("Page and Size must be greater than 0");
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<Course> coursesPage = courseRepository.findAll(pageable);
        return coursesPage.map(courseMapper::courseToCourseDTO);
    }
}
