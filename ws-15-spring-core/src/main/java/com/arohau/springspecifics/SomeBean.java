package com.arohau.springspecifics;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/*
Singleton
Prototype
Request
Session
Application
WebSocket
 */
@Component
@Scope("singleton")
public class SomeBean implements InitializingBean, DisposableBean {

    @Value("${my.custom.prop.1}")
    private String propVal1;

    @Value("${my.custom.prop.2}")
    private String propVal2;

    public SomeBean() {
        System.out.println("init-0 - some-constructor");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("init-1 - @PostConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("init-2 - InitializingBean # afterPropertiesSet()");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("destroy-1 - @PreDestroy");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy-2 - DisposableBean # destroy()");
    }

    public void doSomething() {
        System.out.println();
        System.out.println("... doing something ...");
        System.out.println("propVal1 = " + propVal1);
        System.out.println("propVal2 = " + propVal2);
        System.out.println();
    }
}
