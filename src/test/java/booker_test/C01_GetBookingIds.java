package booker_test;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class C01_GetBookingIds extends BookerBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking
    When
        I send get request to the endpoint
    Then
        Status code should be 200
    And
        Response should contain booking ids
     */

    @Test
    public void getBookingIdsTest() {

        //Set the url
        spec.pathParams("first", "booking");

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();

        //Do assertion
        assertEquals(response.statusCode(), 200);
        assertFalse(response.jsonPath().getList("bookingid").isEmpty());

    }
}
