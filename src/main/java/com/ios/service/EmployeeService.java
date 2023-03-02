package com.ios.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ios.dto.UserDTO;
import com.ios.exception.EmployeeNotFoundException;
import com.ios.model.Employee;
import com.ios.model.User;
import com.ios.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ModelMapper mapper;

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

	public List<User> getUserData() {

		List<Object> userList = getAllUsersFromOtherApi();

		List<User> userDTOList = convertObjectToUser(userList);

		return userDTOList;
	}

	public List<UserDTO> getActiveUsers() {

		List<Object> objectList = getAllUsersFromOtherApi();
		
		List<UserDTO> userDtoList= convertObjectToUserDTO(objectList);
		
		System.out.println("UserDTOList********"+userDtoList);
		
		return userDtoList.stream()
				.filter(user->user.getStatus().equals("active")).collect(Collectors.toList());
	}

	private List<UserDTO> convertObjectToUserDTO(List<Object> objectList) {
		
		System.out.println("Converting ObjedctList in to UserDTO list....");

		 List<UserDTO> userDtoList= objectList.stream()
		.map(object->mapper.map(object, UserDTO.class))
		.collect(Collectors.toList());		
		 
		 System.out.println("Converted ObjedctList in to UserDTO list...." + userDtoList);
		 return userDtoList;
	}
	
	private List<User> convertObjectToUser(List<Object> objectList) {
		
		System.out.println("Converting ObjedctList in to User list....");

		List<User> userList = objectList.stream().map((user) -> mapper.map(user, User.class))
				.collect(Collectors.toList());

		System.out.println("Converted ObjedctList in to User list...." + userList);

		return userList;
	}

//calling other api using RestTemplate....

	private List<Object> getAllUsersFromOtherApi() {

		String url = "https://gorest.co.in/public/v2/users";

		
		/*
		 * HttpHeaders headers = new HttpHeaders();
		 * 
		 * headers.setContentType(MediaType.APPLICATION_JSON); HttpEntity<Object>
		 * requestEntity =new HttpEntity<>(headers);
		 * 
		 * 
		 * List<Object> userList = (List<Object>) restTemplate.exchange(url,
		 * HttpMethod.GET, requestEntity,Object.class); return userList;
		 */
		  
		return (List<Object>) restTemplate.getForObject(url, Object.class);
	}
}
