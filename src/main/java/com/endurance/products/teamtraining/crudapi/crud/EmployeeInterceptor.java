package com.endurance.products.teamtraining.crudapi.crud;

import com.endurance.products.teamtraining.crudapi.auth.Employee;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeInterceptor implements HandlerInterceptor {

    Employee current_employee = new Employee();

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String authToken = httpServletRequest.getHeader("authToken");
        if(current_employee.getCurrentEmployeeAuth().equals(authToken) && authToken!=null){
            System.out.print(current_employee.getCurrentEmployeeAuth());
            System.out.print(authToken);
            return true;
        }
        System.out.print(current_employee.getCurrentEmployeeAuth());
        System.out.print(authToken);
        System.out.print("batman");
        httpServletResponse.setStatus(403);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}