package com.example.demo;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ApplicationConfig {

    @Bean
    public CourseService courseService(ICourseRecommender csCourseRecommender) {
        return new CourseService(csCourseRecommender);
    }

    @Bean
    public ICourseRecommender csCourseRecommender(){
        return new CSCourseRecommender();
    }

    @Bean
    public ICourseRecommender mathCourseRecommender(){
        return new MathCourseRecommender();
    }
}
