package com.ngproject.api.component.service.post;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ngproject.api.component.repository.post.IUserPostRepository;
import com.ngproject.api.dto.Comment;
import com.ngproject.api.dto.Post;

@Service
public class UserPostService implements IUserPostService {

	private IUserPostRepository userPostRepository;
	
	@Autowired
	public UserPostService(IUserPostRepository userPostRepository) {
		this.userPostRepository = userPostRepository;
	}
	
	@Override
	public Collection<Post> getPosts() {
		return userPostRepository.getPosts();
	}

	@Override
	public Collection<Post> getUserPosts(String userId) {
		return userPostRepository.getUserPosts(userId);
	}

	@Override
	public Collection<Comment> getPostComments(String postId) {
		return userPostRepository.getPostComments(postId);
	}

}
