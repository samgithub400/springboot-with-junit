package com.ios.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ios.exception.EmployeeNotFoundException;
import com.ios.model.Employee;
import com.ios.service.EmployeeService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(value = EmployeeController.class)
public class EmployeeControllerTest {


  @MockBean
  private EmployeeService employeeService;

  @Autowired
  private MockMvc mockMvc;

  private Employee employee;


  @BeforeEach
  void setUp() {

   /* employee.builder()
        .id(1L)
        .firstName("Akash")
        .lastName("Mane")
        .email("akash@gmail.com")
        .salary(20000)
        .city("Pune")
        .build();*/

    employee=new Employee(1L,"Akash","Mane","akash@gmail.com",20000,"pune");
  }

  @Test
  @DisplayName("This test case is for save emnployee....")
  void test_saveEmployee() throws Exception {

    //setting the behaviour for the save employee method...
    when(employeeService.saveEmployee(any())).thenReturn(employee);

    //converting the employee object into the json object....
    ObjectMapper mapper = new ObjectMapper();
    String jsonObject = mapper.writeValueAsString(employee);


    // creating the post request uri=/employees/save
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/employees/save")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonObject);


    //performing the request...
    ResultActions perform = mockMvc.perform(requestBuilder);
    MvcResult mvcResult = perform.andReturn();


    //getting the results
    MockHttpServletResponse response = mvcResult.getResponse();
    int status = response.getStatus();


    //asserting the results....
    assertEquals(200, status);
  }

  @Test
  @DisplayName("This test is for get all employee......")
  void test_GetAllEmplpoyee() throws Exception {

    List<Employee> employeeList = Arrays.asList(
        new Employee(1L, "Akash", "Mane", "akash@gmail.com", 20000, "pune"),
        new Employee(2L, "Mahesh", "Mane", "mahesh@gmail.com", 30000, "Hyderabad"),
        new Employee(3L, "Ajay", "Kanase", "ajay@gmail.com", 40000, "pune")
    );

    //setting the behaviour for the getall for service layer...
    when(employeeService.getAllEmpoyee()).thenReturn(employeeList);

    //building the get request uri="/employees/"
    MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get(
        "/employees/");

    //performing the get request.....
    ResultActions perform = mockMvc.perform(mockHttpServletRequestBuilder);

    //getting the return response...
    MvcResult mvcResult = perform.andReturn();

    MockHttpServletResponse response = mvcResult.getResponse();
    int status = response.getStatus();


    //verifying  the results where it is calling  the method or not....
    verify(employeeService,times(1)).getAllEmpoyee();

    //asserting the response result...
    assertEquals(200,status);

    String contentAsString = response.getContentAsString();
    assertNotNull(contentAsString);
  }


  @Test
  @DisplayName("This test case is for getting the employee by Id...")
  void test_GetEmployeeById() throws Exception {

    //setting the behaviour for the get employee by id...
    when(employeeService.getEmployeeById(anyLong())).thenReturn(employee);

    //creating the request for the get request uri="/employee/{empid}"
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employees/1");

    //performing the get request.....
    ResultActions perform = mockMvc.perform(requestBuilder);

    MvcResult mvcResult = perform.andReturn();

    MockHttpServletResponse response = mvcResult.getResponse();

    int status = response.getStatus();

    assertEquals(200,status);

    String contentAsString = response.getContentAsString();
    System.out.println(contentAsString);

    verify(employeeService,times(1)).getEmployeeById(anyLong());


  }

  @Test
  @DisplayName("This test case is for update employee...")
  void test_UpdateEmployee() throws Exception {

    //setting the behaviour for the update employee....
    when(employeeService.updateEmployee(anyLong(),anyObject())).thenReturn(employee);

    //converting the employee object into the json object....
    ObjectMapper mapper = new ObjectMapper();
    String jsonObject = mapper.writeValueAsString(employee);

    System.out.println(jsonObject+"*******************");

    //creating the request for the uir:"/employees/{empid}"
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/employees/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonObject);

    //performing the request...
    ResultActions perform = mockMvc.perform(requestBuilder);

    MvcResult mvcResult = perform.andReturn();
    MockHttpServletResponse response = mvcResult.getResponse();

    int status = response.getStatus();
   verify(employeeService,times(1)).updateEmployee(anyLong(),anyObject());
    assertEquals(200,status);

  }


  @Test
  @DisplayName("This test case is for delete employee by id....")
  //@Disabled
  void test_DeleteEmployeeById() throws Exception {

    //setting the behaviour for the delete employee by id...
    when(employeeService.deleteEmployeeById(anyLong())).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));

    //creating the requedst for the uri: /emploees/{empid}
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/employees/1");

    //perform the request...
    ResultActions perform = mockMvc.perform(requestBuilder);

    MvcResult mvcResult = perform.andReturn();

    //getting the response status....
    MockHttpServletResponse response = mvcResult.getResponse();
    int status = response.getStatus();

    //verify how many times service method is called....
    verify(employeeService,times(1)).deleteEmployeeById(anyLong());

    //asserting the results...
    assertEquals(204,status);
  }

  @Test
  @DisplayName("This test case is for return_Void_DeleteEmployeeById()")
  //@Disabled
  void test_Return_Void_DeleteEmployeeById() throws Exception {
    //setting the behaviour for the delete employee by id...
    doNothing().when(employeeService).return_Void_DeleteEmployeeById(anyLong());

    //creating the requedst for the uri: /emploees/{empid}
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/employees/id/1");

    //perform the request...
    ResultActions perform = mockMvc.perform(requestBuilder);

    MvcResult mvcResult = perform.andReturn();

    //getting the response status....
    MockHttpServletResponse response = mvcResult.getResponse();
    int status = response.getStatus();

    //verify how many times service method is called....
    verify(employeeService,times(1)).return_Void_DeleteEmployeeById(anyLong());

    //asserting the results...
    assertEquals(200,status);

  }  @AfterEach
  void tearDown() {

  }
}
