package assignments;

import base_urls.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class HW06_Jsonpath extends ReqresBaseUrl {

/*
        Homework 06:
    Create a Rest Assured test to perform the following:
    1. URL: https://reqres.in/api/unknown/3
    2. Steps:
    o Given: The URL.
    o When: A GET request is sent to the URL.
    o Then:
    ▪ Verify the HTTP status code is 200.
    ▪ Verify the response content type is "application/json; charset=utf-8".
    ▪ Use TestNG soft assertions to validate the response body matches the following structure:
    {
    "data": {
        "id": 3,
        "name": "true red",
        "year": 2002,
        "color": "#BF1932",
        "pantone_value": "19-1664"
        },
    "support": {
        "url": "https://reqres.in/#support-heading",
        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
    }
 */

    @Test
    public void jsonPathTest() {

        //Set the url
        spec.pathParams("first", "api", "second", "unknown", "third", "3");

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}/{third}");
        response.prettyPrint();

        //Do assertion
        response.then().statusCode(200).contentType(ContentType.JSON);
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.getInt("data.id");
        String name = jsonPath.getString("data.name");
        int year = jsonPath.getInt("data.year");
        String color = jsonPath.getString("data.color");
        String pantone_value = jsonPath.getString("data.pantone_value");
        String url = jsonPath.getString("support.url");
        String text = jsonPath.getString("support.text");

        assertEquals(id, 3);
        assertEquals(name, "true red");
        assertEquals(year, 2002);
        assertEquals(color, "#BF1932");
        assertEquals(pantone_value, "19-1664");
        assertEquals(url, "https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral");
        assertEquals(text, "Tired of writing endless social media content? Let Content Caddy generate it for you.");

    }

}
