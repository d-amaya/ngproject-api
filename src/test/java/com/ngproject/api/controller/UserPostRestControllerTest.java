package com.ngproject.api.controller;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ngproject.api.dto.Comment;
import com.ngproject.api.dto.Post;
import com.ngproject.api.util.JsonConverter;

public class UserPostRestControllerTest extends ControllerTest<UserPostRestController> {

	@Autowired
	private UserPostRestController userPostRestController;
	
	@Before
	public void setUp() {
		testSetUp(userPostRestController);
	}
	
	@Test
	public void getPosts() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/posts").accept(MediaType.APPLICATION_JSON_UTF8))
									 .andExpect(MockMvcResultMatchers.status().isOk())
									 .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
									 .andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		Collection<Post> posts = JsonConverter.deserialize(new TypeReference<Collection<Post>>() {}, response.getContentAsString());
		
		Assert.assertNotNull(posts);
		Assert.assertTrue(posts.size() > 0);
	}
	
	@Test
	public void getPostComments() throws Exception {
		String postId = "1";
		MvcResult mvcResult = mockMvc.perform(
										MockMvcRequestBuilders.get("/posts/" + postId + "/comments")
															  .accept(MediaType.APPLICATION_JSON_UTF8)
								  )
								  .andExpect(MockMvcResultMatchers.status().isOk())
								  .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
								  .andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		Collection<Comment> comments = JsonConverter.deserialize(new TypeReference<Collection<Comment>>() {}, response.getContentAsString());
		
		Assert.assertNotNull(comments);
		Assert.assertTrue(comments.size() > 0);
	}
}
