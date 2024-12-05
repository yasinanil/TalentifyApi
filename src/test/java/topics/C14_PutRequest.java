package topics;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C14_PutRequest extends JsonPlaceHolderBaseUrl {
/*
    Given:
    1) The URL: https://jsonplaceholder.typicode.com/todos/198
    2) Request body:
       {
          "userId": 21,
          "title": "Read Books",
          "completed": false
       }

    When:
    - A PUT request is sent to the URL.

    Then:
    - The status code should be 200.
    - The response body should match:
       {
          "completed": false,
          "title": "Read Books",
          "userId": 21,
          "id": 198
       }
*/

    @Test
    public void putRequestTest() {

        //Set the url
        spec.pathParams("first", "todos", "second", "198");

        //Set the expected data
        Map<String, Object> payload = new HashMap<>();
        payload.put("completed",false);
        payload.put("title","Read Books");
        payload.put("userId",21);

        //Send the request and get the response
        Response response = given(spec).body(payload).put("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map actualData = response.as(Map.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.get("completed"), payload.get("completed"));
        assertEquals(actualData.get("title"), payload.get("title"));
        assertEquals(actualData.get("userId"), payload.get("userId"));


    }


}
