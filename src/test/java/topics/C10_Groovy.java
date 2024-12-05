package topics;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C10_Groovy extends JsonPlaceHolderBaseUrl {
    /*
        Given
            https://jsonplaceholder.typicode.com/todos
        When
             I send GET Request to the URL
        Then
             1)Status code is 200
             2)Print all ids greater than 190 on the console
               Assert that there are 10 ids greater than 190
             3)Print all userIds whose ids are less than 5 on the console
               Assert that the number of userIds whose ids are less than 5 is 4
             4)Print all titles whose ids are greater than 195
               Assert that "quis eius est sint explicabo" is one of the titles whose id is less than 5
             5)Print id whose title is "quo adipisci enim quam ut ab"
               Assert that id is 8
    */
    @Test
    public void groovyTest() {
        //Set the url
        spec.pathParams("first", "todos");

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();

        //Do assertion
        //Status code is 200
        assertEquals(response.statusCode(), 200);

        // Print all ids greater than 190 on the console
        // Assert that there are 10 ids greater than 190
        JsonPath jsonPath = response.jsonPath();
        List<Integer> idList = jsonPath.getList("id");
        System.out.println("idList = " + idList);

        //Groovy:
        List<Integer> idsGreaterThan190 = jsonPath.getList("findAll{it.id>190}.id");
        System.out.println("idsGreaterThan190 = " + idsGreaterThan190);
        assertEquals(idsGreaterThan190.size(), 10);

//        3)Print all userIds whose ids are less than 5 on the console
//        Assert that the number of userIds whose ids are less than 5 is 4
        List<Integer> userIds = jsonPath.getList("findAll{it.id<5}.userId");
        System.out.println("userIds = " + userIds);
        assertEquals(userIds.size(), 4);

//        4)Print all titles whose ids are greater than 195
//        Assert that "quis eius est sint explicabo" is one of the titles whose id is less than 5
        List<String> titles = jsonPath.getList("findAll{it.id>195}.title");
        System.out.println("titles = " + titles);

        assert titles.contains("quis eius est sint explicabo");

//        5)Print id whose title is "quo adipisci enim quam ut ab"
//        Assert that id is 8
        Object id = jsonPath.getList("findAll{it.title=='quo adipisci enim quam ut ab'}.id").getFirst();
        System.out.println("id = " + id);
        assertEquals(id, 8);

    }
}
