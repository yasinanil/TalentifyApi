package topics;

import base_urls.BookerBaseUrl;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class C07_JsonPath extends BookerBaseUrl {
    /*
    Scenario:
    Given
     The endpoint URL: https://restful-booker.herokuapp.com/booking/20
    When
     A GET request is sent to the URL
    Then
     The HTTP status code should be 200
    And
     The response content type should be "application/json"
    And
     The response body should match the following structure:
    {
    "firstname": "Josh",
    "lastname": "Allen",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "midnight snack"
    }
    */

    @Test
    public void jsonPathTest(){

        //Set the url
        spec.pathParams("first","booking", "second","20");

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        //1st Way: Hamcrest
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", equalTo("Josh"),
                        "lastname", equalTo("Allen"),
                        "totalprice", equalTo(111),
                        "depositpaid", equalTo(true),
                        "bookingdates.checkin", equalTo("2018-01-01"),
                        "bookingdates.checkout", equalTo("2019-01-01"),
                        "additionalneeds", equalTo("midnight snack")
                        );


        //2nd Way: JsonPath
        JsonPath jsonPath = response.jsonPath();

        String firstname = jsonPath.getString("firstname");
        String lastname = jsonPath.getString("lastname");
        int totalprice = jsonPath.getInt("totalprice");
        boolean depositpaid = jsonPath.getBoolean("depositpaid");
        String checkin = jsonPath.getString("bookingdates.checkin");
        String checkout = jsonPath.getString("bookingdates.checkout");
        String additionalneeds = jsonPath.getString("additionalneeds");

        assertEquals(firstname,"Josh");
        assertEquals(lastname,"Allen");
        assertEquals(totalprice,111);
        assertTrue(depositpaid);
        assertEquals(checkin, "2018-01-01");
        assertEquals(checkout, "2019-01-01");
        assertEquals(additionalneeds, "midnight snack");


    }

}
