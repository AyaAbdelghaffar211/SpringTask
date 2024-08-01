package com.example.demo;

import com.example.demo.model.Course;
import com.example.demo.repository.JdbcCourseRepository;
import com.example.demo.service.CourseService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Day5TaskApplication {

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		JdbcCourseRepository courseRepository = context.getBean(JdbcCourseRepository.class);
		courseRepository.addCourse(new Course(100, "Test course 1", "Test course description 1",4));
		courseRepository.addCourse(new Course(200, "Test course 2", "Test course description 2",5));
		courseRepository.addCourse(new Course(300, "Test course 3", "Test course description 3",6));
		courseRepository.deleteCourse(1);
		courseRepository.updateCourseDescription(2,"Course 2 new description");
		Course course = courseRepository.findByID(3);
		System.out.println(course);

	}

}
