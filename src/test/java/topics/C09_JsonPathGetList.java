package topics;

import base_urls.BookStoreBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C09_JsonPathGetList extends BookStoreBaseUrl {
    /*
    Given
        https://bookstore.demoqa.com/BookStore/v1/Books
    When
        User sends a GET request to the URL
    Then
        The HTTP status code should be 200
    And
        The content type must be JSON
    And
        The first book's title must be 'Git Pocket Guide'
    And
        The total page count of books must be 2542
    */

    @Test
    public void jsonPathTest() {

        //Set the url
        spec.pathParams("first", "Books");

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();

        //Do assertion
        int statusCode = response.statusCode();
        String contentType = response.contentType();
        assertEquals(statusCode, 200);
        assertEquals(contentType, "application/json; charset=utf-8");

        //The first book's title must be 'Git Pocket Guide'
        JsonPath jsonPath = response.jsonPath();
        String firstTitle = jsonPath.getString("books[0].title");
        System.out.println("firstTitle = " + firstTitle);
        assertEquals(firstTitle, "Git Pocket Guide");

        //The total page count of books must be 2542
        List<Integer> pages = jsonPath.getList("books.pages");
        System.out.println("pages = " + pages);
        int sum = 0;
        for (int w : pages) {
            sum += w;
        }
        System.out.println("sum = " + sum);

        assertEquals(sum, 2542);
    }

}
