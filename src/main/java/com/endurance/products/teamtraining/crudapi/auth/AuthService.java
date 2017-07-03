package com.endurance.products.teamtraining.crudapi.auth;

import com.endurance.products.teamtraining.crudapi.utils.BCrypt;
import com.endurance.products.teamtraining.crudapi.utils.SessionIdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

@Transactional
@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private Environment env;
    @Autowired
    private SessionIdentifierGenerator authTokenGenerator;

    public void createEmployeeService(Employee employee, HttpServletResponse response){
        Employee existing_emp = authRepository.findByName(employee.getName());
        if(existing_emp == null) {
            employee.setPassword(BCrypt.hashpw(employee.getPassword(), env.getProperty("hashWithSalt")));
            authRepository.save(employee);

            response.setStatus(201);
        }
        else
            response.setStatus(409);

    }

    public String loginEmployeeService(String user_name, String passsword, HttpServletResponse response) {
        Employee existing_emp = authRepository.findByName(user_name);
        if(existing_emp == null) {
            response.setStatus(404);
            return null;
        }
        else{
            if(BCrypt.checkpw(passsword, existing_emp.getPassword())){
                existing_emp.setAuthToken(authTokenGenerator.nextSessionId());
                response.setStatus(200);
                existing_emp.setCurrentEmployeeAuth(existing_emp.getAuthToken());
                System.out.println(existing_emp.getCurrentEmployeeAuth());
                return existing_emp.getAuthToken();

            }
            else{
                response.setStatus(401);
                return null;
            }
        }
    }

    public void logoutEmployeeService(String employee_name, HttpServletResponse response){
        Employee existing_emp = authRepository.findByName(employee_name);
        if(existing_emp == null)
            response.setStatus(404);
        else{
            if(existing_emp.getAuthToken()!=null){
                existing_emp.setAuthToken(null);
                authRepository.save(existing_emp);
                response.setStatus(200);
                System.out.println(existing_emp.getCurrentEmployeeAuth());
                existing_emp.clearCurrentEmployeeAuth();
                System.out.println(existing_emp.getCurrentEmployeeAuth());
            }
            else
                response.setStatus(400);
        }
    }
}
