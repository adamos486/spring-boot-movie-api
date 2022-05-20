package com.kohls.interview.exercise.logger;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class GeneralLogger {

  @Pointcut("execution(* com.kohls.interview.exercise.*.*.*(..))")
  public void logMessage(){}

  @Before("logMessage()")
  void beforeMethod(JoinPoint jp){
    log.info(" method name "+jp.getSignature() +" Args: "+jp.getArgs());
  }

}
