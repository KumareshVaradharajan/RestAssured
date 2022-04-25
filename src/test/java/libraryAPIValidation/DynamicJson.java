package libraryAPIValidation;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import payloadFiles.Payload;

public class DynamicJson {
	
	@Test
	public void addBook()
	{
		baseURI = "https://rahulshettyacademy.com";
		
	String response =
		given()
			.contentType("application/json")
			.body(Payload.AddBooksPayload("asdsf", "6002"))
		.when()
			.post("/Library/Addbook.php")
		.then()
			.statusCode(200)
			.log().all()
			.extract().response().asString();
	
	JsonPath js = new JsonPath(response);
	System.out.println("ID is "+js.getString("ID"));
	
		
	}

}
