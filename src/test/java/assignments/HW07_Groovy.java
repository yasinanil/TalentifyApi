package assignments;

import base_urls.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class HW07_Groovy extends ReqresBaseUrl {
/*
        Homework 07:
    Implement the following test steps using Rest Assured:
    1. Given
    Base URL: https://reqres.in/api/unknown/
    2. When
    Send a GET request to the URL.
    3. Then
    Perform the following validations and actions:
    o Status Code Validation:
    ▪ Verify that the status code of the response is 200.
    o Name:
    ▪ Verify that the name whose id equals 5 is "tigerlily"
    o Pantone Values:
    ▪ Print all pantone_values from the response to the console.
    o IDs Greater Than 3:
    ▪ Print all IDs greater than 3 to the console.
    ▪ Assert that there are exactly 3 IDs greater than 3.
    o Names with IDs Less Than 3:
    ▪ Print all names whose IDs are less than 3 to the console.
    ▪ Assert that there are exactly 2 names with IDs less than 3.
 */

    @Test
    public void groovyTest() {

        //Set the url
        spec.pathParams("first", "api", "second", "unknown");

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        response.then().statusCode(200);

        //Verify that the name whose id equals 5 is "tigerlily"
        JsonPath jsonPath = response.jsonPath();
        Object name = jsonPath.getList("data.findAll{it.id==5}.name").getFirst();
        assertEquals(name, "tigerlily");

        // ▪ Print all pantone_values from the response to the console.
        System.out.println(jsonPath.getList("data.pantone_value"));

        //    ▪ Print all IDs greater than 3 to the console.
        //    ▪ Assert that there are exactly 3 IDs greater than 3.
        List<Integer> ids = jsonPath.getList("data.findAll{it.id>3}.id");
        System.out.println(ids);
        assertEquals(ids.size(), 3);

        //    ▪ Print all names whose IDs are less than 3 to the console.
        //    ▪ Assert that there are exactly 2 names with IDs less than 3.
        List<String> names = jsonPath.getList("data.findAll{it.id<3}.name");
        System.out.println(names);
        assertEquals(names.size(), 2);

    }

}
