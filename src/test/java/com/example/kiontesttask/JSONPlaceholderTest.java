package com.example.kiontesttask;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;

import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.util.List;
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
        PostMethods postNewPostPage = new PostMethods(11, "New Post", "New created post makes my life better");
        postNewPostPage.SendNewPost();
    }
}

