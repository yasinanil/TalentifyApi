package practices;

import base_urls.PetStoreBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.testng.Assert.assertEquals;

public class PetsStatus extends PetStoreBaseUrl {

    /*
    Test if there are more than 5 pets whose status is "sold" using this document:
    https://petstore.swagger.io/
     */

    @Test
    public void statusTest() {

        //Set the url
        //https://petstore.swagger.io/v2/pet/findByStatus?status=sold
        spec
                .pathParams("first", "pet", "second", "findByStatus")
                .queryParams("status", "sold");

        //Set the expected data


        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        //1st Way: Matcher
        response.then().statusCode(200).body("id", hasSize(greaterThan(5)));

        //2nd Way: JsonPath
        assertEquals(response.statusCode(), 200);
        JsonPath jsonPath = response.jsonPath();
        int sizeOfElements = jsonPath.getList("id").size();
        assert sizeOfElements > 5;

    }
}
