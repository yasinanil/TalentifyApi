package topics;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class C03_Assertion {
    /*
   Given
       https://restful-booker.herokuapp.com/booking/0
   When
       User send a GET Request to the url
   Then
       HTTP Status code should be 404
   And
       Status Line should be HTTP/1.1 404 Not Found
   And
       Response body contains "Not Found"
   And
       Response body does not contain "Hello World"
   And
       Server is "Cowboy"
*/

    @Test
    public void assertionTest() {
//        https://restful-booker.herokuapp.com/booking/0
//        User send a GET Request to the url
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/0");
        response.prettyPrint();

        response
                .then()
                .statusCode(404)//        HTTP Status code should be 404
                .statusLine("HTTP/1.1 404 Not Found")//        Status Line should be HTTP/1.1 404 Not Found
        ;

//        Response body contains "Not Found"
        assert response.asString().contains("Not Found");

//        Response body does not contain "Hello World"
        assert !response.asString().contains("Hello World");

//        Server is "Cowboy"
        String server = response.header("Server");
        assertEquals(server,"Cowboy");

    }


}
