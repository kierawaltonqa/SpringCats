package com.qa.springcats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringCatsApplication {

	public static void main(String[] args) {
		
		ApplicationContext beanBag = SpringApplication.run(SpringCatsApplication.class, args);
		
		System.out.println(beanBag.getBean("helloWorld", String.class));
		System.out.println("Server Time:\t" + beanBag.getBean("getTime",String.class));
		
	
	}
	
}
