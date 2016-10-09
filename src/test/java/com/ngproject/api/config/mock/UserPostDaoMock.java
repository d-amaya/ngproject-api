package com.ngproject.api.config.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.ngproject.api.component.dao.IUserPostDao;
import com.ngproject.api.dto.Comment;
import com.ngproject.api.dto.Post;

public class UserPostDaoMock implements IUserPostDao {

	@Override
	public Collection<Post> getPosts() {
		return Arrays.asList(createUser1Post1Mock(), createUser1Post2Mock());
	}

	@Override
	public Collection<Post> getUserPosts(String userId) {
		return Arrays.asList(createUser1Post1Mock(), createUser1Post2Mock());
	}

	@Override
	public Collection<Comment> getPostComments(String postId) {
		Collection<Comment> comments = null;
		switch (postId) {
			case "1":
				comments = Arrays.asList(createPost1Comment1Mock(), createPost1Comment2Mock());
				break;
			case "2":
				comments = Arrays.asList(createPost2Comment1Mock(), createPost2Comment2Mock());
				break;
			default:
				comments = new ArrayList<Comment>();
				break;
		}
		return comments;
	}
	
	private Post createUser1Post1Mock() {
		Post post = new Post();
		post.setId("1");
		post.setUserId("1");
		post.setTitle("Title Post Number 1");
		post.setBody("Body Post Number 1");
		return post;
	}
	
	private Post createUser1Post2Mock() {
		Post post = new Post();
		post.setId("2");
		post.setUserId("1");
		post.setTitle("Title Post Number 2");
		post.setBody("Body Post Number 2");
		return post;
	}
	
	private Comment createPost1Comment1Mock() {
		Comment comment = new Comment();
		comment.setId("1");
		comment.setPostId("1");
		comment.setName("Name Post 1 Comment 1");
		comment.setBody("Body Post 1 Comment 1");
		return comment;
	}
	
	private Comment createPost1Comment2Mock() {
		Comment comment = new Comment();
		comment.setId("2");
		comment.setPostId("1");
		comment.setName("Name Post 1 Comment 2");
		comment.setBody("Body Post 1 Comment 2");
		return comment;
	}
	
	private Comment createPost2Comment1Mock() {
		Comment comment = new Comment();
		comment.setId("3");
		comment.setPostId("2");
		comment.setName("Name Post 2 Comment 1");
		comment.setBody("Body Post 2 Comment 1");
		return comment;
	}
	
	private Comment createPost2Comment2Mock() {
		Comment comment = new Comment();
		comment.setId("4");
		comment.setPostId("2");
		comment.setName("Name Post 2 Comment 2");
		comment.setBody("Body Post 2 Comment 2");
		return comment;
	}

}
