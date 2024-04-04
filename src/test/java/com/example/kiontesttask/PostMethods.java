package com.example.kiontesttask;

import io.restassured.RestAssured;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostMethods {
    private Integer currentUserId;
    private String currentTitle;
    private String currentBody;
    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(AllureLogger.class));

    public PostMethods (Integer userId, String title, String body) {
        currentUserId = userId;
        currentTitle = title;
        currentBody = body;
    }
    public void SendNewPost() {
        Map<String, String> request = new HashMap<>();
        request.put("userId", currentUserId.toString());
        request.put("title", currentTitle);
        request.put("body", currentBody);
        given().contentType("application/json")
                .body(request)
                .when()
                .post(baseURI + "/posts")
                .then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .body("userId", equalTo(currentUserId.toString()))
                .statusLine("HTTP/1.1 201 Created");

        LOG.info("Создание нового поста");
    }
}
