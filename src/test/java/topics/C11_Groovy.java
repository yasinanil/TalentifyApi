package topics;

import base_urls.BookStoreBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C11_Groovy extends BookStoreBaseUrl {
    /*
        Given
            https://bookstore.demoqa.com/BookStore/v1/Books
        When
             I send a GET request to the URL
        Then
             1) The status code is 200
             2) Print the publisher of "You Don't Know JS" and assert that its pages are 278
             3) The author of the book with the fewest pages is 'Richard E. Silverman'
             4) Print the book titles that have more than 300 pages and verify that their number is 3
    */
    @Test
    public void groovyTest() {

        //Set the url
        spec.pathParams("first", "Books");

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();

        //Do assertion
//        1) The status code is 200
        assertEquals(response.statusCode(), 200);

//        2) Print the publisher of "You Don't Know JS" and assert that its pages are 278
        JsonPath jsonPath = response.jsonPath();
        Object publisher = jsonPath.getList("books.findAll{it.title==\"You Don't Know JS\"}.publisher").getFirst();
        System.out.println("publisher = " + publisher);
        Object pagesOfJS = jsonPath.getList("books.findAll{it.title==\"You Don't Know JS\"}.pages").getFirst();
        assertEquals(pagesOfJS, 278);

//        3) The author of the book with the fewest pages is 'Richard E. Silverman'
        List<Integer> pages = jsonPath.getList("books.pages");
        System.out.println("pages = " + pages);
        pages.sort(Comparator.naturalOrder());
        System.out.println("pages = " + pages);
        int fewestPage = pages.getFirst();
        Object author = jsonPath.getList("books.findAll{it.pages==" + fewestPage + "}.author").getFirst();
        assertEquals(author, "Richard E. Silverman");

//        4) Print the book titles that have more than 300 pages and verify that their number is 3
        List<String> titles = jsonPath.getList("books.findAll{it.pages>300}.title");
        System.out.println("titles = " + titles);
        assertEquals(titles.size(), 3);

    }
}
