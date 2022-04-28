package ctc;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class MarksSearch {
	
	@Test
	public void search()
	{
	String s =	given()
			.headers("content-type","appication/json")
		.when()
			.get("https://staging-core.dxpapi.com/api/v1/core/?account_id=6683&auth_key=rnht3g7vp0b13ild&domain_key=marks_com&request_id=8537037411714&_br_uid_2=uid%3D1008818563330%3Av%3D11.8%3Ats%3D1639392310018%3Ahc%3D23&url=www.bloomique.com&ref_url=www.bloomique.com&request_type=search&rows=10&start=0&fl=pid%2Ctitle%2Cbrand%2Cprice%2Csale_price%2Cpromotions%2Cthumb_image%2Csku_thumb_images%2Csku_swatch_images%2Csku_color_group%2Curl%2Cprice_range%2Csale_price_range%2Cdescription%2Cdescription%2Csku_title%2Csku_size%2Csku_color%2Csku_skuid%2Csku_availability%2Cskuid&q=mocktest&search_type=keyword#response/docs/0")
		.then()
			.log().all()
			.extract().response().asString();
		System.out.println(s);
	
	}

}
