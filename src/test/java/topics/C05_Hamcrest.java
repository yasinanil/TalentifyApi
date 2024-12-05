package topics;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C05_Hamcrest {
/*
    Given
        https://jsonplaceholder.typicode.com/todos
    When
        I send a GET request to the Url
    Then
        HTTP Status Code should be 200
    And
        Response format should be "application/json"
    And
        There should be 200 todos
    And
        "quis eius est sint explicabo" should be one of the todos title
    And
        2, 7, and 9 should be among the userIds
*/

    @Test
    void hamcrestTest() {
//        https://jsonplaceholder.typicode.com/todos
//        I send a GET request to the Url
        Response response = given().get("https://jsonplaceholder.typicode.com/todos");
        response.prettyPrint();



        response
                .then()
                .statusCode(200)//        HTTP Status Code should be 200
                .contentType(ContentType.JSON)//        Response format should be "application/json"
                .body("", hasSize(200),//        There should be 200 todos
                        "title", hasItem("quis eius est sint explicabo"),//        "quis eius est sint explicabo" should be one of the todos title
                        "userId", hasItems(2, 7, 9));//        2, 7, and 9 should be among the userIds

    }
}
