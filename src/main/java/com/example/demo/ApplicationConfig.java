package com.example.demo;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ApplicationConfig {

    @Bean
    public CourseService courseService(@Qualifier("Math") ICourseRecommender courseRecommender) {
        return new CourseService(courseRecommender);
    }

    @Bean(name="CS")
    public ICourseRecommender csCourseRecommender(){
        return new CSCourseRecommender();
    }

    @Bean(name="Math")
    public ICourseRecommender mathCourseRecommender(){
        return new MathCourseRecommender();
    }
}
