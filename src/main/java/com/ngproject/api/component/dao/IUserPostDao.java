package com.ngproject.api.component.dao;

import java.util.Collection;

import com.ngproject.api.dto.Comment;
import com.ngproject.api.dto.Post;

public interface IUserPostDao {

	public Collection<Post> getPosts();
	public Collection<Comment> getPostComments(String postId);
}
