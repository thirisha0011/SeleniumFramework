package com.customException;

public class BrowserException extends Exception {
	
	public BrowserException() {
		super("Browsername is incorrect");
	}
	
	public BrowserException(String exception) {
		super(exception);
	}
}
