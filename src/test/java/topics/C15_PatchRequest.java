package topics;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C15_PatchRequest extends JsonPlaceHolderBaseUrl {
/*
    Given:
      1) URL: https://jsonplaceholder.typicode.com/todos/198
      2) Payload: {"title": "Study Lesson"}
    When:
      A PATCH request is sent to the URL
    Then:
      - The status code is 200
      - The response body is expected to match:
        {
          "completed": false,
          "title": "Study Lesson",
          "userId": 21,
          "id": 198
        }
*/

    @Test
    public void patchRequestTest() {

        //Set the url
        spec.pathParams("first", "todos", "second", "198");

        //Set the expected data
        Map<String, String> expectedData = new HashMap<>();
        expectedData.put("title","Study Lesson");

        //Send the request and get the response
        Response response = given(spec).body(expectedData).patch("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String, Object> actualData = response.as(Map.class);
        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.get("title"), expectedData.get("title"));

        assertEquals(actualData.get("completed"), true);
        assertEquals(actualData.get("userId"), 10);



    }


}
