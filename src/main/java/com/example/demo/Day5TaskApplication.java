package com.example.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Day5TaskApplication {

	public static void main(String[] args) {

		//test when using @Qualifier to give a priority
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		CourseService courseService = context.getBean(CourseService.class);
		courseService.printRecommendedCourses();
	}

}
