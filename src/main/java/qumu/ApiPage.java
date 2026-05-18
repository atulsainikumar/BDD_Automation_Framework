package qumu;

import io.restassured.response.Response;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static io.restassured.RestAssured.given;

public class ApiPage {

    public static Response response;

    public static final String BASE_URL = "https://reqres.in/api";

    public static final String API_KEY =
            "free_user_3DrQMIQEtAFzfTnE9rbvdTjzwIq";

    // Get users page 1

    public void getUsersPageOne() {
    	
    	Log.logger.info("Calling GET Users API");

        response = given().header("x-api-key", API_KEY).when().get(BASE_URL + "/users?page=1");

    }

    // Get all users

    public void getAllUsers() {

        response = given().header("x-api-key", API_KEY).when().get(BASE_URL + "/users?page=2");
        
        Log.logger.info("Response Code: " + response.getStatusCode());

    }

    // Get single user

    public void getSingleUser(int userId) {

        response = given().header("x-api-key", API_KEY).when().get(BASE_URL + "/users/" + userId);

    }

    // Create user

    public void createUser(String name, String job) {

    	Log.logger.info("Creating User: " + name);
        String body = "{\n" +
                "\"name\":\"" + name + "\",\n" +
                "\"job\":\"" + job + "\"\n" +
                "}";

        response = given().header("x-api-key", API_KEY).header("Content-Type", "application/json").body(body).when().post(BASE_URL + "/users");

    }

    // Login user

    public void loginUser(String email, String password) {
    	
    	Log.logger.info("Executing Login API");

        String body;

        if(password == null || password.isEmpty()) {

            body = "{\n" +
                    "\"email\":\"" + email + "\"\n" +
                    "}";

        } else {

            body = "{\n" +
                    "\"email\":\"" + email + "\",\n" +
                    "\"password\":\"" + password + "\"\n" +
                    "}";

        }

        response = given().header("x-api-key", API_KEY).header("Content-Type", "application/json").body(body).when().post(BASE_URL + "/login");

    }

    // Delayed response

    public void delayedResponse() {
    	
    	Log.logger.info("Calling Delayed Response API");

        response = given().header("x-api-key", API_KEY).when().get(BASE_URL + "/users?delay=3");

    }
    
    // Unique user IDs

    public boolean verifyUniqueIds() {

        List<Integer> ids = response.jsonPath().getList("data.id");

        Set<Integer> uniqueIds = new HashSet<>(ids);

        return ids.size() == uniqueIds.size();
    }

}