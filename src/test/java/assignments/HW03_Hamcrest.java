package assignments;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class HW03_Hamcrest {
/*
        Homework 03:
    Create a test using Rest Assured to validate the following scenario:
    1. Endpoint: https://reqres.in/api/users/2
    2. Steps to implement:
    o Given: A base URL https://reqres.in/api/users/2
    o When: A GET request is sent to the endpoint
    o Then: Validate the following:
    ▪ The HTTP status code is 200
    ▪ The response format is "application/json"
    ▪ The value of "email" is "janet.weaver@reqres.in"
    ▪ The value of "first_name" is "Janet"
    ▪ The value of "last_name" is "Weaver"
    ▪ The "text" field contains the message:
    "To keep ReqRes free, contributions towards server costs are appreciated!"
 */

    @Test
    public void hamcrestTest(){

       Response response = RestAssured.get("https://reqres.in/api/users/2");
       response.prettyPrint();
       response
               .then()
               .statusCode(200)
               .contentType(ContentType.JSON)
               .body("data.email", equalTo("janet.weaver@reqres.in"),
                       "data.first_name", equalTo("Janet"),
                       "data.last_name", equalTo("Weaver"),
                       "support.text", equalTo("Tired of writing endless social media content? Let Content Caddy generate it for you."));

    }
}
