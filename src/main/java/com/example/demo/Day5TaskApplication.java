package com.example.demo;

import com.example.demo.configuration.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableFeignClients
public class Day5TaskApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ApplicationConfig.class,args);
	}

}
