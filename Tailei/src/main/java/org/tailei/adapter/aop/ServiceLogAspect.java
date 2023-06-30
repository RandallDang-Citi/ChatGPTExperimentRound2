package org.tailei.adapter.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ServiceLogAspect {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(public * com.xyz.service.StockMining*.*(..))")
    public void serviceLog(){}

    @Before("serviceLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        logger.info("AOP: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + " start...");
        logger.info("ARGS: " + Arrays.toString(joinPoint.getArgs()));
    }

    @After("serviceLog()")
    public  void doAfter(JoinPoint joinPoint){
        logger.info("AOP: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + " end...");

    }

}
