package com.ios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ios.dto.UserDTO;
import com.ios.exception.EmployeeNotFoundException;
import com.ios.model.Employee;
import com.ios.model.User;
import com.ios.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/post")
	public Employee saveEmployee(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}

	@GetMapping("/{employeeId}")
	public Employee getEmployeeById(@PathVariable("employeeId") long employeeId) throws EmployeeNotFoundException {
		return employeeService.getEmployeeById(employeeId);
	}

	@GetMapping("/get")
	public List<Employee> getAllEmployee() {
		return employeeService.getAllEmpoyee();
	}

	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{employeeId}")
	public void deleteEmployeeById(@PathVariable("employeeId") long employeeId) throws EmployeeNotFoundException {
		employeeService.deleteEmployeeById(employeeId);
	}

	@PutMapping("/{employeeId}")
	public Employee updateEmployee(@PathVariable("employeeId") long employeeId, @RequestBody Employee employee)
			throws EmployeeNotFoundException {
		return employeeService.updateEmployee(employeeId, employee);
	}

//getting the data from other API:

	@GetMapping("/users")
	public List<User> getUserData() {
		return employeeService.getUserData();
	}

//getting users whose status is active
	
	@GetMapping("/active-users")
	public List<UserDTO> getActiveUsers() {
		return employeeService.getActiveUsers();
	}

}
