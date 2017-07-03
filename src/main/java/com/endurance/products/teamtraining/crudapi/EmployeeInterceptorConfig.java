package com.endurance.products.teamtraining.crudapi;

import com.endurance.products.teamtraining.crudapi.crud.EmployeeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class EmployeeInterceptorConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new EmployeeInterceptor()).addPathPatterns("/emp/*");
    }
}