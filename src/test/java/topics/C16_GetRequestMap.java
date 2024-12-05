package topics;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C16_GetRequestMap extends BookerBaseUrl {
/*
    Given
        https://restful-booker.herokuapp.com/booking/985
    When
        I send a GET request to the URL
    Then
        The response body should be as follows:
            {
                "firstname": "Josh",
                "lastname": "Allen",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "super bowls"
            }
*/

    @Test
    public void getRequestMapTest() {

        //Set the url
        spec.pathParams("first", "booking", "second", "65");

        //Set the expected data
        Map<String, String> bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin", "2018-01-01");
        bookingdatesMap.put("checkout", "2019-01-01");

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Josh");
        expectedData.put("lastname", "Allen");
        expectedData.put("totalprice", 111);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingdatesMap);
        expectedData.put("additionalneeds", "super bowls");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map actualData = response.as(Map.class);
        System.out.println("actualData = " + actualData);
        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.get("firstname"), expectedData.get("firstname"));
        assertEquals(actualData.get("lastname"), expectedData.get("lastname"));
        assertEquals(actualData.get("totalprice"), expectedData.get("totalprice"));
        assertEquals(actualData.get("depositpaid"), expectedData.get("depositpaid"));
        assertEquals( ((Map)actualData.get("bookingdates")).get("checkin"), bookingdatesMap.get("checkin"));
        assertEquals( ((Map)actualData.get("bookingdates")).get("checkout"), bookingdatesMap.get("checkout"));
        assertEquals(actualData.get("additionalneeds"), expectedData.get("additionalneeds"));

    }

}
