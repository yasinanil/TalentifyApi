package topics;

import base_urls.BookerBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C17_PostRequestNestedMap extends BookerBaseUrl {
    /*
    Given
    1) Endpoint: https://restful-booker.herokuapp.com/booking
    2) Request Body:
       {
           "firstname": "John",
           "lastname": "Doe",
           "totalprice": 15,
           "depositpaid": true,
           "bookingdates": {
               "checkin": "2023-03-07",
               "checkout": "2024-09-25"
           },
           "additionalneeds": "Lunch"
       }

    When
    I send a POST request to the above URL

    Then
    1) The status code should be 200
    2) The response body should match the following structure:
       {
           "bookingid": 2243,
           "booking": {
               "firstname": "John",
               "lastname": "Doe",
               "totalprice": 471,
               "depositpaid": true,
               "bookingdates": {
                   "checkin": "2023-03-07",
                   "checkout": "2024-09-25"
               },
               "additionalneeds": "Lunch"
           }
       }
*/

    @Test
    public void postRequestNestedMapTest(){

        //Set the url
        spec.pathParams("first","booking");

        //Set the expected data
        Map<String, String> bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin", "2023-03-07");
        bookingdatesMap.put("checkout", "2024-09-25");

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "John");
        expectedData.put("lastname", "Doe");
        expectedData.put("totalprice", 471);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingdatesMap);
        expectedData.put("additionalneeds", "Lunch");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        //Do assertion
        Map actualData = response.as(Map.class);
        System.out.println("actualData = " + actualData);
        assertEquals(response.statusCode(), 200);
        assertEquals( ((Map)actualData.get("booking")).get("firstname"), expectedData.get("firstname"));
        assertEquals( ((Map)actualData.get("booking")).get("lastname"), expectedData.get("lastname"));
        assertEquals( ((Map)actualData.get("booking")).get("totalprice"), expectedData.get("totalprice"));
        assertEquals( ((Map)actualData.get("booking")).get("depositpaid"), expectedData.get("depositpaid"));
        assertEquals( ((Map) ((Map)actualData.get("booking")).get("bookingdates")).get("checkin"), bookingdatesMap.get("checkin"));
        assertEquals( ((Map) ((Map)actualData.get("booking")).get("bookingdates")).get("checkout"), bookingdatesMap.get("checkout"));
        assertEquals( ((Map)actualData.get("booking")).get("additionalneeds"), expectedData.get("additionalneeds"));
    }

}
