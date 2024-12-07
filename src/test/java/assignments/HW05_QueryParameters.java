package assignments;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

public class HW05_QueryParameters {
    /*
            Homework 05:
        Create a Rest Assured test script to perform the following operations sequentially using the API documentation at:
        https://petstore.swagger.io/
        1. Send a GET request to retrieve pets by status:
        o Endpoint: /pet/findByStatus
        o Query Parameter: status=sold
        2. Parse and verify the response:
        o Validate the status code.
        o Confirm that the response contains pets with the status "sold."
        3. Log the details:
        o Output the pet IDs and names from the response.
    */
    @Test
    public void queryParamTest() {

        Response response = given().get("https://petstore.swagger.io/v2/pet/findByStatus?status=sold");
        response.prettyPrint();
        response.then().statusCode(200).body("status", hasSize(greaterThan(0)));


    }


}
