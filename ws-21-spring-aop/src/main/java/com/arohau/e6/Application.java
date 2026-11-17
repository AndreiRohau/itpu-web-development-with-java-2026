package com.arohau.e6;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan("com.arohau.e6")
@EnableAspectJAutoProxy
public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);

        LogicV6 logic = context.getBean(LogicV6.class);

        logic.combineValues("asd", 123);
        logic.combineValuesV2("asd", 123);
    }
}
