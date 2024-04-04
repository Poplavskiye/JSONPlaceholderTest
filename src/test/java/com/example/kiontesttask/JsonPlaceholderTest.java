package com.example.kiontesttask;

import Helpers.AllureLogger;
import Helpers.GetMethods;
import Helpers.PostMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;

import org.slf4j.LoggerFactory;

import java.util.List;

public class JsonPlaceholderTest {
    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(AllureLogger.class));

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    //TODO Добавить логирование по шагам в allure
    @Test
    @DisplayName("Получение списка POST запросов")
    public void getListOfPosts() {
        GetMethods getMethods = new GetMethods("/posts");
        LOG.info("Получение списка постов");

        List<String> allPosts = getMethods.getAllPosts();

        LOG.info("Вывод названия полученных постов");
        for (String post : allPosts) {
            LOG.info("Получен Пост: " + post);
        }

    }

    @Test
    @DisplayName("Добавление новой записи POST")
    public void addNewPost() {
        PostMethods postMethods = new PostMethods(11, "New Post", "New created post makes my life better");
        postMethods.sendNewPost();
    }
}

