package booker_test;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingPojo;
import utilities.ObjectMapperUtils;

import static booker_test.C02_CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C04_UpdateBooking extends BookerBaseUrl {
        /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    And
        {
            "firstname" : "John",
            "lastname" : "Doe",
            "totalprice" : 200,
            "depositpaid" : false,
            "bookingdates" : {
                "checkin" : "2024-11-22",
                "checkout" : "2025-01-01"
            },
            "additionalneeds" : "Dinner"
        }

    When
        I send PUT request to the endpoint
    Then
        Status code should be 200
    And
        Response body should be like:
        {
            "firstname" : "John",
            "lastname" : "Doe",
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
                            "firstname" : "John",
                            "lastname" : "Doe",
                            "totalprice" : 200,
                            "depositpaid" : false,
                            "bookingdates" : {
                                "checkin" : "2024-11-22",
                                "checkout" : "2025-01-01"
                            },
                            "additionalneeds" : "Dinner"
                        }
                        """;

        BookingPojo expectedData = ObjectMapperUtils.convertJsonToJava(json, BookingPojo.class);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).put("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        BookingPojo actualData = response.as(BookingPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.getFirstname(), expectedData.getFirstname());
        assertEquals(actualData.getLastname(), expectedData.getLastname());
        assertEquals(actualData.getTotalprice(), expectedData.getTotalprice());
        assertEquals(actualData.getDepositpaid(), expectedData.getDepositpaid());
        assertEquals(actualData.getBookingdates().getCheckin(), expectedData.getBookingdates().getCheckin());
        assertEquals(actualData.getBookingdates().getCheckout(), expectedData.getBookingdates().getCheckout());
        assertEquals(actualData.getAdditionalneeds(), expectedData.getAdditionalneeds());

    }


}
