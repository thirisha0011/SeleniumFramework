package com.Baseclass;

import java.io.IOException;

public class WebDriverKillProcessor {
	
	public static void killOpenedWebBroser() {
		
		try {
//			Runtime.getRuntime().exec("taskkill /im chrome.exe /f /t");
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
//			Runtime.getRuntime().exec("taskkill /im chromedriver.exe /f /t");
//			System.out.println("Kill opened Chrome Browser....Done");
//			Runtime.getRuntime().exec("taskkill /im iexplore.exe /f /t");
//			Runtime.getRuntime().exec("taskkill /im IEDriverServer.exe /f /t");
			System.out.println("Kill opened IE Browser....Done");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String ar[]) {
		killOpenedWebBroser();
	}

}
