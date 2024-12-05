package topics;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class C02_Assertion {
    /*
       Given
           https://restful-booker.herokuapp.com/booking/10
       When
           User sends a GET Request to the url
       Then
           HTTP Status Code should be 200
       And
           Content Type should be JSON
       And
           Status Line should be HTTP/1.1 200 OK
   */

    @Test
    public void assertionTest(){
//        https://restful-booker.herokuapp.com/booking
//        User sends a GET Request to the url
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking");

//        HTTP Status Code should be 200
        response.then().statusCode(200);

//        Content Type should be JSON
        response.then().contentType("application/json");

//        Status Line should be "HTTP/1.1 200 OK"
        response.then().statusLine("HTTP/1.1 200 OK");

    }

    @Test
    public void assertionTestMethodChain(){
//        https://restful-booker.herokuapp.com/booking
//        User sends a GET Request to the url
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking");

        response
                .then()
                .statusCode(200)//        HTTP Status Code should be 200
                .contentType("application/json")//        Content Type should be JSON
                .statusLine("HTTP/1.1 200 OK");//        Status Line should be "HTTP/1.1 200 OK"

    }

}
