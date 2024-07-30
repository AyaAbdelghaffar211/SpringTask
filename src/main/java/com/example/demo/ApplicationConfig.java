package com.example.demo;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ApplicationConfig {

    @Bean
    public CourseService courseService(ICourseRecommender courseRecommender) {
        return new CourseService(courseRecommender);
    }

    @Bean
    @Primary
    public ICourseRecommender csCourseRecommender(){
        return new CSCourseRecommender();
    }

    @Bean
    public ICourseRecommender mathCourseRecommender(){
        return new MathCourseRecommender();
    }
}
