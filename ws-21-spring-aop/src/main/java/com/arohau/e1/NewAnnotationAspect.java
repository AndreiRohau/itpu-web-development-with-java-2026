package com.arohau.e1;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class NewAnnotationAspect {

    @After("@annotation(NewAnnotation)")
    public void newAnnotationAdvice(){
        System.out.println("newAnnotationAdvice worked");
    }

}
