package com.ngproject.api.component.dao.rest.user;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ngproject.api.component.dao.IUserDao;
import com.ngproject.api.component.dao.rest.ClientRestDao;
import com.ngproject.api.dto.User;
import com.ngproject.api.util.JsonConverter;

@Component
public class UserDao extends ClientRestDao implements IUserDao {

	private static final String USER_SERVICE_URL = "https://jsonplaceholder.typicode.com/users";
	
	@Override
	public Collection<User> getUsers() {
		ResponseEntity<String> response = get(USER_SERVICE_URL);
		if (response == null) {
			return new ArrayList<User>();
		}
		return JsonConverter.deserialize(new TypeReference<ArrayList<User>>() {}, response.getBody());
	}

	@Override
	public User getUser(String userId) {
		ResponseEntity<String> response = get(USER_SERVICE_URL + "/" + userId);
		if (response == null) {
			return null;
		}
		return JsonConverter.deserialize(new TypeReference<User>() {}, response.getBody());
	}

	@Override
	public User createUser(User user) {
		String jsonUser = post(USER_SERVICE_URL, JsonConverter.serialize(user)).getBody();
		User userCreated = JsonConverter.deserialize(new TypeReference<User>() {}, jsonUser);
		user.setId(userCreated.getId());
		return user;
	}

	@Override
	public boolean updateUser(User user) {
		ResponseEntity<String> response = put(USER_SERVICE_URL + "/" + user.getId(), JsonConverter.serialize(user));
		if (response != null && HttpStatus.OK.equals(response.getStatusCode())) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteUser(String userId) {
		ResponseEntity<String> response = delete(USER_SERVICE_URL + "/" + userId);
		if (response != null && HttpStatus.OK.equals(response.getStatusCode())) {
			return true;
		}
		return false;
	}
}
