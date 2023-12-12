package com.ios.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ios.dto.UserDTO;
import com.ios.model.Posts;
import com.ios.model.User;
import com.ios.service.PostsService;
import com.ios.service.UserService;

@RestController
@RequestMapping("/users")

public class UserController {

	@Autowired
	private PostsService postsService;
	@Autowired
	private UserService userService;

	// getting All Users:
	@GetMapping()
	public List<User> getUserData() {
		return userService.getUserData();
	}

	// getting users by status is active or inactive

	@GetMapping("/{status}")
	public List<UserDTO> getUsersByStatus(@PathVariable("status") String status) {
		return userService.getUsersByStatus(status);
	}

	// group the users in the list whose status is active or inactive
	@GetMapping("/groupBy")
	public Map<String, List<User>> getGroupOfUsers() {
		return userService.getGroupOfUsers();
	}

	// group the users names only in the list whose status is active or inactive
	@GetMapping("/groupByName")
	public Map<String, List<String>> getGroupOfUsersName() {
		return userService.getGroupOfUsersName();
	}

	@GetMapping("/get-users")
	public List<User> gettUsers() {
		return userService.getUsers();
	}

	@GetMapping("/get-posts")
	public List<Posts> getAllPosts() {
		return postsService.getPosts();
	}
}
