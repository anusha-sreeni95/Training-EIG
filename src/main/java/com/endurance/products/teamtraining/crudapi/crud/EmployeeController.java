package com.endurance.products.teamtraining.crudapi.crud;

import com.endurance.products.teamtraining.crudapi.auth.Employee;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by anusha on 30/6/17.
 */
@RequestMapping("/emp")
@RestController
@EnableAutoConfiguration
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value="/{name}",method= RequestMethod.GET)
    public Employee getEmployee(@PathVariable("name") String name, HttpServletResponse httpServletResponse){
        Employee employee=employeeService.findByName(name);
        if(employee!=null){
            httpServletResponse.setStatus(200);
        }
        else{
            httpServletResponse.setStatus(404);
        }
        return employee;
    }

    @RequestMapping(value="/",method=RequestMethod.GET)
    public ArrayList<Employee> findAllEmployees(HttpServletResponse httpServletResponse){
        ArrayList<Employee> employees = employeeService.findAllEmployees();
        if(employees != null){
            httpServletResponse.setStatus(200);
        }
        else {
            httpServletResponse.setStatus(404);
        }
        return employees;
    }

    @RequestMapping(value="/update/{name}/{password}",method=RequestMethod.PUT)
    public Employee updatePassword(@PathVariable("name") String name,@PathVariable("password") String password){
        Employee employee=employeeService.updatePassword(name,password);
        return employee;
    }

    @RequestMapping(value="/delete/{name}",method= RequestMethod.DELETE)
    public void deleteEmployee(@PathVariable("name") String name){
        employeeService.deleteEmployee(name);
    }

}
