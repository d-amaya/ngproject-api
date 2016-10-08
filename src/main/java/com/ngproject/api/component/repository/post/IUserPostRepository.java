package com.ngproject.api.component.repository.post;

import java.util.Collection;

import com.ngproject.api.dto.Comment;
import com.ngproject.api.dto.Post;

public interface IUserPostRepository {
	
	public Collection<Post> getPosts();
	public Collection<Comment> getPostComments(String postId);
}
