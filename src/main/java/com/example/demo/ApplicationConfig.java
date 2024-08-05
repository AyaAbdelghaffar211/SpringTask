package com.example.demo;


import course.project.CSCourseRecommender;
import course.project.ICourseRecommender;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Configuration
@ComponentScan("com.example.demo")
public class ApplicationConfig {

    @Bean (name = "CS")
    public ICourseRecommender csCourseRecommender() {
        return new CustomCSCourseRecommender();
    }

    @Bean (name = "Math")
    public ICourseRecommender mathCourseRecommender() {
        return new MathCourseRecommender();
    }
}
