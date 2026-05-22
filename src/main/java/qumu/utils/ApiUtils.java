package qumu.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ApiUtils {

    /**
     * Returns a base RequestSpecification pre-configured with the API base URI,
     * authentication header, and JSON content type.
     * All API methods should build on this to avoid repeating common setup.
     */
    public static RequestSpecification baseRequest() {
        return new RequestSpecBuilder()
                .setBaseUri(LoadProp.getProperty("apiBaseUrl"))
                .addHeader("x-api-key", LoadProp.getProperty("apiKey"))
                .setContentType(ContentType.JSON)
                .build();
    }
}
