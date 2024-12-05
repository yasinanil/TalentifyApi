package booker_test;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingPojo;
import utilities.ObjectMapperUtils;

import java.util.Map;

import static booker_test.C02_CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C05_PartialUpdateBooking extends BookerBaseUrl {
        /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    And
        {
            "firstname" : "Mary",
            "lastname" : "Star",
        }

    When
        I send PUT request to the endpoint
    Then
        Status code should be 200
    And
        Response body should be like:
        {
            "firstname" : "Mary",
            "lastname" : "Star",
            "totalprice" : 200,
            "depositpaid" : false,
            "bookingdates" : {
                "checkin" : "2024-11-22",
                "checkout" : "2025-01-01"
            },
            "additionalneeds" : "Dinner"
        }
     */

    @Test
    public void updateBookingTest(){

        //Set the url
        spec.pathParams("first","booking","second", bookingId);

        //Set the expected data
        String json = """
                        {
                            "firstname" : "Mary",
                            "lastname" : "Star"
                        }
                        """;

        Map expectedData = ObjectMapperUtils.convertJsonToJava(json, Map.class);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).patch("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map actualData = response.as(Map.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.get("firstname"), expectedData.get("firstname"));
        assertEquals(actualData.get("lastname"), expectedData.get("lastname"));

    }


}
