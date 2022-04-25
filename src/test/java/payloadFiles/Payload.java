package payloadFiles;

public class Payload {
	
	public static String AddLocationJsonPayload()
	{
		
		return "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 60,\r\n"
				+ "  \"name\": \"Frontline home\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"19, side layout, Coimbatore 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "";
	}
	
	
	public static String AddBooksPayload(String isbn, String aisle)
	{
		
		return "{\r\n"
				+ "\r\n"
				+ "\"name\":\"Learn Selenium Automation with Python\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"Kumaresh V\"\r\n"
				+ "}\r\n"
				+ "";
	}

}
