package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Authentication {

    public static String generateToken() {

        String json = """
                {
                    "username" : "admin",
                    "password" : "password123"
                }""";

        Response response = given().body(json).contentType(ContentType.JSON).post("https://restful-booker.herokuapp.com/auth");
        return response.jsonPath().getString("token");
    }

}
