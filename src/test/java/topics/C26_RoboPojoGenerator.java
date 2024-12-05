package topics;

import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.PetPojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C26_RoboPojoGenerator extends PetStoreBaseUrl {
    /*
        Task: Validate a successful POST request to add a pet to the store.

        Given:
            URL: https://petstore.swagger.io/v2/pet
        And:
            Payload with pet details.

        When:
            I send a POST request to the given URL with the payload.

        Then:
            The HTTP Status Code should be 200.

        And:
            The response body should match the sent payload.

    /*
    Payload:
            {
            "id": 101,
                "category": {
                    "id": 1,
                    "name": "Dog"
                },
            "name": "Buddy",
                "photoUrls": [
            "buddy_photo1.com",
                    "buddy_photo2.com"
            ],
            "tags": [
            {
                "id": 101,
                    "name": "friendly"
            },
            {
                "id": 102,
                    "name": "energetic"
            }
            ],
            "status": "adopted"
        }
     */
    @Test
    public void createPetTest() {

        //Set the url
        spec.pathParams("first", "pet");

        //Set the expected data
        String json = """
                {
                  "id": 101,
                  "category": {
                    "id": 1,
                    "name": "Dog"
                  },
                  "name": "Buddy",
                  "photoUrls": [
                    "buddy_photo1.com",
                    "buddy_photo2.com"
                  ],
                  "tags": [
                    {
                      "id": 101,
                      "name": "friendly"
                    },
                    {
                      "id": 102,
                      "name": "energetic"
                    }
                  ],
                  "status": "adopted"
                }
                """;

        PetPojo expectedData = ObjectMapperUtils.convertJsonToJava(json, PetPojo.class);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        //Do Assertion
        PetPojo actualData = response.as(PetPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.getId(), expectedData.getId());
        assertEquals(actualData.getCategory().getId(), expectedData.getCategory().getId());
        assertEquals(actualData.getCategory().getName(), expectedData.getCategory().getName());
        assertEquals(actualData.getName(), expectedData.getName());
        assertEquals(actualData.getPhotoUrls().getFirst(), expectedData.getPhotoUrls().getFirst());
        assertEquals(actualData.getPhotoUrls().getLast(), expectedData.getPhotoUrls().getLast());
        assertEquals(actualData.getTags().getFirst().getId(), expectedData.getTags().getFirst().getId());
        assertEquals(actualData.getTags().getFirst().getName(), expectedData.getTags().getFirst().getName());
        assertEquals(actualData.getTags().getLast().getId(), expectedData.getTags().getLast().getId());
        assertEquals(actualData.getTags().getLast().getName(), expectedData.getTags().getLast().getName());
        assertEquals(actualData.getStatus(), expectedData.getStatus());
    }

}
