package booker_test;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static utilities.ObjectMapperUtils.convertJsonToJava;

public class C02_CreateBooking extends BookerBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking
    And
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

    When
        I send post request to the endpoint
    Then
        Status code should be 200
    And
        Response body should be like:
        {
            "bookingid": 1,
            "booking": {
                "firstname": "Jim",
                "lastname": "Brown",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Breakfast"
            }
        }
     */

    public static int bookingId;

    @Test
    public void createBookingTest(){

        //Set the url
        spec.pathParams("first","booking");

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
        BookingPojo expectedData = convertJsonToJava(json, BookingPojo.class);
        System.out.println("expectedData = " + expectedData);

        //Send the post request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        //Do assertion
        BookingResponsePojo actualData = response.as(BookingResponsePojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.getBooking().getFirstname(), expectedData.getFirstname());
        assertEquals(actualData.getBooking().getLastname(), expectedData.getLastname());
        assertEquals(actualData.getBooking().getTotalprice(), expectedData.getTotalprice());
        assertEquals(actualData.getBooking().getDepositpaid(), expectedData.getDepositpaid());
        assertEquals(actualData.getBooking().getBookingdates().getCheckin(), expectedData.getBookingdates().getCheckin());
        assertEquals(actualData.getBooking().getBookingdates().getCheckout(), expectedData.getBookingdates().getCheckout());
        assertEquals(actualData.getBooking().getAdditionalneeds(), expectedData.getAdditionalneeds());

        bookingId = actualData.getBookingid();//To use this id outside of this class
    }
}
