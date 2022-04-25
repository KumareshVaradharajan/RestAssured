package restAssuredTests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import junit.framework.Assert;
import payloadFiles.Payload;

public class ValidateUpdatedPlaceName {
	
	@Test
	public void postvalidationExplicitPayload()
	{
		
//POST
		baseURI = "https://rahulshettyacademy.com";
		
	String response =	 given()
			.queryParam("key", "qaclick123")
			.contentType("application/json")
			.body(Payload.AddLocationJsonPayload())
		.when()
			.post("/maps/api/place/add/json")
		.then()
			.statusCode(200)
			.assertThat().body("scope", equalTo("APP"))
			.header("Server", "Apache/2.4.18 (Ubuntu)")
			.log().all()
			.extract().response().asString();
	
	System.out.println(response);
	
	JsonPath js = new JsonPath(response);
	String placeID = js.getString("place_id");
	System.out.println(placeID);
	
//Put
	
	baseURI = "https://rahulshettyacademy.com";
	
	String updatedAddress = "70 Summer walk, USA";
	
	given()
		.queryParam("key", "qaclick123")
		.queryParam("place_id", placeID)
		.contentType("application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeID+"\",\r\n"
				+ "\"address\":\""+updatedAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
	.when()
		.put("/maps/api/place/update/json")
	.then()
		.statusCode(200)
		.log().all();

	
//GET
	
String getRes = given()
		.queryParam("key", "qaclick123")
		.queryParam("place_id", placeID)
		.contentType("application/json")
	.when()
		.get("https://rahulshettyacademy.com/maps/api/place/get/json")
	.then()
		.statusCode(200)
		.log().all()
		.extract().response().asString();

	JsonPath js2 = new JsonPath(getRes);
	Assert.assertEquals(updatedAddress, js2.getString("address"));

		
	
	}
}
