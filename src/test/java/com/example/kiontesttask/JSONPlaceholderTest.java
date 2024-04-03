package com.example.kiontesttask;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.util.List;

public class JSONPlaceholderTest {
    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(AllureLogger.class));
    RequestSpecification httpRequest = RestAssured.given();
    Response response = httpRequest.get("https://jsonplaceholder.typicode.com/posts");
    JsonPath jsonPathEvaluator = response.jsonPath();

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/posts";
    }
//TODO Добавить логирование по шагам в allure
    @Test
    @DisplayName("Получение списка POST запросов")
    public void getListOfPosts() throws MalformedURLException {

        LOG.info("Отправляем запрос на получение списка запросов");
        //given().contentType("application/json");
        List<PostList> allPostsList = jsonPathEvaluator.getList("posts", PostList.class);
        for (PostList PostList : allPostsList)
        {
            //LOG.info("Список постов запросов готов:" + PostList);
            System.out.println("Posts: " + PostList);
        }

    }
    @Test
    @DisplayName("Добавление новой записи POST")
    public void addNewPost() {

    }
}

