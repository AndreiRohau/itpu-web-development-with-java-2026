package com.arohau.e1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan("com.arohau.e1")
@EnableAspectJAutoProxy
public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        SomeService someService = context.getBean(SomeService.class);

        UserDto userDto = new UserDto();
        userDto.setLogin("PII_login");
        userDto.setPassword("PII_password");

        UserDto returnedUser = someService.login(userDto);

        System.out.println(returnedUser);
        System.out.println("=".repeat(40));
        someService.textLength("12345");
    }
}
