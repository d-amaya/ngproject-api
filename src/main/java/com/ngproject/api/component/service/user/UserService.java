package com.ngproject.api.component.service.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ngproject.api.component.repository.user.IUserRepository;
import com.ngproject.api.dto.User;

@Service
public class UserService implements IUserService {

	private IUserRepository userRepository;
	
	@Autowired
	public UserService(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public Collection<User> getUsers() {
		return userRepository.getUsers();
	}

	@Override
	public User getUser(String userId) {
		return userRepository.getUser(userId);
	}

	@Override
	public User createUser(User user) {
		return userRepository.createUser(user);
	}

	@Override
	public boolean updateUser(User user) {
		return userRepository.updateUser(user);
	}

	@Override
	public boolean deleteUser(String userId) {
		return userRepository.deleteUser(userId);
	}
}
