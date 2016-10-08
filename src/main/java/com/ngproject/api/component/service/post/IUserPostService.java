package com.ngproject.api.component.service.post;

import java.util.Collection;

import com.ngproject.api.dto.Comment;
import com.ngproject.api.dto.Post;

public interface IUserPostService {
	
	public Collection<Post> getPosts();
	public Collection<Comment> getPostComments(String postId);
}
