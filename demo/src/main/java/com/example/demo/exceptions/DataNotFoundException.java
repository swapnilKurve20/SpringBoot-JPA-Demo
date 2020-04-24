package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DataNotFoundException(String arg0) {
		super(arg0);
	}

	public DataNotFoundException(Throwable arg0) {
		super(arg0);
	}

}
