package com.ngproject.api.util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {

	private static final Logger LOGGER = Logger.getLogger(JsonConverter.class.getSimpleName());
	
	public static <E> E deserialize(TypeReference<E> typeReference, String json) {
		E object = null; 
		if (json != null && !json.isEmpty()) {
			try {
				object = new ObjectMapper().readValue(json, typeReference);
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, "There was an error converting from json.", e);
			}
		}
		return object;
	}
	
	public static String serialize(Object object) {
		try {
			if (object != null) {
				return new ObjectMapper().writeValueAsString(object);
			}
		} catch (JsonProcessingException e) {
			LOGGER.log(Level.SEVERE, "There was an error converting to json.", e);
		}
		return null;
	}
}
