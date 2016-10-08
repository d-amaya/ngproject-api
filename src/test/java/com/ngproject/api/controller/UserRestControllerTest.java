package com.ngproject.api.controller;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ngproject.api.dto.Address;
import com.ngproject.api.dto.ErrorMessageDto;
import com.ngproject.api.dto.User;
import com.ngproject.api.util.JsonConverter;

public class UserRestControllerTest extends ControllerTest<UserRestController> {

	@Autowired
	private UserRestController userRestController;
	
	@Before
	public void setUp() {
		testSetUp(userRestController);
	}
	
	@Test
	public void getUsers() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/users").accept(MediaType.APPLICATION_JSON_UTF8))
									 .andExpect(MockMvcResultMatchers.status().isOk())
									 .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
									 .andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		Collection<User> users = JsonConverter.deserialize(new TypeReference<Collection<User>>() {}, response.getContentAsString());
		
		Assert.assertNotNull(users);
		Assert.assertTrue(users.size() > 0);
	}
	
	@Test
	public void getUserById() throws Exception {
		String userId = "1";
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users/" + userId).accept(MediaType.APPLICATION_JSON_UTF8))
								  .andExpect(MockMvcResultMatchers.status().isOk())
								  .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
								  .andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		User user = JsonConverter.deserialize(new TypeReference<User>() {}, response.getContentAsString());
		
		Assert.assertNotNull(user);
		Assert.assertEquals(userId, user.getId());
		Assert.assertNotNull(userId, user.getAddress());
		Assert.assertFalse(user.getAddress().getCity().isEmpty());
	}
	
	@Test
	public void notFoundWhileGettingUser() throws Exception {
		String userId = "userIdDoesNotExist";
		MvcResult mvcResult = mockMvc.perform(
										MockMvcRequestBuilders.get("/users/" + userId)
		 									.accept(MediaType.APPLICATION_JSON_UTF8)
								 	 )
									 .andExpect(MockMvcResultMatchers.status().isNotFound())
									 .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
									 .andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		ErrorMessageDto errorMessageDto = JsonConverter.deserialize(new TypeReference<ErrorMessageDto>() {}, response.getContentAsString());
		
		Assert.assertNotNull(errorMessageDto);
		Assert.assertEquals(HttpStatus.NOT_FOUND.value(), errorMessageDto.getCode());
		Assert.assertEquals("The user does not exist.", errorMessageDto.getMessage());
	}
	
	@Test
	public void createUser() throws Exception {
		String jsonUser = JsonConverter.serialize(createUserMock(""));
		MvcResult mvcResult = mockMvc.perform(
										MockMvcRequestBuilders.post("/users")
		 									.content(jsonUser)
		 									.accept(MediaType.APPLICATION_JSON_UTF8)
		 									.contentType(MediaType.APPLICATION_JSON_UTF8)
								 	 )
									 .andExpect(MockMvcResultMatchers.status().isCreated())
									 .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
									 .andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		User user = JsonConverter.deserialize(new TypeReference<User>() {}, response.getContentAsString());
		
		Assert.assertNotNull(user);
		Assert.assertFalse(user.getId().isEmpty());
	}
	
	@Test
	public void badRequestWhileCreatingUser() throws Exception {
		String jsonUser = JsonConverter.serialize(createUserMock("1"));
		MvcResult mvcResult = mockMvc.perform(
										MockMvcRequestBuilders.post("/users")
		 									.content(jsonUser)
		 									.accept(MediaType.APPLICATION_JSON_UTF8)
		 									.contentType(MediaType.APPLICATION_JSON_UTF8)
								 	 )
									 .andExpect(MockMvcResultMatchers.status().isBadRequest())
									 .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
									 .andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		ErrorMessageDto errorMessageDto = JsonConverter.deserialize(new TypeReference<ErrorMessageDto>() {}, response.getContentAsString());
		
		Assert.assertNotNull(errorMessageDto);
		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), errorMessageDto.getCode());
		Assert.assertEquals("The user cannot contain an id. Use the PUT method to update the user.", errorMessageDto.getMessage());
	}
	
	@Test
	public void updateUser() throws Exception {
		String userId = "1";
		String jsonUser = JsonConverter.serialize(createUserMock(userId));
		mockMvc.perform(
					MockMvcRequestBuilders.put("/users/" + userId)
						.content(jsonUser)
						.contentType(MediaType.APPLICATION_JSON_UTF8)
			   )
			   .andExpect(MockMvcResultMatchers.status().isOk())
			   .andReturn();
	}
	
	@Test
	public void notFoundWhileUpdatingUser() throws Exception {
		String userId = "userIdDoesNotExist";
		String jsonUser = JsonConverter.serialize(createUserMock(userId));
		MvcResult mvcResult = mockMvc.perform(
										MockMvcRequestBuilders.put("/users/" + userId)
											.content(jsonUser)
		 									.accept(MediaType.APPLICATION_JSON_UTF8)
		 									.contentType(MediaType.APPLICATION_JSON_UTF8)
								 	 )
									 .andExpect(MockMvcResultMatchers.status().isNotFound())
									 .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
									 .andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		ErrorMessageDto errorMessageDto = JsonConverter.deserialize(new TypeReference<ErrorMessageDto>() {}, response.getContentAsString());
		
		Assert.assertNotNull(errorMessageDto);
		Assert.assertEquals(HttpStatus.NOT_FOUND.value(), errorMessageDto.getCode());
		Assert.assertEquals("The user does not exist.", errorMessageDto.getMessage());
	}
	
	@Test
	public void deleteUser() throws Exception {
		String userId = "1";
		mockMvc.perform(MockMvcRequestBuilders.delete("/users/" + userId).contentType(MediaType.APPLICATION_JSON_UTF8))
			   .andExpect(MockMvcResultMatchers.status().isOk())
			   .andReturn();
	}
	
	@Test
	public void notFoundWhileDeletingUser() throws Exception {
		String userId = "userIdDoesNotExist";
		MvcResult mvcResult = mockMvc.perform(
										MockMvcRequestBuilders.delete("/users/" + userId)
		 									.accept(MediaType.APPLICATION_JSON_UTF8)
								 	 )
									 .andExpect(MockMvcResultMatchers.status().isNotFound())
									 .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
									 .andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		ErrorMessageDto errorMessageDto = JsonConverter.deserialize(new TypeReference<ErrorMessageDto>() {}, response.getContentAsString());
		
		Assert.assertNotNull(errorMessageDto);
		Assert.assertEquals(HttpStatus.NOT_FOUND.value(), errorMessageDto.getCode());
		Assert.assertEquals("The user does not exist.", errorMessageDto.getMessage());
	}
	
	private User createUserMock(String id) {
		User user = new User();
		user.setId(id);
		user.setEmail("user@gmail.com");
		user.setName("Daniel Roldan Amaya");
		user.setUserName("d-amaya");
		user.setPhone("321-5558-457-4");
		user.setAddress(new Address());
		user.getAddress().setCity("Medellin");
		user.getAddress().setStreet("Main Street 32 # 54");
		user.getAddress().setZipcode("049598");
		user.getAddress().setSuite("Suite 23");
		return user;
	}
}
