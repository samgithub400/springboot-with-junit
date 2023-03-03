package com.ios.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ios.dto.UserDTO;
import com.ios.model.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ModelMapper mapper;

	public List<User> getUserData() {

		List<Object> objectList = getAllUsers();

		List<User> userDTOList = convertObjectToUser(objectList);

		return userDTOList;
	}

	public List<UserDTO> getUsersByStatus(String status) {

		List<Object> objectList = getAllUsers();
		
		List<UserDTO> userDtoList= convertObjectToUserDTO(objectList);
		
		log.info("UserDTOList********"+userDtoList);
		
		return userDtoList.stream()
				.filter(user->user.getStatus().equals(status)).collect(Collectors.toList());
	}

	private List<UserDTO> convertObjectToUserDTO(List<Object> objectList) {
		
		log.info("Converting ObjedctList in to UserDTO list....");

		 List<UserDTO> userDtoList= objectList.stream()
		.map(object->mapper.map(object, UserDTO.class))
		.collect(Collectors.toList());		
		 
		 log.info("Converted ObjedctList in to UserDTO list.... {}" , userDtoList);
		 return userDtoList;
	}
	
	private List<User> convertObjectToUser(List<Object> objectList) {
		
		log.info("Converting ObjedctList in to User list....");

		List<User> userList = objectList.stream().map((user) -> mapper.map(user, User.class))
				.collect(Collectors.toList());

		log.info("Converted ObjedctList in to User list.... {}", userList);

		return userList;
	}

//getting all the users
	private List<Object> getAllUsers() {

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

	public Map<String, List<User>> getGroupOfUsers() {
		
		List<User> userList = getUserData();
		
	Map<String, List<User>> users=	userList.stream()
		.collect(Collectors.groupingBy(User::getStatus));
		
	log.info("**** USERS *** {}",users);
		return users;
	}

	public Map<String, List<String>> getGroupOfUsersName() {
		
		List<User> userList = getUserData();
		
		return userList.stream()
		.collect(
				Collectors
				.groupingBy(User::getStatus
						,Collectors.mapping(User::getName 
								,Collectors.toList())));
	}
}
