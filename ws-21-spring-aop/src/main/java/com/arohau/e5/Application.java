package com.arohau.e5;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan("com.arohau.e5")
@EnableAspectJAutoProxy
public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);

        LogicV5 logic = context.getBean(LogicV5.class);

        logic.perform();
        logic.textLength("asdasd");
        logic.returnText("some text");
        logic.returnConstantText();
    }
}
