package com.customException;

public class NoTestCaseEntry extends Exception{

	public NoTestCaseEntry() {
		super("No Testcase Entry in TestData sheet");
	}
	
	public NoTestCaseEntry(String exception) {
		super(exception);
	}
}
