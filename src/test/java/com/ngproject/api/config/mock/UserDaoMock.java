package com.ngproject.api.config.mock;

import java.util.Arrays;
import java.util.Collection;

import com.ngproject.api.component.dao.IUserDao;
import com.ngproject.api.dto.Address;
import com.ngproject.api.dto.User;

public class UserDaoMock implements IUserDao {

	@Override
	public Collection<User> getUsers() {
		return Arrays.asList(createUser1Mock(), createUser2Mock());
	}

	@Override
	public User getUser(String userId) {
		User user = null;
		switch (userId) {
			case "1":
				user = createUser1Mock();
				break;
	
			case "2":
				user = createUser2Mock();
				break;
				
			default:
				break;
		}
		return user;
	}

	@Override
	public User createUser(User user) {
		user.setId("100");
		return user;
	}

	@Override
	public boolean updateUser(User user) {
		return true;
	}

	@Override
	public boolean deleteUser(String userId) {
		return true;
	}
	
	private User createUser2Mock() {
		User user2 = new User();
		user2.setId("2");
		user2.setName("Maria C. Amaya O.");
		user2.setUserName("c-amaya");
		user2.setPhone("522-13-8338-54");
		user2.setAddress(createAddressMock());
		return user2;
	}
	
	private User createUser1Mock() {
		User user1 = new User();
		user1.setId("1");
		user1.setName("Daniel Roldan Amaya");
		user1.setUserName("d-amaya");
		user1.setPhone("548-57-8558-52");
		user1.setAddress(createAddressMock());
		return user1;
	}
	
	private Address createAddressMock() {
		Address address = new Address();
		address.setCity("Medellin");
		address.setStreet("Main Street 34");
		address.setSuite("Suite 1");
		address.setZipcode("049958");
		return address;
	}

}
