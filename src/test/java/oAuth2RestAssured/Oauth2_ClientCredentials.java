package oAuth2RestAssured;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class Oauth2_ClientCredentials {
	
	@Test
	public void oauth_CoopApi_ClientCredentials()
	{
		String res = given()
			.formParam("client_id", "kumareshApp")
			.formParam("client_secret", "7c7bb85a01bcb3188f597a6bdba587ee")
			.formParam("grant_type", "client_credentials")
		.when()
			.post("http://coop.apps.symfonycasts.com/token")
		.then()
			.log().all()
			.extract().response().asString();
		
		System.out.println(res);
		
		JsonPath js = new JsonPath(res);
		String access_token = js.getString("access_token");
			
	
  baseURI = "http://coop.apps.symfonycasts.com/api";
  
		given()
			.auth().oauth2(access_token)
		.when()
			.post("/3141/chickens-feed")
		.then()
			.statusCode(200)
			.log().all();
			
	}
	

}
