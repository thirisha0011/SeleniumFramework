package restAssured;

import static io.restassured.RestAssured.*;

import org.testng.annotations.BeforeClass;

import com.Baseclass.WebTestBase;

import io.restassured.path.json.JsonPath;

public class BaseURL extends WebTestBase {
	
	@BeforeClass
	public void url() {
		baseURI = "https://reqres.in/api";
	}
	
	public String getAccessToken(String code, String client_id, String client_secret, String redirect_url, String grant_type, String url,
			String keyForAccessToken) {
		String authCode = given().queryParams("code", code)
		.queryParams("client_id", client_id)
		.queryParams("client_secret", client_secret)
		.queryParams("redirect_url", redirect_url)
		.queryParams("grant_type", grant_type)
		.when().post(url).asString();
		
		JsonPath js = Json.rawToJson(authCode);
		return js.getString(keyForAccessToken);
	}
}