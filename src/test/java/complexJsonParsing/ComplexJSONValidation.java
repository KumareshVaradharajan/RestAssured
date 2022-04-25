package complexJsonParsing;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;


public class ComplexJSONValidation {
	
/*
1. Print No of courses returned by API
2.Print Purchase Amount
3. Print Title of the first course
4. Print All course titles and their respective Prices
5. Print no of copies sold by RPA Course
6. Verify if Sum of all Course prices matches with Purchase Amount
*/
	@Test
	public void jsonValidation()
	{
		
		JsonPath js1 = new JsonPath(MockAPI.courseprice());

//1. Print No of courses returned by API	
		
		int count = js1.getInt("courses.size()");
		System.out.println(count);

//2. Print Purchase Amount
		
		int purchaseAmount = js1.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		
//3. Print Title of the first course
		
		String titleOfFirstCourse = js1.getString("courses[0].title");
		System.out.println(titleOfFirstCourse);

//4. Print All course titles and their respective Prices
		
		for(int i=0; i<count; i++)
		{
		String title = js1.getString("courses["+i+"].title");
		System.out.println(title);
		}

//5. Print no of copies sold by RPA Course		
		
		for(int i=0; i<count; i++)
		{
			if((js1.getString("courses["+i+"].title")).equals("RPA"))
			{
				String copies = js1.getString("courses["+i+"].copies");
				System.out.println(copies);
			}
		}

//6. Verify if Sum of all Course prices matches with Purchase Amount
		int totalPrice =0;
		
		for(int i=0; i<count; i++)
		{
			
			totalPrice = totalPrice+((js1.getInt("courses["+i+"].price"))*((js1.getInt("courses["+i+"].copies"))));
			System.out.println(totalPrice);

		}
		if(purchaseAmount==totalPrice)
		{
			System.out.println("Sum of all Course prices matches with Purchase Amount");
		}
		else
		{
			System.out.println("Sum of all Course prices NOT matches with Purchase Amount");
		}
		
		
	}

}
