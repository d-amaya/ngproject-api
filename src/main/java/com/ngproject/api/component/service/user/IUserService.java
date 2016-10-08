package com.ngproject.api.component.service.user;

import java.util.Collection;

import com.ngproject.api.dto.User;

public interface IUserService {

	public Collection<User> getUsers();
	public User getUser(String userId);
	public User createUser(User user);
	public boolean updateUser(User user);
	public boolean deleteUser(String userId);
}
