package topics;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.JsonPlaceholderPojo;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C18_POJOClass extends JsonPlaceHolderBaseUrl {
/*
    Given
        https://jsonplaceholder.typicode.com/todos
        {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
        }
    When
        I send a POST Request to the URL
    Then
        Status code is 201
    And
        Response body is like:
        {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false,
            "id": 201
        }
*/

    @Test
    public void pojoTest(){

        //Set the url
        spec.pathParams("first","todos");

        //Set the expected data
        JsonPlaceholderPojo expectedData = new JsonPlaceholderPojo(55,"Tidy your room",false);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        //Do assertion
        JsonPlaceholderPojo actualData = response.as(JsonPlaceholderPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(), 201);
        assertEquals(actualData.getTitle(), expectedData.getTitle());
        assertEquals(actualData.getUserId(), expectedData.getUserId());
        assertEquals(actualData.getCompleted(), expectedData.getCompleted());

    }

}
