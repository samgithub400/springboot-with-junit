package com.ios.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ios.exception.EmployeeNotFoundException;
import com.ios.model.Employee;
import com.ios.repository.EmployeeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTests {

  @InjectMocks
  private EmployeeService employeeService;

  @Mock
  private EmployeeRepository employeeRepository;

  private Employee employee;

  // @Rule
  // public ExpectedException exceptionRule = ExpectedException.none();

  @BeforeEach
  public void setUp() {
    //MockitoAnnotations.openMocks(this);
    // if you are annotating this class as a @ExtendWith(MockitoExtension.class) then you don't need to
    //write this stmt  'MockitoAnnotations.openMocks(this);'

    employee = Employee.builder()
        .id(1L)
        .firstName("Akshat")
        .lastName("Gupta")
        .email("akshat@gmail.com")
        .salary(70000)
        .city("Mumbai")
        .build();
  }

  // @DisplayName("Junit test for saveEmployee method")


  //test saveEmployee
  @Test
  void testSaveEmployee() {
    // given(employeeRepository.save(employee)).willReturn(employee);

    Mockito.when(employeeRepository.save(employee)).thenReturn(employee);

    Employee savedEmployee = employeeService.saveEmployee(employee);

    assertThat(savedEmployee).isNotNull();
  }

  //test getAllEmployee
  @Test
  void testGetAllEmpoyee() throws EmployeeNotFoundException {

    List<Employee> employeeData = new ArrayList<>();
    employeeData.add(new Employee(1, "Akash", "Pondkule", "akash@gmail.com", 12000, "Pune"));
    employeeData.add(new Employee(15, "Ajay", "Kale", "Ajay@gmail.com", 20000, "Pune"));
    employeeData.add(new Employee(19, "Aman", "Shaikh", "Aman@gmail.com", 25000, "Pune"));

    Mockito.when(employeeRepository.findAll()).thenReturn(employeeData);

    List<Employee> employeesList = employeeService.getAllEmpoyee();

    assertThat(employeesList).hasSize(3);
  }


  // test getEmployeeById when exception is thrown
  @Test
  void testGetEmployeeById_WhenExceptionThrown() {

    Exception exception = assertThrows(EmployeeNotFoundException.class, () ->
        employeeService.getEmployeeById(employee.getId()));

    String expectedMessage = "NOT FOUND..!";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  //test getEmployeeById
  @Test
  @DisplayName("This is for find Employee By id....")
  void testGetEmployeeById() throws EmployeeNotFoundException {

    Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.ofNullable(employee));
    Employee savedEmployee = employeeService.getEmployeeById(employee.getId());
    assertThat(savedEmployee).isEqualTo(employee);
  }

  @Disabled
  //EmployeeNotFoundException to be thrown, but nothing was thrown.  (getting this error...)
  @Test
  @DisplayName("This is for if employee not found....")
  void testGetEmployeeById_Exceptional() throws EmployeeNotFoundException {

    //setting the behaviour....
    when(employeeRepository.findById(1L)).thenReturn(Optional.ofNullable(employee));

    //calling the actual service method....
    EmployeeNotFoundException employeeNotFoundException = assertThrows(
        EmployeeNotFoundException.class,
        () -> employeeService.getEmployeeById(employee.getId()), "NOT FOUND..!");

    //asserting the results....
    assertEquals("NOT FOUND..!", employeeNotFoundException.getMessage());
  }

  //test deleteEmployeeById
  @Test
  void testDeleteEmployeeById() throws EmployeeNotFoundException {

    Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.ofNullable(employee));
    ResponseEntity<HttpStatus> actualResult = employeeService.deleteEmployeeById(employee.getId());

    assertThat(actualResult.getStatusCodeValue()).isEqualTo(204);

  }

  //test updateEmployee
  @Test
  void testUpdateEmployee() throws EmployeeNotFoundException {

    Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.ofNullable(employee));
    employee.setFirstName("Amol");
    employee.setLastName("Rao");
    employee.setEmail("rao@gmail.com");

    Mockito.when(employeeRepository.save(employee)).thenReturn(employee);

    Employee actualEmployee = employeeService.updateEmployee(1L, employee);

    assertThat(actualEmployee.getFirstName()).isEqualTo("Amol");
    assertThat(actualEmployee.getLastName()).isEqualTo("Rao");
    assertThat(actualEmployee.getEmail()).isEqualTo("rao@gmail.com");
  }


  @Test
    // this test case for void method (return type is void)
  void test_Return_Void_DeleteEmployeeById() {

    long id = 1L;

    //here we are stubbing the deleteById() method of employeeRepository........
    doNothing().when(employeeRepository).deleteById(anyLong());

    //here we are calling actual service method.......
    employeeService.return_Void_DeleteEmployeeById(id);

    //here we are verifying repository method called or not....
    verify(employeeRepository, times(1)).deleteById(anyLong());

  }

  @Test
    // this test case for void method (return type is void) calling the actual method....
  void test_Return_Void_DeleteEmployeeById_Call_Real_Method() {

    mock(EmployeeRepository.class, Mockito.CALLS_REAL_METHODS);
    //we can also use spy() to mock real repository.......
    //spy(EmployeeRepository.class);

    //Setting the behaviour for real repository call....
    doAnswer(Answers.CALLS_REAL_METHODS).when(employeeRepository).deleteById(anyLong());

    //we can also use doNothing for void method for setting the behaviour....
    //doNothing().when(employeeRepository).deleteById(anyLong());

    //verifying the service method...
    //assertThat(employeeService.return_Void_DeleteEmployeeById(1L));

    //here we are calling actual service method.......
    employeeService.return_Void_DeleteEmployeeById(1L);

    //here we are verifying repository method called or not....
    verify(employeeRepository, times(1)).deleteById(anyLong());

  }


  @Test
  @DisplayName("This test case for Exceptional scenario...")
  void test_GetAllEmpoyee_ExceptionalScenario() {

    List<Employee> empls = new ArrayList<>(); //this is an empty list....

    //set the behaviour for the repository method i.e. findAll()....
    when(employeeRepository.findAll()).thenReturn(empls);

    //Asserting the results....
    EmployeeNotFoundException employeeNotFoundException =
        assertThrows(EmployeeNotFoundException.class, () ->
            employeeService.getAllEmpoyee());
    assertEquals("Employee List Is Empty", employeeNotFoundException.getMessage());
  }

}

