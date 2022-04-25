package restAssuredTests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import payloadFiles.Payload;

public class Post2 {
	
	@Test
	public void postvalidationExplicitPayload()
	{
		baseURI = "https://rahulshettyacademy.com";
		
		given()
			.queryParam("key", "qaclick123")
			.contentType("application/json")
			.body(Payload.AddLocationJsonPayload())
		.when()
			.post("/maps/api/place/add/json")
		.then()
			.statusCode(200)
			.assertThat().body("scope", equalTo("APP"))
			.header("Server", "Apache/2.4.18 (Ubuntu)")
			.log().all();
	}

}
