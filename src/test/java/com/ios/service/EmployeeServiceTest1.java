package com.ios.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ios.exception.EmployeeNotFoundException;
import com.ios.model.Employee;
import com.ios.repository.EmployeeRepository;
import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


class EmployeeServiceTest1 {


  @Mock
  private EmployeeRepository employeeRepository;

  private EmployeeService employeeService;

  private Employee employee;
  private AutoCloseable autoCloseable;

 /* @BeforeEach  // it will work for without annotations @Mock
  void setUp() {
    employeeRepository = Mockito.mock(EmployeeRepository.class);
    employeeService = new EmployeeService(employeeRepository);
    autoCloseable = MockitoAnnotations.openMocks(this);
    employee = new Employee(123, "firstName", "lastName", "email@gmail.com", 15000, "pune");

  }*/

  @BeforeEach
  void setUp() {  // here we will try to use annotations @Mock
    autoCloseable = MockitoAnnotations.openMocks(this);
    employeeService = new EmployeeService(employeeRepository);
    employee = new Employee(123, "firstName", "lastName", "email@gmail.com", 15000, "pune");

  }

  @AfterEach
  void tearDown() throws Exception {
    autoCloseable.close();
  }

  @Test
  void testSaveEmployee() {

    //Stubbing OR setting the behaviour for employeeRepository....
    when(employeeRepository.save(ArgumentMatchers.any())).thenReturn(employee);

    //calling service layer saveEmployee() method....
    Employee result = employeeService.saveEmployee(employee);

    verify(employeeRepository, times(1)).save(ArgumentMatchers.any());

    //Asserting the results.....
    assertThat(result.getFirstName()).isEqualTo(
        employee.getFirstName());
    assertThat(result.getLastName()).isEqualTo(
        employee.getLastName());

  }

  @Test
  void getEmployeeById() {
  }

  @Test
  @DisplayName("This test case for positive scenario...")
  void testGetAllEmpoyee_PositiveScenario() throws EmployeeNotFoundException {

    List<Employee> employees = Arrays.asList(
        new Employee(123, "AJay", "Ajay", "ajay@gmail.com", 20000, "pune"),
        new Employee(123, "Akash", "Akash", "akash@gmail.com", 15000, "Mumbai")
        );

    //set the behaviour for the repository method i.e. findAll()....
    when(employeeRepository.findAll()).thenReturn(employees);

    //calling actual service method ..
    List<Employee> employeeList = employeeService.getAllEmpoyee();

    //Asserting the results....
    assertEquals(employees.size(),employeeList.size());
    assertEquals(employees.get(0).getFirstName(),employeeList.get(0).getFirstName());

  }
  @Test
  @DisplayName("This test case for Exceptional scenario...")
  void testGetAllEmpoyee_ExceptionalScenario() throws EmployeeNotFoundException {

    List<Employee> empls=new ArrayList<>(); //this is an empty list....

    //set the behaviour for the repository method i.e. findAll()....
    when(employeeRepository.findAll()).thenReturn(empls);

    //Asserting the results....
    EmployeeNotFoundException employeeNotFoundException =
        assertThrows(EmployeeNotFoundException.class, () -> {
          employeeService.getAllEmpoyee();
        });
    assertEquals("Employee List Is Empty",employeeNotFoundException.getMessage());

  }


  @Test
  void deleteEmployeeById() {
  }

  @Test
  void updateEmployee() {
  }

  @Test
    // this test case for void method (return type is void)
  void test_Return_Void_DeleteEmployeeById() {

    long id = 1l;

    //here we are stubbing the deleteById() method of employeeRepository........
    doNothing().when(employeeRepository).deleteById(anyLong());

    //here we are calling actual service method.......
    employeeService.return_Void_DeleteEmployeeById(id);

    //here we are verifying repository method called or not....
    verify(employeeRepository, times(1)).deleteById(anyLong());

  }
}