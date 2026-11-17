package com.arohau;

public interface FillFreeToExplore {
    /*
    Explore the following below or do Optional Practical task.

    @Pointcut("execution(public * get*())") // all public Getters everywhere
    @Pointcut("execution(* * get*())") // ERROR specify public, private, etc. or nothing
    @Pointcut("execution(* get*())") // allGetters everywhere
    @Pointcut("execution(* get*(*))") // allGetters everywhere, which has any args
    @Pointcut("execution(* get*(..))") // allGetters everywhere, which has any args or has none

    @Pointcut("execution(* get*(Long))") // allGetters everywhere, which has Long arg
    @Pointcut("execution(String get*(Long))") // allGetters everywhere, which has Long arg and return String

    @Pointcut("execution(* com.example.Service.*(..))") // all methods of Service class
    @Pointcut("execution(* com.example.*.*(..))") // all methods of all classes in example package
    @Pointcut("execution(* com.example..*.*(..))") // all methods of all classes in example package and subpackages
    @Pointcut("execution(* com.example.*Impl.*(..))") // all methods of *Impl classes in example package
    @Pointcut("execution(* com.example.User*Impl.*(..))") // all methods of User*Impl classes in example package (UserAccountImpl)
    @Pointcut("execution(* com.example..*Impl.*(..))") // all methods of *Impl classes in example package and subpackages

    @Pointcut("within(com.example.Service)") // all methods of Service class
    @Pointcut("within(com.example.*Impl)") // all methods of *Impl classes
    @Pointcut("within(com.example.*)") // all methods of all classes in example package
    @Pointcut("within(com.example..*)") // all methods of all classes in example package and subpackages

    @Pointcut("args(String)") // all methods with String Arg
    public void stringArgMethods() {}

    @Pointcut("args(name)") // all methods with String Arg
    public void stringArgMethods(String name) {}

    Difference @After and @AfterReturning? // @AfterReturning works only after success method processing and returns
    Difference @After and @AfterThrowing? // @AfterThrowing works only after throwing exception during method processing
    @After? // @After is considered as after finally - cause behaves as a finally block

    @AfterReturning(pointcut = "args(name)", returning = "returnValue") // applied to all methods with String param, and returns String value
    public void stringArgMethods(String name, String returnValue) {}

    @AfterReturning(pointcut = "args(name)", returning = "returnValue") // applied to all methods with String param, and returns any value
    public void stringArgMethods(String name, Object returnValue) {}

    @AfterThrowing(pointcut = "args(name)", throwing = "ex") 				// applied to all methods with String param, and throws Runtime exception
    public void stringArgMethods(String name, RuntimeException ex) {}


    @Around("execution(public * get*())") // around all getters
    public void aroundAdvice(ProceedingJoinPoint pjp) {
        // before method
        pjp.proceed();
        // after method
    }

    @Around("execution(public * get*())") // around all getters
    public Object aroundAdvice(ProceedingJoinPoint pjp) {
        Object returnValue = pjp.proceed();
        return returnValue;
    }


     */
}
