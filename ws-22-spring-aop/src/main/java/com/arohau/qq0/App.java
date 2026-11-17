package com.arohau.qq0;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan("com.arohau.qq0")
@EnableAspectJAutoProxy
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        MyService myService = context.getBean(MyService.class);

        String s = myService.getString("asd");
        System.out.println(s);



    }
}
