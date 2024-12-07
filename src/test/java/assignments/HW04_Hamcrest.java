package assignments;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class HW04_Hamcrest {
/*
        Homework 04:
    Write a Rest Assured test to verify the following:
    1. Given
    The endpoint: https://reqres.in/api/users/23
    2. When
    A GET request is sent to the URL
    3. Then
    o The HTTP status code should be 404
    o The status line should be HTTP/1.1 404 Not Found
    o The server should be "cloudflare"
    o The response body should be empty
 */

    @Test
    public void hamcrestTest() {

        Response response = given().get("https://reqres.in/api/users/23");
        response.prettyPrint();
        response.then()
                .statusCode(404)
                .statusLine("HTTP/1.1 404 Not Found")
                .header("Server", equalTo("cloudflare"))
                .body(equalTo("{}"));

    }
}
