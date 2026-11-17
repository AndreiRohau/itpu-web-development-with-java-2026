package com.arohau.e4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan("com.arohau.e4")
@EnableAspectJAutoProxy
public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);

        LogicV4 logic = context.getBean(LogicV4.class);

        logic.perform();
        logic.textLength("asdasd");
        logic.textLength("asdasd", 123);
    }
}
