package readExternalJsonFile;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadExternalJSONFile {
	
	@Test
	public void ExternalJSONFile() throws IOException
	{
		baseURI = "https://rahulshettyacademy.com";
		
		given()
			.contentType("application/json")
			.body(new String(Files.readAllBytes(Paths.get("C:\\API Testing Files\\Library json file.json"))))
		.when()
			.post("/Library/Addbook.php")
		.then()
			.statusCode(200)
			.log().all();
	}

}
