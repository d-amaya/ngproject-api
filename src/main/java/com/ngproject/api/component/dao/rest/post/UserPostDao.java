package com.ngproject.api.component.dao.rest.post;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ngproject.api.component.dao.IUserPostDao;
import com.ngproject.api.component.dao.rest.ClientRestDao;
import com.ngproject.api.dto.Comment;
import com.ngproject.api.dto.Post;
import com.ngproject.api.util.JsonConverter;

@Component
public class UserPostDao extends ClientRestDao implements IUserPostDao {

	private static final String POST_SERVICE_URL = "https://jsonplaceholder.typicode.com/posts";
	private static final String COMMENT_SERVICE_URL = POST_SERVICE_URL + "/%s/comments";
	
	@Override
	public Collection<Post> getPosts() {
		ResponseEntity<String> response = get(POST_SERVICE_URL);
		if (response == null) {
			return new ArrayList<Post>();
		}
		return JsonConverter.deserialize(new TypeReference<ArrayList<Post>>() {}, response.getBody());
	}

	@Override
	public Collection<Post> getUserPosts(String userId) {
		ResponseEntity<String> response = get(POST_SERVICE_URL + "?userId=" + userId);
		if (response == null) {
			return new ArrayList<Post>();
		}
		return JsonConverter.deserialize(new TypeReference<ArrayList<Post>>() {}, response.getBody());
	}

	@Override
	public Collection<Comment> getPostComments(String postId) {
		ResponseEntity<String> response = get(String.format(COMMENT_SERVICE_URL, postId));
		if (response == null) {
			return new ArrayList<Comment>();
		}
		return JsonConverter.deserialize(new TypeReference<Collection<Comment>>() {}, response.getBody());
	}
}
