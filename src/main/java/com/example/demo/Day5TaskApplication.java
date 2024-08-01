package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

public class Day5TaskApplication {

	public static void main(String[] args) {

		//test when using @Primary to give a priority
		ApplicationContext context = SpringApplication.run(ApplicationConfig.class);
		CourseService courseService = context.getBean(CourseService.class);
		courseService.printRecommendedCourses();
	}

}
