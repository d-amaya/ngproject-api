package com.ngproject.api.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ngproject.api.component.service.user.IUserService;
import com.ngproject.api.controller.exception.BadRequestException;
import com.ngproject.api.controller.exception.NotFoundException;
import com.ngproject.api.dto.User;

@RestController
@RequestMapping("/users")
public class UserRestController {

	private IUserService userService;
	
	@Autowired
	public UserRestController(IUserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public Collection<User> getUsers() {
		return userService.getUsers();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public User getUser(@PathVariable String id) {
		User user = userService.getUser(id);
		if (user == null) {
			throw new NotFoundException("The user does not exist.");
		}
		return user;
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public User createUser(@RequestBody User user) {
		validateUserCreation(user);
		return userService.createUser(user);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public String updateUser(@PathVariable String id, @RequestBody User user) {
		validateUserUpdate(user);
		User currentUser = userService.getUser(id);
		if (currentUser == null) {
			throw new NotFoundException("The user does not exist.");
		}
		currentUser.setName(user.getName());
		currentUser.setUserName(user.getUserName());
		currentUser.setEmail(user.getEmail());
		currentUser.setPhone(user.getPhone());
		currentUser.setAddress(user.getAddress());
		
		boolean success = userService.updateUser(user);
		return "{\"success\": \""+ success + "\"}";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public String deleteUser(@PathVariable String id) {
		User currentUser = userService.getUser(id);
		if (currentUser == null) {
			throw new NotFoundException("The user does not exist.");
		}
		boolean success = userService.deleteUser(id);
		return "{\"success\": \""+ success + "\"}";
	}
	
	private void validateUserCreation(User user) {
		if (user.getId() != null && !user.getId().isEmpty()) {
			throw new BadRequestException("The user cannot contain an id. Use the PUT method to update the user.");
		}
	}
	
	private void validateUserUpdate(User user) {
		if (user.getId() == null && user.getId().isEmpty()) {
			throw new BadRequestException("The user cannot contain an id. Use the PUT method to update the user.");
		}
	}
	
}
