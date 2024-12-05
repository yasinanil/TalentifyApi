package booker_test;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static booker_test.C02_CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C06_DeleteBooking extends BookerBaseUrl {
        /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    When
        I send DELETE request to the endpoint
    Then
        Status code should be 201
    And
        Response body should be like: 'Created'
     */

    @Test
    public void deleteBookingTest() {

        //Set the url
        spec.pathParams("first", "booking", "second", bookingId);

        //Set the expected data
        String expectedData = "Created";

        //Send the request and get the response
        Response response = given(spec).delete("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        assertEquals(response.statusCode(), 201);
        assertEquals(response.asString(), expectedData);

    }

}
