package topics;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C19_POJOClassGetRequest extends BookerBaseUrl {
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
    public void pojoGetTest() {

        //Set the url
        spec.pathParams("first", "booking", "second", "26");

        //Set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01", "2019-01-01");
        BookingPojo expectedData = new BookingPojo("Josh","Allen", 111,true, bookingDatesPojo, "midnight snack");
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
