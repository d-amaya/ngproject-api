package com.ngproject.api.component.repository.post;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ngproject.api.component.dao.IUserPostDao;
import com.ngproject.api.dto.Comment;
import com.ngproject.api.dto.Post;

@Repository
public class UserPostRepository implements IUserPostRepository {

	private IUserPostDao userPostDao;
	
	@Autowired
	public UserPostRepository(IUserPostDao userPostDao) {
		this.userPostDao = userPostDao;
	}
	
	@Override
	public Collection<Post> getPosts() {
		return userPostDao.getPosts();
	}

	@Override
	public Collection<Post> getUserPosts(String userId) {
		return userPostDao.getUserPosts(userId);
	}

	@Override
	public Collection<Comment> getPostComments(String postId) {
		return userPostDao.getPostComments(postId);
	}

}
