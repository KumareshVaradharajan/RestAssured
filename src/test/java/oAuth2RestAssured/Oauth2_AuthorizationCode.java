package oAuth2RestAssured;

import static io.restassured.RestAssured.given;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;


public class Oauth2_AuthorizationCode {
	
/*
 1.Form the Get url by add the required query paramaeters in postman
 2.launch the formed url in browser and Find the code from the url
 3.With code, Find the access token
 3. 
 */
	
	public String driverinit() throws InterruptedException
	{
		/*
		 Step 1: To find the code via Postman
		 
		 //form the below Get url by add the below query paramaeters in postman
		 
		 GET: http://coop.apps.symfonycasts.com/authorize?client_id=kumareshApp&response_type=code&redirect_uri =https://learn-automation.com/&scope =chickens-feed eggs-collect
		 
		 client_id : kumareshApp
		 response_type : code
		 redirect_uri : https://learn-automation.com/
		 scope : chickens-feed eggs-collect
		 
		 */
		
		String url = "http://coop.apps.symfonycasts.com/authorize?client_id=Kumaresh2App&response_type=code&redirect_uri=https://www.epam.com/&scope =eggs-collect eggs-count";
		System.setProperty("webdriver.gecko.driver", "./ChromeDriver/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get(url);
		
		driver.findElement(By.id("form-email")).sendKeys("kumara181019@gmail.com");
		driver.findElement(By.id("form-password")).sendKeys("Coop");
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/form/fieldset/div[3]/div/button")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/a[1]")).click();
		Thread.sleep(2000);
		
		String UrlWithCode = driver.getCurrentUrl();
		String code1 = UrlWithCode.split("code=")[1];
		String code2 = code1.split("&")[0];
		System.out.println(code2);
		
		driver.close();
		return code2;
		
	}
	
	@Test
	public void oAuth2_Coop_AuthorizationCode() throws InterruptedException
	{

			
	String code = driverinit();
		
		
	String accesstokenResponse =
		given()
			.formParam("client_id", "Kumaresh2App")
			.formParam("client_secret", "0509eb84d528b3374ce8924716cb67d6")
			.formParam("grant_type", "authorization_code")
			.formParam("redirect_uri", "https://www.epam.com/")
			.formParam("code", code)
			.formParam("scope", "eggs-collect eggs-count")
		.when()
			.post("http://coop.apps.symfonycasts.com/token")
		.then()
			.log().all()
			.extract().response().asString();
	
	JsonPath js2 = new JsonPath(accesstokenResponse);
	String access_token = js2.getString("access_token");
	
	System.out.println(access_token);
		
		given()
			.auth().oauth2(access_token)
		.when()
			.post("http://coop.apps.symfonycasts.com/api/3145/eggs-collect")
		.then()
			.log().all();
			
		
	}
	

}
