package com.arohau.e1;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
public class LoggingAspect {
    public static final Logger LOGGER = Logger.getLogger(LoggingAspect.class.getName());

    @Pointcut("@annotation(Log)")
    public void logPointcut() {
        LOGGER.warning("logPointcut - never runs");
    }

    @Before("logPointcut()")
    public void logBeforeAllMethodCallsAdvice(){
        LOGGER.warning("Aspect Before");
    }

    @After("logPointcut()")
    public void logAfterAllMethodCallsAdvice(){
        LOGGER.warning("Aspect After");
    }
}
