package qumu.api;

import com.google.gson.Gson;
import io.restassured.response.Response;
import qumu.utils.ApiUtils;
import qumu.utils.LoadProp;
import qumu.utils.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class ApiClient {

    public static Response response;

    private int declaredTotal;
    private final List<Integer> collectedIds = new ArrayList<>();
    private static final Gson gson = new Gson();

    // Fetch page 1 and record the API-declared total user count
    public void fetchUserListAndNoteTotalCount() {
        Log.logger.info("Fetching user list page 1");
        response = given().spec(ApiUtils.baseRequest()).get("/users?page=1");
        Log.logger.info("Response status: " + response.getStatusCode());
        declaredTotal = response.jsonPath().getInt("total");
        collectedIds.addAll(response.jsonPath().getList("data.id"));
    }

    // Paginate through all remaining pages and collect every user ID
    public void collectUsersAcrossAllPages() {
        int totalPages = response.jsonPath().getInt("total_pages");
        for (int page = 2; page <= totalPages; page++) {
            Log.logger.info("Fetching user list page " + page);
            Response pageResponse = given().spec(ApiUtils.baseRequest()).get("/users?page=" + page);
            Log.logger.info("Response status: " + pageResponse.getStatusCode());
            collectedIds.addAll(pageResponse.jsonPath().getList("data.id"));
        }
    }

    public boolean collectedCountMatchesDeclaredTotal() {
        Log.logger.info("Collected IDs: " + collectedIds.size() + " | API declared total: " + declaredTotal);
        return collectedIds.size() == declaredTotal;
    }

    // Get single user by ID
    public void getSingleUser(int userId) {
        Log.logger.info("Fetching user: " + userId);
        response = given().spec(ApiUtils.baseRequest()).get("/users/" + userId);
        Log.logger.info("Response status: " + response.getStatusCode());
    }

    // Create a user
    public void createUser(String name, String job) {
        Log.logger.info("Creating user: " + name);
        Map<String, String> body = new HashMap<>();
        body.put("name", name);
        body.put("job", job);
        response = given().spec(ApiUtils.baseRequest()).body(gson.toJson(body)).post("/users");
        Log.logger.info("Response status: " + response.getStatusCode());
    }

    // Login with valid credentials from config.properties
    public void loginWithValidCredentials() {
        Log.logger.info("Executing login API with valid credentials");
        loginUser(LoadProp.getProperty("apiEmail"), LoadProp.getProperty("apiPassword"));
    }

    // Login without a password to trigger the 400 error scenario
    public void loginWithMissingPassword() {
        Log.logger.info("Executing login API with missing password");
        loginUser(LoadProp.getProperty("apiEmail"), null);
    }

    // Delayed response
    public void delayedResponse() {
        Log.logger.info("Calling delayed response API");
        response = given().spec(ApiUtils.baseRequest()).get("/users?delay=3");
        Log.logger.info("Response status: " + response.getStatusCode());
    }

    // Verify all IDs in the current response are unique
    public boolean verifyUniqueIds() {
        List<Integer> ids = response.jsonPath().getList("data.id");
        Set<Integer> uniqueIds = new HashSet<>(ids);
        return ids.size() == uniqueIds.size();
    }

    private void loginUser(String email, String password) {
        Map<String, String> body = new HashMap<>();
        body.put("email", email);
        if (password != null && !password.isEmpty()) {
            body.put("password", password);
        }
        response = given().spec(ApiUtils.baseRequest()).body(gson.toJson(body)).post("/login");
        Log.logger.info("Response status: " + response.getStatusCode());
    }
}
