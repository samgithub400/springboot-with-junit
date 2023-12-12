package com.ios.service;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ios.exception.EmployeeNotFoundException;
import com.ios.model.Employee;
import com.ios.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository=employeeRepository;

	}
	public Employee saveEmployee(Employee employee) {

		return employeeRepository.save(employee);
	}

	public Employee getEmployeeById(long employeeId) throws EmployeeNotFoundException {
		return findById(employeeId);
	}

	public List<Employee> getAllEmpoyee() throws EmployeeNotFoundException {

		List<Employee> employeeList = employeeRepository.findAll();
		if(employeeList.isEmpty())
			throw new EmployeeNotFoundException("Employee List Is Empty");
		return employeeList;
	}

	public ResponseEntity<HttpStatus> deleteEmployeeById(long employeeId) throws EmployeeNotFoundException {
		Employee employee = findById(employeeId);
		employeeRepository.delete(employee);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	private Employee findById(long employeeId) throws EmployeeNotFoundException {
		//return employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException("NOT FOUND..!"));

		Optional<Employee> byId = employeeRepository.findById(employeeId);
		if(byId.isPresent())
			return byId.get();
		else throw new EmployeeNotFoundException("NOT FOUND..!");
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

	public void return_Void_DeleteEmployeeById(Long id) {
		//Deleting the Employee By Id....deleteEmployeeById=deleteEmployeeById
		employeeRepository.deleteById(id);
	}
}
