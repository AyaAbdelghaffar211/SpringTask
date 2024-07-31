package com.example.demo;

import com.example.demo.model.Course;
import com.example.demo.repository.JdbcCourseRepository;
import com.example.demo.service.CourseService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Day5TaskApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ApplicationConfig.class);
		JdbcCourseRepository courseRepository = context.getBean(JdbcCourseRepository.class);
		courseRepository.addCourse(new Course(1, "Test course 1", "Test course description 1",4));
		courseRepository.addCourse(new Course(2, "Test course 2", "Test course description 2",5));
		courseRepository.addCourse(new Course(3, "Test course 3", "Test course description 3",6));
		courseRepository.deleteCourse(1);
		courseRepository.updateCourseDescription(2,"Course 2 new description");
		Course course = courseRepository.findByID(3);
		System.out.println(course);

	}

}
