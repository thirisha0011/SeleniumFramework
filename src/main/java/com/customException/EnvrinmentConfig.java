package com.customException;

import net.bytebuddy.implementation.bind.annotation.Super;

public class EnvrinmentConfig extends Exception{
	public EnvrinmentConfig() {
		super("Missed Configuration Data..!");
	}
	
	public EnvrinmentConfig(String exception) {
		super(exception);
	}
}
