package com.endurance.products.teamtraining.crudapi;

import com.endurance.products.teamtraining.crudapi.auth.Employee;
import com.endurance.products.teamtraining.crudapi.crud.EmployeeController;
import com.endurance.products.teamtraining.crudapi.crud.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by hemantv on 3/7/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class, secure = false)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    Employee mockEmployee = new Employee(1, "Batman", "Gotham", "codenamearmagadeon");
    String exampleEmployeeJson = "{\"authtoken\":\"codenamearmagadeon\",\"username\":\"Batman\"}";
    Map<String,String> exampleEmployeeReturn = new HashMap<String, String>();

    public EmployeeControllerTest() {
        exampleEmployeeReturn.put("authtoken", "codenamearmagadeon");
        exampleEmployeeReturn.put("username", "Batman");
    }

    @Test
    public void getEmployeeTest() throws Exception {
        Mockito.when(employeeService.findByName(Mockito.anyString(),Mockito.any())).thenReturn(exampleEmployeeReturn);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/emp/Batman").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse());
        JSONAssert.assertEquals(exampleEmployeeJson, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void updatePasswordTest() throws Exception{
        Mockito.when(employeeService.updatePassword(Mockito.anyString(), Mockito.anyString())).thenReturn(mockEmployee);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/emp/update")
                .accept(MediaType.APPLICATION_JSON).content(exampleEmployeeJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
