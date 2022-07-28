package com.Baseclass;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.customException.BrowserException;

import enumVariales.BrowserName;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CloudConnection {
	
	private static WebDriver driver;
	private static final MutableCapabilities sauceLabs = new MutableCapabilities();
	private static final DesiredCapabilities cap = new DesiredCapabilities();
	public static WebDriver connectWithSauceLabs(String buildName, String SeleniumVersion, String oSName, String userrname, String accessKey, String tags, String browserName,String browserVersion, String url) throws BrowserException, MalformedURLException {
		sauceLabs.setCapability("build", buildName);
		sauceLabs.setCapability("seleniumVersion", SeleniumVersion);
		sauceLabs.setCapability("username", userrname);
		sauceLabs.setCapability("accessKey", accessKey);
		sauceLabs.setCapability("tags", tags);
		
		cap.setCapability("sauce:options", sauceLabs);
		cap.setCapability("browserVersion", browserVersion);
		cap.setCapability("platformName", oSName);
		switch (browserName.toUpperCase()) {
		case "CHROME":
			WebDriverManager.chromedriver().setup();
			cap.setCapability("browserName", browserName);
		break;
			
		case "FIREFOX":
			WebDriverManager.firefoxdriver().setup();
			cap.setCapability("browserName", browserName);
		break;
		
		case "IE":
			WebDriverManager.iedriver().setup();
			cap.setCapability("browserName", browserName);
		break;
		
		case "EDGE":
			WebDriverManager.iedriver().setup();
			cap.setCapability("browserName", browserName);
		break;
		
		case "SAFARI":
			WebDriverManager.safaridriver().setup();
			cap.setCapability("browserName", browserName);
		break;
		
		case "OPERA":
			WebDriverManager.operadriver().setup();
			cap.setCapability("browserName", browserName);
		break;
		
		default:
			throw new BrowserException("Invalid Browser Name "+browserName);
		}
		
		driver = new RemoteWebDriver(new URL(url), cap);
		return driver;
	}
	
	
	public static WebDriver connectWithSauceLabs(BrowserName browserName) throws MalformedURLException{
		MutableCapabilities capabilities = null;
		System.out.println("Browser Name is "+browserName.toString());
		sauceLabs.setCapability("build", "Customized Testing Framework");
		sauceLabs.setCapability("seleniumVersion", "4.0.0");
		sauceLabs.setCapability("username", "oauth-dxavier1962-eaf29");
		sauceLabs.setCapability("accessKey", "10fb6e18-9cb6-401b-91e0-97132cbeed22");
		sauceLabs.setCapability("tags", "Test with Sauce Labs");
		
		cap.setCapability("sauce:options", sauceLabs);
		cap.setCapability("browserVersion", "latest");
		cap.setCapability("platformName", "Windows 10");
		if(browserName.equals(BrowserName.CHROME)) {
			WebDriverManager.chromedriver().setup();
			cap.setCapability("browserName", browserName.toString());
		}
		
//		switch (browserName) {
//		case CHROME:
//			WebDriverManager.chromedriver().setup();
//			//capabilities = new ChromeOptions();
//			cap.setCapability("browserName", browserName.toString());
//			//capabilities.setCapability("browserName", browserName.toString());
//		break;
//			
//		case FIREFOX:
//			break;
//		
//		case IE:
//			
//		default:
//			break;
//		}
		
		//https://oauth-dxavier1962-eaf29:10fb6e18-9cb6-401b-91e0-97132cbeed22@ondemand.eu-central-1.saucelabs.com:443/wd/hub
		driver = new RemoteWebDriver(new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub"), cap);
		return driver;
	}
	
	public static WebDriver connectwithBrowserStack(String userrname, String accessKey, String url, String testName, String osName, String osVersion,String browserName, String browserVersion) throws BrowserException, MalformedURLException {
		
		cap.setCapability("sauce:options", sauceLabs);
		cap.setCapability("browserVersion", browserVersion);
		cap.setCapability("os", osName);
		cap.setCapability("os_version", osVersion);
		cap.setCapability("browser", browserName);
		cap.setCapability("name", testName);
		
		switch (browserName.toUpperCase()) {
		case "CHROME":
			WebDriverManager.chromedriver().setup();
			cap.setCapability("browserName", browserName);
		break;
			
		case "FIREFOX":
			WebDriverManager.firefoxdriver().setup();
			cap.setCapability("browserName", browserName);
		break;
		
		case "IE":
			WebDriverManager.iedriver().setup();
			cap.setCapability("browserName", browserName);
		break;
		
		case "EDGE":
			WebDriverManager.iedriver().setup();
			cap.setCapability("browserName", browserName);
		break;
		
		case "SAFARI":
			WebDriverManager.safaridriver().setup();
			cap.setCapability("browserName", browserName);
		break;
		
		case "OPERA":
			WebDriverManager.operadriver().setup();
			cap.setCapability("browserName", browserName);
		break;
		
		default:
			throw new BrowserException("Invalid Browser Name "+browserName);
		}
		
		driver = new RemoteWebDriver(new URL("https://"+userrname+":"+accessKey+url), cap);
		return driver;
	}
}
