package com.arohau.e5;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
public class LoggingAspect {
    public static final Logger LOGGER = Logger.getLogger(LoggingAspect.class.getName());

    // applied to methods of class LogicV5, which has public String and any name and any args 1 or 255
    @Pointcut("within(com.arohau.e5.LogicV5) && execution(public String com.arohau.e5.LogicV5.*(..))")
    public void logPointcutWithArgs() {
    }

    @Before("logPointcutWithArgs()")
    public void logMethodCallsWithArgsAdvice() {
        LOGGER.warning("In Aspect from Args");
    }
}
