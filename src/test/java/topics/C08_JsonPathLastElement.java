package topics;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class C08_JsonPathLastElement extends JsonPlaceHolderBaseUrl {
/*
    Given https://jsonplaceholder.typicode.com/todos
    When
    User sends a GET request to the URL
    Then
    HTTP status code should be 200
    And
    Content type must be JSON
    And
    "last title" is "ipsam aperiam voluptates qui"
*/

    @Test
    public void jsonPathTest(){

        //Set the url
        spec.pathParams("first","todos");

        //Set the expected data


        //Send the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();

        //Do assertion
        //1st Way: Hamcrest
        int lastIndex = response.jsonPath().getList("id").size()-1;

        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("["+lastIndex+"].title", equalTo("ipsam aperiam voluptates qui"));

        //2nd Way: JsonPath
        int statusCode = response.statusCode();
        String contentType = response.contentType();
        assertEquals(statusCode, 200);
        assertEquals(contentType, "application/json; charset=utf-8");

        String lastTitle = response.jsonPath().getString("["+lastIndex+"].title");
        System.out.println("lastTitle = " + lastTitle);
        assertEquals(lastTitle,"ipsam aperiam voluptates qui");


    }


}
