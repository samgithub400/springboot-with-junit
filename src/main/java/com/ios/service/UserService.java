package com.ios.service;

import com.ios.dto.UserDTO;
import com.ios.model.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

	@Autowired
	RestTemplate restTemplate;

		ModelMapper mapper=new ModelMapper();

	//ObjectMapper mapper=new ObjectMapper();

	public List<User> getUserData() {

		List<Object> objectList = getAllUsers();

		System.out.println("**********************************************");
		System.out.println(objectList);

		System.out.println("Converting...............................................");

		List<User> users = convertObjectToUser(objectList);

		return users;
	}

	public List<UserDTO> getUsersByStatus(String status) {

		List<Object> objectList = getAllUsers();

		List<UserDTO> userDtoList = convertObjectToUserDTO(objectList);

		log.info("UserDTOList********" + userDtoList);

		return userDtoList.stream().filter(user -> user.getStatus().equals(status)).collect(Collectors.toList());
	}

	private List<UserDTO> convertObjectToUserDTO(List<Object> objectList) {

		log.info("Converting ObjedctList in to UserDTO list....");

		List<UserDTO> userDtoList = objectList.stream().map(object -> mapper.map(object, UserDTO.class))
				.collect(Collectors.toList());


		//objectList.stream().map(obj->mapper.c)

		log.info("Converted ObjedctList in to UserDTO list.... {}", userDtoList);
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

		List forObject = restTemplate.getForObject(url, List.class);
		System.out.println("*********************************************************************");
		System.out.println(forObject);

		System.out.println("*********************************************************************");

		return forObject;
	}

	public Map<String, List<User>> getGroupOfUsers() {

		List<User> userList = getUserData();

		Map<String, List<User>> users = userList.stream().collect(Collectors.groupingBy(User::getStatus));

		log.info("**** USERS *** {}", users);
		return users;
	}

	public Map<String, List<String>> getGroupOfUsersName() {

		List<User> userList = getUserData();

		return userList.stream().collect(
				Collectors.groupingBy(User::getStatus, Collectors.mapping(User::getName, Collectors.toList())));
	}

	public List<User> getUsers() {
		log.info("Getting  Users....");

		return new ArrayList<>(Arrays.asList(new User(501200, "Ak", "ak@gmail.com", "male", "active"),
				new User(501243, "PK", "pk@gmail.com", "male", "active"),
				new User(500254, "Jack", "jack@gmail.com", "male", "inactive"),
				new User(500250, "Manu", "manu@gmail.com", "female", "active"),
				new User(501239, "Anu", "anu@gmail.com", "female", "active")));
	}
}
