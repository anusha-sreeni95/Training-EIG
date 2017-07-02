package com.endurance.products.teamtraining.crudapi.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by hemantv on 30/6/17.
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("")
public class AuthController {

    @Autowired
    private AuthService authService;



    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void createEmployee(@RequestBody Employee employee, HttpServletResponse response){
        authService.createEmployeeService(employee, response);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginEmployee(@RequestBody Map<String, String> body, HttpServletResponse response){
        String user_name = body.get("username");
        String password = body.get("password");
        return authService.loginEmployeeService(user_name, password, response);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.PUT)
    public void logoutEmployee(@RequestBody Map<String, String> body, HttpServletResponse response){
        authService.logoutEmployeeService(body.get("name"), response);
    }

}
