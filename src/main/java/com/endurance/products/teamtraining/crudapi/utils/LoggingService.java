package com.endurance.products.teamtraining.crudapi.utils;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class LoggingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingService.class);

    @Before("execution(* com.endurance.products.teamtraining.crudapi.auth.AuthController.createEmployee (java.lang.String)) && args(sampleName)")
    public void beforeSampleCreation(String sampleName) {
        LOGGER.info("A request was issued for a sample name: "+sampleName);
    }
}
