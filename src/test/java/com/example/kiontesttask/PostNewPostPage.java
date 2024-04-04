package com.example.kiontesttask;

import com.example.kiontesttask.AllureLogger;
import io.restassured.RestAssured;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostNewPostPage {
    private Integer currentUserId;
    private Integer currentId;
    private String currentTitle;
    private String currentBody;
    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(AllureLogger.class));


    public void PostNewPost(int userID, String title, String body) {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        currentUserId = userID;
        //currentId = id;
        currentTitle = title;
        currentBody = body;
        Map<String, String> request = new HashMap<>();
        request.put("userId", String.valueOf(currentUserId));
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
