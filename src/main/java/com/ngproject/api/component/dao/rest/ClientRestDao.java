package com.ngproject.api.component.dao.rest;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public abstract class ClientRestDao {
	
	private HttpEntity<String> getHttpEntity(String body) {
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    headers.add("User-Agent", "ngproject-api v0.1.0");
	    return new HttpEntity<String>(body, headers);
	}
	
	public ResponseEntity<String> get(String url) {
		try {
			return new RestTemplate().exchange(url, HttpMethod.GET, getHttpEntity(null), String.class);
		} catch (HttpClientErrorException e) {
			if (HttpStatus.NOT_FOUND.equals(e.getStatusCode())) {
				return null;
			}
			throw e;
		}
	}
	
	public ResponseEntity<String> post(String url, String body) {
	    return new RestTemplate().exchange(url, HttpMethod.POST, getHttpEntity(body), String.class);
	}
	
	public ResponseEntity<String> put(String url, String body) {
	    return new RestTemplate().exchange(url, HttpMethod.PUT, getHttpEntity(body), String.class);
	}

	public ResponseEntity<String> delete(String url) {
		return new RestTemplate().exchange(url, HttpMethod.DELETE, getHttpEntity(null), String.class);
	}
}
