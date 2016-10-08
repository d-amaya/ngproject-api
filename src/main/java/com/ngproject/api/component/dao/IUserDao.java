package com.ngproject.api.component.dao;

import java.util.Collection;

import com.ngproject.api.dto.User;

public interface IUserDao {

	public Collection<User> getUsers();
	public User getUser(String userId);
	public User createUser(User user);
	public boolean updateUser(User user);
	boolean deleteUser(String userId);
}
