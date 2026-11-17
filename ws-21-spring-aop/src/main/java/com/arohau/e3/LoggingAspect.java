package com.arohau.e3;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
public class LoggingAspect {
    public static final Logger LOGGER = Logger.getLogger(LoggingAspect.class.getName());

    @Pointcut("within(com.arohau.e3.LogicV3)")
    public void logPointcutWithin() {}

    @Before("logPointcutWithin()")
    public void logMethodCallsWithinAdvice() {
        LOGGER.warning("In Aspect from within");
    }
}
