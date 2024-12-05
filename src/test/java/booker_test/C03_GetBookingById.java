package booker_test;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingPojo;
import utilities.ObjectMapperUtils;

import static booker_test.C02_CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C03_GetBookingById extends BookerBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/:id
        When
            I send a GET request to the URL
        Then
            Status code should be 200
        And
            The response body should be as follows:
                {
                "firstname" : "Jim",
                "lastname" : "Brown",
                "totalprice" : 111,
                "depositpaid" : true,
                "bookingdates" : {
                    "checkin" : "2018-01-01",
                    "checkout" : "2019-01-01"
                },
                "additionalneeds" : "Breakfast"
                }
    */

    @Test
    public void getBookingByIdTest(){

        //Set the url
        spec.pathParams("first","booking","second", bookingId);

        //Set the expected data
        String json = """
                                {
                                "firstname" : "Jim",
                                "lastname" : "Brown",
                                "totalprice" : 111,
                                "depositpaid" : true,
                                "bookingdates" : {
                                    "checkin" : "2018-01-01",
                                    "checkout" : "2019-01-01"
                                },
                                "additionalneeds" : "Breakfast"
                                }
                """;

        BookingPojo expectedData = ObjectMapperUtils.convertJsonToJava(json, BookingPojo.class);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}");
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
