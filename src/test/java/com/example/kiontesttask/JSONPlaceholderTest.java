package com.example.kiontesttask;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;

import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class JSONPlaceholderTest {
    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(AllureLogger.class));
    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }
//TODO Добавить логирование по шагам в allure
    @Test
    @DisplayName("Получение списка POST запросов")
    public void getListOfPosts() throws MalformedURLException {
        LOG.info("Получение списка постов");
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/posts");

        JsonPath jsonPathEvaluator = new JsonPath(response.asString());

        List<String> allPosts = jsonPathEvaluator.getList("body");

        LOG.info("Вывод названия полученных постов");
        for(String post : allPosts)
        {
            System.out.println("post: " + post);
        }
    }
    @Test
    @DisplayName("Добавление новой записи POST")
    public void addNewPost() {
        PostNewPostPage postNewPostPage = new PostNewPostPage();
        postNewPostPage.PostNewPost(11, "New Post", "New created post makes my life better");
    }
    @Test
    public void postNewPost() {
        Integer userId = 11;
        String title = "Just title of new Post";
        String body = "New post makes my life better";

        Map<String, String> request = new HashMap<>();
        request.put("userId", userId.toString());
        request.put("title", title);
        request.put("body", body);
        given().contentType("application/json")
                .body(request)
                .when()
                .post(baseURI + "/posts")
                .then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .body("userId", equalTo(userId))
                .statusLine("HTTP/1.1 201 Created");

        LOG.info("Создание нового поста");

}   }

