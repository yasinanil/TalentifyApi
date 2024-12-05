package topics;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C13_PostRequestMapPayload extends JsonPlaceHolderBaseUrl {
    /*
    Given:
    1) URL: https://jsonplaceholder.typicode.com/todos
    2) Request Body:
       {
           "userId": 55,
           "title": "Tidy your room",
           "completed": false
       }

    When:
    A POST request is sent to the URL.

    Then:
    - The status code should be 201.
    - The response body should match:
       {
           "userId": 55,
           "title": "Tidy your room",
           "completed": false,
           "id": 201
       }
    */

    @Test
    public void postRequestTest() {

        //Set the url
        spec.pathParams("first", "todos");

        //Set the expected data
        Map<String, Object> payload = new HashMap();
        payload.put("userId", 55);
        payload.put("title", "Tidy your room");
        payload.put("completed", false);
        System.out.println("payload = " + payload);

        //Send the request and get the response
        Response response = given(spec).body(payload).post("{first}");
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(201)
                .body("userId", equalTo(payload.get("userId")),
                        "title", equalTo(payload.get("title")),
                        "completed", equalTo(payload.get("completed")));


    }


}
