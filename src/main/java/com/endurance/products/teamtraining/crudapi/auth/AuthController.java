package com.endurance.products.teamtraining.crudapi.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
/**
 * Created by hemantv on 30/6/17.
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/")
public class AuthController {

    //TODO add logging/auditing
    //TODO add aspect for checking if the user is logedin

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void createEmployee(@RequestBody Employee employee, HttpServletResponse response){
        authService.createEmployeeService(employee, response);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void loginEmployee(@RequestBody String user_name, @RequestBody String password, HttpServletResponse response){
        authService.loginEmployeeService(user_name, password, response);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.PUT)
    public void logoutEmployee(@RequestBody String employee_name, HttpServletResponse response){
        authService.logoutEmployeeService(employee_name, response);
    }

}
