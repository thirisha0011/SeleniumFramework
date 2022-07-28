package apiTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.customException.BrowserException;

import io.restassured.path.json.JsonPath;
import restAssured.BaseURL;
import restAssured.SamplePayload;

public class SampleTestCase extends BaseURL {
	
	@Test
	public void show() {
		given().
			get("/users?page=2")
		.then()
			.body("data[1].first_name", equalTo("Lindsay"))
		.log().body();
	}
	
	//@Test
	public void getResponse() {
		int sum = 0;
		JsonPath js = new JsonPath(SamplePayload.Section);
		
		//Print No of courses returned by API
		int size = js.getInt("courses.size()");
		System.out.println(size);
		
		//Print Purchase Amount
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		
		//Print Title of the first course
		String string = js.getString("courses.title[0]");
		//Second way
		js.getString("courses[0].title");
		System.out.println(string);
		
		//Print All course titles and their respective Prices
		for (int i = 0; i < size; i++) {
			String courseTitle = js.getString("courses["+i+"].title");
			System.out.println(courseTitle);
			String coursePrice = js.getString("courses["+i+"].price");
			System.out.println(coursePrice);
		}
		
		//Print no of copies sold by RPA Course
		int coursePrice = 0;
		for (int i = 0; i < size; i++) {
			String courseTitle = js.getString("courses["+i+"].title");
			if(courseTitle.equals("RPA")) {
				System.out.println("Course name "+courseTitle);
				coursePrice = js.getInt("courses["+i+"].copies");
				System.out.println("No Of Copies "+coursePrice);
				break;
			}
		}
		
		//Verify if Sum of all Course prices matches with Purchase Amount
		int price=0;
		for (int i = 0; i < size; i++) {
			price = js.getInt("courses["+i+"].price");
			coursePrice = js.getInt("courses["+i+"].copies");
			int amount = price*coursePrice;
			sum+=amount;
		}
		System.out.println("Sum is "+sum);
		Assert.assertEquals(sum, purchaseAmount);
	}
	
	//@Test
	public void authCode() throws BrowserException, IOException {
		String Auth_Url = getData("Auth Url");
		String Client_id = getData("Client id");
		String Client_Secret = getData("Client Secret");
		String Redirect_Url = getData("Redirect Url");
		String Grant_Type = getData("Grant Type");
		
		WebDriver driver = Initialize("Chrome",Auth_Url);
		String currentUrl = driver.getCurrentUrl();
		//Manipulate the code accordingly
		
		String partialURL = currentUrl.split("code=")[0];
		String authCode = partialURL.split("&scope")[0];
		getAccessToken(authCode, Client_id, Client_Secret, Redirect_Url, Grant_Type, Auth_Url, "access_token");
	}
}
