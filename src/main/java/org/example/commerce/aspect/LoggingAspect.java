package org.example.commerce.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Around("@annotation(org.example.commerce.annotation.LoggingAnnotation)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Method will execute");
        Object result = joinPoint.proceed();
        log.info("Method executed");
        return result;
    }
}


