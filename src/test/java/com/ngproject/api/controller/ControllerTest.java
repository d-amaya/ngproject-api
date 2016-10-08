package com.ngproject.api.controller;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ngproject.api.Application;
import com.ngproject.api.config.InitializerTestConfiguration;
import com.ngproject.api.controller.exception.handler.AdviceController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Application.class, InitializerTestConfiguration.class })
public abstract class ControllerTest<T> {

	protected MockMvc mockMvc;
	
	protected void testSetUp(T controller) {
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new AdviceController()).build();
	}
	
}
