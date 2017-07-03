package com.endurance.products.teamtraining.crudapi.utils;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;


import org.springframework.stereotype.Component;

import javax.xml.soap.SOAPPart;

@Aspect
@Component
public class LoggingService {

    final static Logger logger = Logger.getLogger(LoggingService.class);

    @Before("execution(* com.endurance.products.teamtraining.crudapi.auth.AuthController.createEmployee(*,*))")
    public void beforeSampleCreation(JoinPoint jp) throws Throwable {
        logger.info("A request was issued to register a employee: ");
    }
}
