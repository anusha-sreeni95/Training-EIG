package com.endurance.products.teamtraining.crudapi.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@EnableAutoConfiguration
@RequestMapping("")
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Map<String,String> createEmployee(@RequestBody Employee employee, HttpServletResponse response){
        return authService.createEmployeeService(employee, response);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String,String> loginEmployee(@RequestBody Map<String, String> body, HttpServletResponse response){
        String user_name = body.get("username");
        String password = body.get("password");
        return authService.loginEmployeeService(user_name, password, response);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.PUT)
    public Map<String, String> logoutEmployee(@RequestBody Map<String, String> body, HttpServletResponse response){
        return authService.logoutEmployeeService(body.get("name"), response);
    }

}
