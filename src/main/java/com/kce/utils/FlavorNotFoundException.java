package com.kce.utils;

public class FlavorNotFoundException extends RuntimeException {

	private String message;

	public FlavorNotFoundException(String message) {
		super(message);
	}

	@Override
	public String toString() {
		return "Flavour not found";
	}

}
