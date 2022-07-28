package apiTest;

import com.Baseclass.WebTestBase;
import com.Reports.ReportStatus;
import com.Reports.Reports;
import com.Reports.ScreenRecorderUtil;
import com.aventstack.extentreports.ExtentTest;
import com.flipkart.pageObject.UserCredentials;
import com.runner.TestRunner;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;

public class Login extends WebTestBase{
	
	private WebDriver driver;
	private WebTestBase webTestBase;
	private UserCredentials userCredentials;
	private TestRunner runner = new TestRunner();
	private ExtentTest createTest;
	
	@Given("user opens app and passes URL")
	public void user_opens_app_and_passes_url() throws Exception {
		//System.out.println("Browser name is "+getbrowserName());
		String URL = getData("URL");
		driver = Initialize(getAppName(), URL);
		webTestBase = new WebTestBase(driver);
		//Reports.log(createTest, "BrowserOpened Successfully", ReportStatus.pass);
	}
	
	@Given("user logs into the application with username {string} and Password {string}")
	public void user_logs_into_the_application_with_username_and_password(String username, String password) throws Exception {
		//UserCredentials userCredentials = UserCredentials.getInstance(driver);
		createTest = runner.createTest("Login", "Login Scenario", driver);
		//Reports.log(createTest, "Log into app", ReportStatus.BUSINESSSTEP);
		ExtentTest test = new Reports().createNode(createTest, "Logging In");
		ScreenRecorderUtil.startRecord("Sample_Recording");
		userCredentials = new UserCredentials(driver, test);
		userCredentials.login(username, password);
		Reports.log(test, "Logged In", ReportStatus.pass);
		webTestBase.closeTab();
		ScreenRecorderUtil.stopRecord();
		Reports.log(test, "", ReportStatus.VIDEO);
	}
	
	@Given("User enters the {string} id to create an account")
	public void user_enters_the_id_to_create_an_account(String email) throws Exception {
		//UserCredentials userCredentials = UserCredentials.getInstance(driver);
		createTest = runner.createTest("Register", "Registration Scenario", driver);
		//Reports.log(createTest, "Registering Email ID", ReportStatus.BUSINESSSTEP);
		ExtentTest test = new Reports().createNode(createTest, "Registering User");
		ScreenRecorderUtil.startRecord("Sample_Recording");
		userCredentials = new UserCredentials(driver, test);
		userCredentials.register(email);
		webTestBase.closeTab();
		ScreenRecorderUtil.stopRecord();
		Reports.log(test, "Regestered Successfully", ReportStatus.pass);
		Reports.log(test, "Pased", ReportStatus.VIDEO);
	}
	
	@After
	public void close() {
		if(webTestBase!=null)
			webTestBase.closeBrowser();
		runner.closeReports();
	}
}