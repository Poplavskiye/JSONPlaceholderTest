package Helpers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostMethods {
    private Integer userId;
    private String title;
    private String body;
    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(AllureLogger.class));

    public PostMethods(Integer userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public void sendNewPost() {
        Map<String, String> request = new HashMap<>();
        request.put("userId", userId.toString());
        request.put("title", title);
        request.put("body", body);
        LOG.info("Создание нового поста");
        given().contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(baseURI + "/posts")
                .then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .body("userId", equalTo(userId.toString()))
                .statusLine("HTTP/1.1 201 Created");
    }
}
