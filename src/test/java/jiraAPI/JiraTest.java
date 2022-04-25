package jiraAPI;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class JiraTest {
	
	@Test
	public void jiraPostComment()
	{
	
	baseURI = "http://localhost:8081";
		
//1. Login to Jira to create session using Login API
		
	SessionFilter session = new SessionFilter();
	
	String response = 
		given()
			.contentType("application/json")
			.body("{ \"username\": \"kumaresh\", \"password\": \"Jira@12345\" }")
			.filter(session)
		.when()
			.post("/rest/auth/1/session")
		.then()
			.statusCode(200)
			.log().all()
			.extract().response().asString();
	
//2. Add a Comment to existing issue using "Add comment" API
		
	String addCommentResponse =
		given()
			.contentType("application/json")
			.pathParam("key", "10002")
			.body("{\r\n"
					+ "    \"body\": \"My Advanced Rest Assured - Automated Jira comment!\",\r\n"
					+ "    \"visibility\": {\r\n"
					+ "        \"type\": \"role\",\r\n"
					+ "        \"value\": \"Administrators\"\r\n"
					+ "    }\r\n"
					+ "}")
			.filter(session)
		.when()
			.post("/rest/api/2/issue/{key}/comment")
		.then()
			.statusCode(201)
			.log().all()
			.extract().response().asString();
		
//3. Add an attachment to existing issue using "Add Attachment API"
		
		given()
			.header("X-Atlassian-Token", "no-check")
			.contentType("multipart/form-data")
			.filter(session)
			.multiPart("file", new File("jira.txt"))
		.when()
			.post("/rest/api/2/issue/RSA-3/attachments")
		.then()
			.statusCode(200)
			.log().all();
		
//4. Get issue details and Verify if added comments and attachments exists using GET API
		
//All Response
		
	String getAllResponse = 	
		given()
			.filter(session)
			.pathParam("Key", "RSA-3")
		.when()
			.get("/rest/api/2/issue/{Key}")
		.then()
			.statusCode(200)
			.log().all()
			.extract().response().asString();

 //Only Comments Response
	
	String getCommentsResponse = 	
			given()
				.filter(session)
				.pathParam("Key", "RSA-3")   //Used to location particular resource by respresnting path
				.queryParam("fields", "comment")   //Use to filter any specific part of the resource
			.when()
				.get("/rest/api/2/issue/{Key}")
			.then()
				.statusCode(200)
				.log().all()
				.extract().response().asString();

//Fetch the comment added in this session from the whole response
	
	JsonPath js1 = new JsonPath(addCommentResponse);
	int commentID = js1.getInt("id");
	
	JsonPath js2 = new JsonPath(getCommentsResponse);
	int commentsSize = js2.getInt("fields.comment.comments.size()");
	
	for(int i=0; i<commentsSize; i++)
	{
		if(js2.getInt("fields.comment.comments["+i+"].id") == commentID)
		{
			System.out.println((js2.getString("fields.comment.comments["+i+"].body")));
		}
	}
		
	}
	
}
