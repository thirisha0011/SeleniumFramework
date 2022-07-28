package com.flipkart.pageObject;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.Reports.ReportStatus;
import com.Reports.Reports;
import com.aventstack.extentreports.ExtentTest;

import mainPageObject.PageObject;

public class UserCredentials extends PageObject{
	
	//private static UserCredentials credentials;
	private WebDriver driver;
	private ExtentTest createTest;
	public UserCredentials(WebDriver driver, ExtentTest createTest) {
		super(driver);
		this.driver = driver;
		this.createTest = createTest;
		element("login_btn_SignIn", By.linkText("Sign in"));
		element("login_txt_Username", By.name("username"));
		element("login_txt_Password", By.name("password"));
		element("login_btn_signIn", By.xpath("//button[text()='Login']"));
		element("login_lnk_Forgotpassword?", By.linkText("Forgot your password?"));
		element("login_txt_registerEmail", By.id("email_create"));
		element("login_btn_createAccount",By.id("SubmitCreate"));
	}
	
	/*public UserCredentials(WebDriver drivers) {
		driver = drivers;
		element("login_btn_SignIn", By.linkText("Sign in"));
		element("login_txt_Username", By.id("email"));
		element("login_txt_Password", By.id("passwd"));
		element("login_btn_signIn", By.id("SubmitLogin"));
		element("login_lnk_Forgotpassword?", By.linkText("Forgot your password?"));
		element("login_txt_registerEmail", By.id("email_create"));
		element("login_btn_createAccount",By.id("SubmitCreate"));
	}*/
	
	//DataProviders dp = new DataProviders(driver);
	
	/*public static UserCredentials getInstance(WebDriver drivers) {
		if(credentials==null) {
			credentials = new UserCredentials();
			//Always give element("classname_txt/btn_elementName", By.locator);
			element("login_btn_SignIn", By.linkText("Sign in"));
			element("login_txt_Username", By.id("email"));
			element("login_txt_Password", By.id("passwd"));
			element("login_btn_signIn", By.id("SubmitLogin"));
			element("login_lnk_Forgotpassword?", By.linkText("Forgot your password?"));
			element("login_txt_registerEmail", By.id("email_create"));
			element("login_btn_createAccount",By.id("SubmitCreate"));
		}
		driver = drivers;
		return credentials;
	}*/
	
	public void login(String email, String password) throws InterruptedException, IOException {
		Reports.log(createTest, "Image Added...!", ReportStatus.Pass);
		//element("login_btn_SignIn").click();
		element("login_txt_Username").sendKeys(email);
		element("login_txt_Password").sendKeys(password);
		element("login_btn_signIn").click();
	}
	
	public void register(String email) throws InterruptedException {
		element("login_btn_SignIn").click();
		element("login_txt_registerEmail").sendKeys(email);
		element("login_btn_createAccount").click();
	}
}
