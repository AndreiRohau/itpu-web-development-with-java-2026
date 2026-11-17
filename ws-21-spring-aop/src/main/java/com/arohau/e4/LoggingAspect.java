package com.arohau.e4;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
public class LoggingAspect {
    public static final Logger LOGGER = Logger.getLogger(LoggingAspect.class.getName());

    //                              com.arohau.e4.LogicV4.textLength("asdasd");
    @Pointcut("execution(public int com.arohau.e4.LogicV4.textLength(String))")
    public void logPointcutWithArgs() {
    }

    @Before("logPointcutWithArgs()")
    public void logMethodCallsWithArgsAdvice() {
        LOGGER.warning("In Aspect from Args");
    }
}
