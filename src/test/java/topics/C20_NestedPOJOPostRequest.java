package topics;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C20_NestedPOJOPostRequest extends BookerBaseUrl {
/*
    Given
    1) Endpoint: https://restful-booker.herokuapp.com/booking
    2) Request Body:
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

    When
    I send a POST request to the above URL

    Then
    1) The status code should be 200
    2) The response body should match the following structure:
       {
           "bookingid": 2243,
           "booking":
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
       }
*/

    @Test
    public void nestedPojoTest(){

        //Set the url
        spec.pathParams("first","booking");

        //Set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01", "2019-01-01");
        BookingPojo expectedData = new BookingPojo("Josh","Allen", 111,true, bookingDatesPojo, "midnight snack");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
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
        assertEquals(actualData.getBooking().getBookingdates().getCheckin(), bookingDatesPojo.getCheckin());
        assertEquals(actualData.getBooking().getBookingdates().getCheckout(), bookingDatesPojo.getCheckout());
        assertEquals(actualData.getBooking().getAdditionalneeds(), expectedData.getAdditionalneeds());

    }
}
