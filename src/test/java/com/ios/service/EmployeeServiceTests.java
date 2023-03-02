package com.ios.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ios.exception.EmployeeNotFoundException;
import com.ios.model.Employee;
import com.ios.repository.EmployeeRepository;

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
		employee = Employee.builder()
				.id(1l)
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
	void testGetAllEmpoyee() {

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

		Exception exception = assertThrows(EmployeeNotFoundException.class, () -> {
			employeeService.getEmployeeById(employee.getId());
		});

		String expectedMessage = "NOT FOUND..!";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

//test getEmployeeById	
	@Test
	void testGetEmpoyeeById() throws EmployeeNotFoundException {

		Mockito.when(employeeRepository.findById(1l)).thenReturn(Optional.of(employee));
		Employee savedEmployee = employeeService.getEmployeeById(employee.getId());
		assertThat(savedEmployee).isEqualTo(employee);
	}

//test deleteEmployeeById	
	@Test
	void testDeleteEmployeeById() throws EmployeeNotFoundException {

		Mockito.when(employeeRepository.findById(1l)).thenReturn(Optional.of(employee));		
		ResponseEntity<HttpStatus> actualResult=employeeService.deleteEmployeeById(employee.getId());
		
		assertThat(actualResult.getStatusCodeValue()).isEqualTo(204);
		
	}
	
//test updateEmployee
	@Test
	void testUpdateEmployee() throws EmployeeNotFoundException {
		
		Mockito.when(employeeRepository.findById(1l)).thenReturn(Optional.of(employee));
		employee.setFirstName("Amol");
		employee.setLastName("Rao");
		employee.setEmail("rao@gmail.com");

		Mockito.when(employeeRepository.save(employee)).thenReturn(employee);

		Employee actualEmployee= employeeService.updateEmployee(1l, employee);
		assertThat(actualEmployee).isEqualTo(employee);
	}
}
