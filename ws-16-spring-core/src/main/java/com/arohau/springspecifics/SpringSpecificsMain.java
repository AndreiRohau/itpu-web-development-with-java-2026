package com.arohau.springspecifics;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

@Configuration
//@Component

@ComponentScan("com.arohau.springspecifics")
@PropertySource("classpath:/application.properties")
public class SpringSpecificsMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringSpecificsMain.class);

        DetectorBean detectorBean = context.getBean("detectorBean", DetectorBean.class);
        DetectorBean detectorBeanSame = context.getBean("detectorBean", DetectorBean.class);
        if (DetectorBean.class.isAnnotationPresent(Scope.class)
                && DetectorBean.class.getAnnotation(Scope.class).value().equalsIgnoreCase("singleton")) {
            assert detectorBean == detectorBeanSame;
        } else if (DetectorBean.class.isAnnotationPresent(Scope.class)
                && DetectorBean.class.getAnnotation(Scope.class).value().equalsIgnoreCase("prototype")) {
            assert detectorBean != detectorBeanSame;
        }
        System.out.println("--------------------------------------------------------------");

        detectorBean.detect();


        configurationVsComponent(context);

        System.out.println("--------------------------------------------------------------");

        // this will release resources
        context.registerShutdownHook();
        context.close();
    }

    @Bean(initMethod="customInit", destroyMethod="customDestroy")
    public DetectorBean detectorBean() {
        return new DetectorBean();
    }

    private static void configurationVsComponent(AnnotationConfigApplicationContext context) {
        System.out.println();
        Object customBean1a = context.getBean("customBean");
        Object customBean1b = context.getBean("customBean");
        Object customBean2a = context.getBean("customBeanTwo");
        Object customBean2b = context.getBean("customBeanTwo");

        if (SpringSpecificsMain.class.isAnnotationPresent(Configuration.class)) {
            System.out.println("SpringSpecificsMain.class annotated with @Configuration");
            System.out.println("customBean is the SAME as customBeanTwo");
            assert customBean1a == customBean1b;
            assert customBean2a == customBean2b;

            assert customBean1b == customBean2a;

        } else if (SpringSpecificsMain.class.isAnnotationPresent(Component.class)) {
            System.out.println("SpringSpecificsMain.class annotated with @Component");
            System.out.println("customBean is NOT the SAME as customBeanTwo");
            assert customBean1a == customBean1b;
            assert customBean2a == customBean2b;

            assert customBean1b != customBean2a;
        }
        System.out.println();
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
