package restAssured;

import io.restassured.path.json.JsonPath;

public class Json {
	
	public static JsonPath rawToJson(String response) {
		JsonPath js = new JsonPath(response);
		return js;
	}
	
	public static String getJsonValue(JsonPath jsonPath,String response) {
		return jsonPath.getString(response);
	}
}
