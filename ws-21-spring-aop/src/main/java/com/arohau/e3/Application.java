package com.arohau.e3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan("com.arohau.e3")
@EnableAspectJAutoProxy
public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);

        LogicV3 logic = context.getBean(LogicV3.class);

        logic.perform();
        logic.textLength("asdasd");
    }
}
