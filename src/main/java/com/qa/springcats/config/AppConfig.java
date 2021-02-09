package com.qa.springcats.config;

import java.time.LocalTime;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	@Bean
	public String helloWorld() {
		return "hello world";
	}
	@Bean
	public String getTime() {
		return LocalTime.now().toString();
	}
	@Bean 
	public ModelMapper getMapper() {
		return new ModelMapper();
	}

}
