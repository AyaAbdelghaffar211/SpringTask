package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="courseRecommenderClient", url = "http://localhost:8081")
public interface CourseRecommenderClient {

    @GetMapping("/api/recommend/cs")
    String recommendCourses();
}
