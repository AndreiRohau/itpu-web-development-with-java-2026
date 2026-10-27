package com.arohau.springspecifics;

import org.springframework.context.annotation.*;

@Configuration
//@Component
@ComponentScan("com.arohau.springspecifics")
@PropertySource("classpath:/application.properties")
public class SpringSpecificsMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringSpecificsMain.class);

        SomeBean someBean = context.getBean("someBean", SomeBean.class);
        someBean.doSomething();
        someBean = null;

        Object customBean1a = context.getBean("customBean");
        Object customBean1b = context.getBean("customBean");
        Object customBean2a = context.getBean("customBeanTwo");
        Object customBean2b = context.getBean("customBeanTwo");

        // this will release resources
        context.registerShutdownHook();

    }

    @Bean
    public Object customBean() {
        return new Object();
    }

    @Bean
    public Object customBeanTwo() {
        return customBean();
    }

    /*
    @Configuration vs @Component

    you do can create i.e SpringSpecificsMain.java class then stereotype with @Component and add @Beans declaration to it.
    In this way it will look as a configuration, main difference is that when annotated class with @Configuration @Bean
    annotated methods are proxy using CGLIB which made in code calls after the first one to return bean from context
    instead of execute method again and create another instance as happens when using @Component with @Bean

    Example
    @Configuration
    customBean1a == customBean1b == customBean2a == customBean2b

    @Component
    customBean1a == customBean1b
    customBean2a == customBean2b
    BUT
    customBean1a != customBean2a

     */
}
