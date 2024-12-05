package topics;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.gorest_pojos.DataItem;
import pojos.gorest_pojos.GoRestPojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C27_PojoList extends GoRestBaseUrl {
    /*
        Given https://gorest.co.in/public/v1/users
        When the user sends a GET request
        Then the HTTP status code should be 200
        And the last user should be as follows:
        {
            "id": 7538720,
            "name": "Prema Khatri II",
            "email": "khatri_ii_prema@haley.test",
            "gender": "male",
            "status": "inactive"
        }
    */
    @Test
    void pojoListTest() {

        //Set the url
        spec.pathParams("first", "users");

        //Set the expected data
        String json = """
                    {
                        "id": 7538720,
                        "name": "Prema Khatri II",
                        "email": "khatri_ii_prema@haley.test",
                        "gender": "male",
                        "status": "inactive"
                    }
                """;

        DataItem expectedData = ObjectMapperUtils.convertJsonToJava(json, DataItem.class);
        System.out.println("expectedData = " + expectedData);


        //Send the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();

        //Do assertion
        DataItem actualData = response.as(GoRestPojo.class).getData().getLast();
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.getName(), expectedData.getName());
        assertEquals(actualData.getEmail(), expectedData.getEmail());
        assertEquals(actualData.getGender(), expectedData.getGender());
        assertEquals(actualData.getId(), expectedData.getId());
        assertEquals(actualData.getStatus(), expectedData.getStatus());

    }


}
