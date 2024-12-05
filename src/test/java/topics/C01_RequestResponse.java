package topics;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class C01_RequestResponse {

    public static void main(String[] args) {

        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking");
        //response.prettyPrint();

        int statusCode = response.statusCode();

        System.out.println("statusCode = " + statusCode);

        System.out.println(response.headers());

        String connection = response.header("Connection");
        System.err.println("connection = " + connection);

        String server = response.header("Server");
        System.err.println("server = " + server);

        String contentType = response.contentType();
        System.err.println("contentType = " + contentType);

    }

}
