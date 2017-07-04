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

@Transactional
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Map<String, String> findByName(String name, HttpServletResponse httpServletResponse){
       Employee employee=employeeRepository.findByName(name);
       Map<String, String> resp = new HashMap<String, String>();
       if(employee!=null){
           httpServletResponse.setStatus(200);
           resp.put("username", employee.getName());
           resp.put("authtoken", employee.getAuthToken());
       }
       else{
           httpServletResponse.setStatus(404);
           resp.put("error", "no such employee exists");
       }
       return resp;
    }

    public ArrayList<Map<String,String>> findAllEmployees(HttpServletResponse httpServletResponse) {
        ArrayList<Employee> employees = employeeRepository.findAll();
        ArrayList<Map<String,String>> resp = new ArrayList<Map<String,String>>();
        Map<String,String> temp=new HashMap<String,String>();
        if(employees != null){
            httpServletResponse.setStatus(200);
            for (Employee employee:employees) {
                temp.put("username", employee.getName());
                temp.put("authtoken", employee.getAuthToken());
                resp.add(temp);
            }
        }
        else {
            httpServletResponse.setStatus(404);
            temp.put("error", "no employees exists");
            resp.add(temp);
        }
        return resp;
    }

    public void updatePassword(String name, String password, HttpServletResponse response){
        Employee employee=employeeRepository.findByName(name);
        if(employee!=null) {
            if(!password.isEmpty() || password!=null) {
                employee.setPassword(password);
                employeeRepository.save(employee);
                response.setStatus(200);
            }
            else
                response.setStatus(400);

        }
        else
            response.setStatus(404);
    }

    public void deleteEmployee(String name, HttpServletResponse response) throws DataAccessException {
        Employee employee=employeeRepository.findByName(name);
        if(employee!=null){
            employeeRepository.delete(employee.getName());
            response.setStatus(200);
        }
        else
            response.setStatus(404);
    }
}
