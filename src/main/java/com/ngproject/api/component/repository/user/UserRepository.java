package com.ngproject.api.component.repository.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ngproject.api.component.dao.IUserDao;
import com.ngproject.api.dto.User;

@Repository
public class UserRepository implements IUserRepository {
	
	private IUserDao userDao;
	
	@Autowired
	public UserRepository(IUserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public Collection<User> getUsers() {
		return userDao.getUsers();
	}

	@Override
	public User getUser(String userId) {
		return userDao.getUser(userId);
	}

	@Override
	public User createUser(User user) {
		return userDao.createUser(user);
	}

	@Override
	public boolean updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public boolean deleteUser(String userId) {
		return userDao.deleteUser(userId);
	}

}
