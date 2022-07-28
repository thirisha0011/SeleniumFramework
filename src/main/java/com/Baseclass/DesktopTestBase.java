package com.Baseclass;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

import com.excelSheet.DataProviders;

public class DesktopTestBase extends DataProviders{
	
	
	public WebDriver getWiniumDriver() throws IOException {
		DesktopOptions options = new DesktopOptions();
		
		String appPath = getData("App Path");
		String Host = getData("Host");
		String PortNo = getData("Port No");

		options.setApplicationPath(appPath);
		
		WebDriver drivers = new WiniumDriver(new URL("http://"+Host+":"+PortNo), options);
		
		return drivers;
	}
}
