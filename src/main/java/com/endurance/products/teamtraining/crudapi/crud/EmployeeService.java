package com.endurance.products.teamtraining.crudapi.crud;

import com.endurance.products.teamtraining.crudapi.auth.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by anusha on 30/6/17.
 */
@Transactional
@Service
public class EmployeeService {
   @Autowired
   private EmployeeRepository employeeRepository;

   public Map<String, String> findByName(String name, HttpServletResponse httpServletResponse){
       Employee employee=employeeRepository.findByName(name);
       if(employee!=null){
           httpServletResponse.setStatus(200);
       }
       else{
           httpServletResponse.setStatus(404);
       }
       Map<String, String> resp = new HashMap<String, String>();
       resp.put("username", employee.getName());
       resp.put("authtoken", employee.getAuthToken());
       return resp;
   }

    public ArrayList<Map<String,String>> findAllEmployees(HttpServletResponse httpServletResponse) {
        ArrayList<Employee> employees = employeeRepository.findAll();
        if(employees != null){
            httpServletResponse.setStatus(200);
        }
        else {
            httpServletResponse.setStatus(404);
        }
        ArrayList<Map<String,String>> resp = new ArrayList<Map<String,String>>();

        for (Employee employee:employees) {
            Map<String,String> temp=new HashMap<String,String>();
            temp.put("username", employee.getName());
            temp.put("authtoken", employee.getAuthToken());
            resp.add(temp);
        }
        return resp;
    }

    public Employee updatePassword(String name, String password){
        Employee employee=employeeRepository.findByName(name);
        employee.setPassword(password);
        return employee;
    }

    public void deleteEmployee(String name) throws DataAccessException {
        Employee employee=employeeRepository.findByName(name);
        if(employee!=null){
            employeeRepository.delete(employee.getName());
        }
    }
}
