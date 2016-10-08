package com.ngproject.api.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ngproject.api.component.service.post.IUserPostService;
import com.ngproject.api.dto.Comment;
import com.ngproject.api.dto.Post;

@RestController
@RequestMapping("/posts")
public class UserPostRestController {

	private IUserPostService userPostService;
	
	@Autowired
	public UserPostRestController(IUserPostService userPostService) {
		this.userPostService = userPostService;
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public Collection<Post> getPosts() {
		return userPostService.getPosts();
	}
	
	@RequestMapping(value = "/{id}/comments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public Collection<Comment> getPostComments(@PathVariable("id") String postId) {
		return userPostService.getPostComments(postId);
	}
}
