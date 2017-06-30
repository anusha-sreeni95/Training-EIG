package com.endurance.products.teamtraining.crudapi.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by anusha on 30/6/17.
 */
public class EmployeeService {
   @Autowired
   private EmployeeRepository employeeRepository;

   public Employee findByName(String name){
       Employee employee=employeeRepository.findByName(name);
       if(employee!=null){
           return employee;
       }
       else{
           return null;
       }
   }

    public ArrayList<Employee> findAllEmployees() {
        ArrayList<Employee> employees=employeeRepository.findAll();
        return employees;
    }

    public Employee updatePassword(String name, String password){
        Employee employee=employeeRepository.findByName(name);
        employee.setPassword(password);
        return employee;
    }

    public void deleteEmployee(String name) throws DataAccessException {
        Employee employee=employeeRepository.findByName(name);
        if(employee!=null){
            employeeRepository.delete(employee.getId());
        }
    }
}
