package com.endurance.products.teamtraining.crudapi.crud;

import com.endurance.products.teamtraining.crudapi.auth.Employee;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialStruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/emp")
@RestController
@EnableAutoConfiguration
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value="/{name}",method= RequestMethod.GET)
    public Map<String, String> getEmployee(@PathVariable("name") String name, HttpServletResponse httpServletResponse){
        return employeeService.findByName(name,httpServletResponse);
    }

    @RequestMapping(value="/",method=RequestMethod.GET)
    public ArrayList<Map<String,String>> findAllEmployees(HttpServletResponse httpServletResponse){
        return employeeService.findAllEmployees(httpServletResponse);
    }

    @RequestMapping(value="/update",method=RequestMethod.PUT)
    public void updatePassword(@RequestBody Map<String,String> body, HttpServletResponse response){
        String name = body.get("username");
        String password = body.get("password");
        employeeService.updatePassword(name,password, response);
    }

    @RequestMapping(value="/delete/{name}",method= RequestMethod.DELETE)
    public void deleteEmployee(@PathVariable("name") String name, HttpServletResponse response){
        employeeService.deleteEmployee(name, response);
    }

}
