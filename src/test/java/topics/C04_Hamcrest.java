package topics;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class C04_Hamcrest {

/*
    Given
        https://jsonplaceholder.typicode.com/todos/23
    When
        User send GET Request to the URL
    Then
        HTTP Status Code should be 200
    And
        Response format should be “application/json”
    And
        “title” is “et itaque necessitatibus maxime molestiae qui quas velit”,
    And
        “completed” is false
    And
        “userId” is 2
*/

    @Test
    void hamcrestTest(){

//        https://jsonplaceholder.typicode.com/todos/23
//        User send GET Request to the URL

        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/todos/23");
        response.prettyPrint();

//        HTTP Status Code should be 200

//        Response format should be “application/json”

//        “title” is “et itaque necessitatibus maxime molestiae qui quas velit”,

//        “completed” is false

//        “userId” is 2

        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"))
                .body("completed", equalTo(false))
                .body("userId", equalTo(2));

    }

}
