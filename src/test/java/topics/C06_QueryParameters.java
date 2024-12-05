package topics;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

public class C06_QueryParameters {
/*
       Given
           https://restful-booker.herokuapp.com/booking
       When
           User sends get request to the URL
       Then
           Status code is 200
         And
             Among the data there should be someone whose firstname is "Josh" and lastname is "Allen"
*/

    @Test
    public void queryParamTest(){

//        https://restful-booker.herokuapp.com/booking
//        User sends get request to the URL
        Response response = given().get("https://restful-booker.herokuapp.com/booking/?firstname=Josh&lastname=Allen");
        response.prettyPrint();

        response
                .then()
                .statusCode(200)//        Status code is 200
                .body("bookingid", hasSize(greaterThan(0)));//        Among the data there should be someone whose firstname is "Josh" and lastname is "Allen"



    }

}
