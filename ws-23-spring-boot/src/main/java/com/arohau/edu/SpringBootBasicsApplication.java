package com.arohau.edu;

import com.arohau.edu.service.SomeService;
import com.arohau.edu.service.SomeServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class SpringBootBasicsApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(SpringBootBasicsApplication.class, args);

		SomeService bean = run.getBean("someService", SomeService.class);

		final String someText = "HeLLo EveRYbODy";

		System.out.println(bean.textToUppercase(someText));
		System.out.println(bean.textToLowercase(someText));

		System.out.println("=".repeat(100));

		SomeService prototype1 = run.getBean("someServicePrototype", SomeService.class);
		SomeService prototype2 = run.getBean("someServicePrototype", SomeService.class);
		SomeService prototype3 = run.getBean("someServicePrototype", SomeService.class);

		System.out.println("stop");
	}

	@Bean
	@Scope("prototype")
	public SomeService someServicePrototype() {
		return new SomeServiceImpl();
	}


}
