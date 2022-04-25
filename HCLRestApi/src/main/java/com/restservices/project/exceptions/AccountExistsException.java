package com.restservices.project.exceptions;

public class AccountExistsException extends RuntimeException {

	public AccountExistsException(String message) {
		super(message);
	}
}