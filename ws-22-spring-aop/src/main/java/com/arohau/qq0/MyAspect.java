package com.arohau.qq0;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
public class MyAspect {

    public static final Logger LOGGER = Logger.getLogger(MyAspect.class.getName());

    @Pointcut("@annotation(MyAnnotation)")
    public void myAnnotationPointcut() {
    }

    @Before("myAnnotationPointcut()")
    public void myAnnotationPointcutBeforeAdvice(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        LOGGER.info(signature.toLongString());
    }

//    @Pointcut("")
//    public void myCustomPointcut() {
//    }
//
//    @Before("myCustomPointcut()")
//    public void myCustomPointcutBeforeAdvice(JoinPoint joinPoint) {
//        Signature signature = joinPoint.getSignature();
//        LOGGER.info(signature.toLongString());
//    }
}
