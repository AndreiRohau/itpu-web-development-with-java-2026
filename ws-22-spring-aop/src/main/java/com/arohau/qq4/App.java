package com.arohau.qq4;

import com.arohau.qq4.sub.MySubService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan("com.arohau.qq4")
@EnableAspectJAutoProxy
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        MyService myService = context.getBean(MyService.class);
        MySubService mySubService = context.getBean(MySubService.class);

        myService.get();
        myService.getField("asd");
        myService.getList();

        mySubService.get();
        mySubService.getField("asd");
        mySubService.getList();
    }
}
