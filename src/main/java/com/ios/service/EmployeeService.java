package com.ios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ios.exception.EmployeeNotFoundException;
import com.ios.model.Employee;
import com.ios.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee saveEmployee(Employee employee) {

		return employeeRepository.save(employee);
	}

	public Employee getEmployeeById(long employeeId) throws EmployeeNotFoundException {
		return findById(employeeId);
	}

	public List<Employee> getAllEmpoyee() {
		return employeeRepository.findAll();
	}

	public ResponseEntity<HttpStatus> deleteEmployeeById(long employeeId) throws EmployeeNotFoundException {
		Employee employee = findById(employeeId);
		employeeRepository.delete(employee);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	private Employee findById(long employeeId) throws EmployeeNotFoundException {
		return employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException("NOT FOUND..!"));

	}

	public Employee updateEmployee(long employeeId, Employee employee) throws EmployeeNotFoundException {
		Employee foundEmployee = findById(employeeId);

		foundEmployee.setFirstName(employee.getFirstName());
		foundEmployee.setLastName(employee.getLastName());
		foundEmployee.setEmail(employee.getEmail());
		foundEmployee.setCity(employee.getCity());
		foundEmployee.setSalary(employee.getSalary());

		return employeeRepository.save(foundEmployee);
	}	
}
