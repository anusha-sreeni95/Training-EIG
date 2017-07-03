package com.endurance.products.teamtraining.crudapi;

import com.endurance.products.teamtraining.crudapi.auth.Employee;
import com.endurance.products.teamtraining.crudapi.crud.EmployeeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by anusha on 2/7/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {
    HttpServletResponse response;

    @MockBean
    private EmployeeService obj;
    @Test
    public void findByNameTest(){
        Map<String,String> result=obj.findByName("endurance",response);
        Map<String,String> expected_result = new HashMap<String,String>();
        expected_result.put("username","endurance");
        expected_result.put("authtoken","jsafdiuewfkj");
        System.out.println(result.get("username")+"----"+expected_result.get("username"));
        Assert.assertEquals(result.get("username"),expected_result.get("username"));
    }
}
