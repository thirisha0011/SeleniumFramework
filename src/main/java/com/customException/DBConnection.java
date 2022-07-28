package com.customException;

public class DBConnection extends Exception{
	
	public DBConnection() {
		super("Exception while connecting DB ");
	}
	
	public DBConnection(String exception) {
		super(exception);
	}

}
