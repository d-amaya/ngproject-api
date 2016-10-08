package com.ngproject.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ngproject.api.component.dao.IUserDao;
import com.ngproject.api.component.dao.IUserPostDao;
import com.ngproject.api.config.mock.UserDaoMock;
import com.ngproject.api.config.mock.UserPostDaoMock;

@Configuration
public class InitializerTestConfiguration {

	@Bean
	public IUserDao userDao() {
		return new UserDaoMock();
	}
	
	@Bean
	public IUserPostDao userPostDao() {
		return new UserPostDaoMock();
	}
}
