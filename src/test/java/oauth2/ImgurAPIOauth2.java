package oauth2;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;


public class ImgurAPIOauth2 {
	
	@Test
	public void getMethod()
	{
	/*	String accessTokenResponse = 
				given()
					.queryParam("code", "")
					.queryParam("client_id", "37b9174e99fdd85")
					.queryParam("client_secret", "6502390bb33d0e0ad1d863ead2d4454b77fa4edd")
					.queryParam("auth_url", "https://api.imgur.com/oauth2/authorize")
					.queryParam("grant_type", "authorization_code")
				.when()
					.post("https://api.imgur.com/oauth2/token")
				.then()
					.log().all()
					.extract().response().asString();
		JsonPath js = new JsonPath(accessTokenResponse);
		String accesstoken = js.getString("access_token");
		*/
		
		String accesstokenvalue = "64a812692c80001f6e88e3ac771670115a37e004";
		
		String response = given()
			.queryParam("access_token", accesstokenvalue)
			.queryParam("client_id", "37b9174e99fdd85")
			.queryParam("client_secret", "6502390bb33d0e0ad1d863ead2d4454b77fa4edd")
			.queryParam("auth_url", "https://api.imgur.com/oauth2/authorize")
			.queryParam("grant_type", "authorization_code")
		.when()
			.get("https://api.imgur.com/3/account/me/images")
		.then()
			.log().all()		
			.extract().response().asString();
		
		
	}
	
	

}
