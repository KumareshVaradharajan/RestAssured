package restAssuredTests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class Post {
	
	@Test
	public void postvalidation()
	{
		baseURI = "https://rahulshettyacademy.com";
		
		given()
			.queryParam("key", "qaclick123")
			.contentType("application/json")
			.body("{\r\n"
					+ "  \"location\": {\r\n"
					+ "    \"lat\": -38.383494,\r\n"
					+ "    \"lng\": 33.427362\r\n"
					+ "  },\r\n"
					+ "  \"accuracy\": 50,\r\n"
					+ "  \"name\": \"Frontline house\",\r\n"
					+ "  \"phone_number\": \"(+91) 983 893 2007\",\r\n"
					+ "  \"address\": \"29, side layout, Chennai 09\",\r\n"
					+ "  \"types\": [\r\n"
					+ "    \"shoe park\",\r\n"
					+ "    \"shop\"\r\n"
					+ "  ],\r\n"
					+ "  \"website\": \"http://google.com\",\r\n"
					+ "  \"language\": \"French-IN\"\r\n"
					+ "}\r\n"
					+ "")
		.when()
			.post("/maps/api/place/add/json")
		.then()
			.statusCode(200)
			.assertThat().body("scope", equalTo("APP"))
			.log().all();
	}

}
