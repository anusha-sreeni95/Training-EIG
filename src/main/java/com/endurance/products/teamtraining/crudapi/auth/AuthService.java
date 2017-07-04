package com.endurance.products.teamtraining.crudapi.auth;

import com.endurance.products.teamtraining.crudapi.utils.BCrypt;
import com.endurance.products.teamtraining.crudapi.utils.SessionIdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Transactional
@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private Environment env;
    @Autowired
    private SessionIdentifierGenerator authTokenGenerator;

    public Map<String, String> createEmployeeService(Employee employee, HttpServletResponse response){
        Employee existing_emp = authRepository.findByName(employee.getName());
        Map<String, String> message = new HashMap<>();
        if(existing_emp == null) {
            if(employee.getPassword().isEmpty() || employee.getPassword() == null) {
                response.setStatus(400);
                message.put("error", "Cannot have empty password field");
            }
            else {
                employee.setPassword(BCrypt.hashpw(employee.getPassword(), env.getProperty("hashWithSalt")));
                authRepository.save(employee);
                response.setStatus(201);
                message.put("message", "Employee created successfully");
            }
        }
        else {
            response.setStatus(409);
            message.put("error","Employee with username already exists");
        }
        return message;
    }

    public Map<String, String> loginEmployeeService(String user_name, String passsword, HttpServletResponse response) {
        Employee existing_emp = authRepository.findByName(user_name);
        Map<String, String> message = new HashMap<>();
        if(existing_emp == null) {
            response.setStatus(404);
            message.put("error", "Employee doesnot exists");
        }
        else{
            if(BCrypt.checkpw(passsword, existing_emp.getPassword())){
                existing_emp.setAuthToken(authTokenGenerator.nextSessionId());
                response.setStatus(200);
                existing_emp.setCurrentEmployeeAuth(existing_emp.getAuthToken());
                System.out.println(existing_emp.getCurrentEmployeeAuth());
                message.put("AuthToken",existing_emp.getAuthToken());
                message.put("messgae","Succcessfuly logged in");
            }
            else{
                response.setStatus(401);
                message.put("error","Password mismatch");
            }
        }
        return message;
    }

    public Map<String, String> logoutEmployeeService(String employee_name, HttpServletResponse response){
        Employee existing_emp = authRepository.findByName(employee_name);
        Map<String,String> message = new HashMap<>();
        if(existing_emp == null) {
            response.setStatus(404);
            message.put("error","Employee doesnot exist");
        }
        else{
            if(existing_emp.getAuthToken()!=null){
                existing_emp.setAuthToken(null);
                authRepository.save(existing_emp);
                response.setStatus(200);
                existing_emp.clearCurrentEmployeeAuth();
                message.put("message", "Employee successfully logged out");
            }
            else {
                response.setStatus(400);
                message.put("error","Employee not logged in");
            }
        }
        return message;
    }
}
