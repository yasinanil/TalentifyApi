package topics;

import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C21_ObjectMapperMap extends JsonPlaceHolderBaseUrl {

/*
    Task:
    1. Send a POST request to the URL: https://jsonplaceholder.typicode.com/todos
    2. Use the following request body:
       {
           "userId": 55,
           "title": "Tidy your room",
           "completed": false
       }
    3. Validate the following:
       - The status code is 201.
       - The response body matches:
         {
             "userId": 55,
             "title": "Tidy your room",
             "completed": false,
             "id": 201
         }
*/
    @Test
    public void objectMapperTest() throws JsonProcessingException {

        //Set the url
        spec.pathParams("first", "todos");

        //Set the expected data
        String stringJson = """
                           {
                               "userId": 55,
                               "title": "Tidy your room",
                               "completed": false
                           }
                           """;

        Map expectedData = new ObjectMapper().readValue(stringJson, Map.class);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        //Do assertion
        Map actualData = new ObjectMapper().readValue(response.asString(), Map.class);
        System.out.println("actualData = " + actualData);
        assertEquals(response.statusCode(), 201);
        assertEquals(actualData.get("userId"), expectedData.get("userId"));
        assertEquals(actualData.get("title"), expectedData.get("title"));
        assertEquals(actualData.get("completed"), expectedData.get("completed"));


    }
}
