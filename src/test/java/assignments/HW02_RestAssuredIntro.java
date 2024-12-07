package assignments;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class HW02_RestAssuredIntro {
/*
    Homework 02:
    Write a Rest Assured test to perform the following:
    1. Endpoint: https://reqres.in/api/users/3
    2. Steps:
    o Given: The URL is provided.
    o When: The user sends a GET request to the URL.
    3. Assertions:
    o Verify the HTTP status code is 200.
    o Verify the content type is application/json.
    o Verify the status line is HTTP/1.1 200 OK.
 */

    @Test
    public void restAssuredIntroTest(){

        Response response = RestAssured.get("https://reqres.in/api/users/3");
        //response.prettyPrint();

        response.then().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

    }

}
