package com.ngproject.api.controller.exception.handler;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ngproject.api.controller.exception.BadRequestException;
import com.ngproject.api.controller.exception.NotFoundException;
import com.ngproject.api.dto.ErrorMessageDto;

@ControllerAdvice
public class AdviceController {

	private static final Logger LOGGER = Logger.getLogger(AdviceController.class.getSimpleName());
	
	@ExceptionHandler(value = NotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorMessageDto handleNotFound(NotFoundException notFoundException) {
		return new ErrorMessageDto(HttpStatus.NOT_FOUND.value(), notFoundException.getMessage());
	}
	
	@ExceptionHandler(value = BadRequestException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorMessageDto handleBadRequest(BadRequestException badRequestException) {
		return new ErrorMessageDto(HttpStatus.BAD_REQUEST.value(), badRequestException.getMessage());
	}
	
	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorMessageDto handleInternalServerErrorException(Exception exception) {
		LOGGER.log(Level.SEVERE, exception.getMessage(), exception);
		return new ErrorMessageDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error ocurred while processing the request. Please try later.");
	}
}
