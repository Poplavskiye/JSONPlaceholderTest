package com.example.kiontesttask;

import Helpers.AllureLogger;
import Requests.GetMethods;
import Requests.PostMethods;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import org.slf4j.LoggerFactory;

import java.util.List;

public class JsonPlaceholderTest {
    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(AllureLogger.class));

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    @DisplayName("Получение списка POST запросов")
    public void getListOfPosts() {
        GetMethods getMethods = new GetMethods("/posts");


        List<String> allPosts = getMethods.getAllPosts();

        LOG.info("Вывод названий полученных постов");
        for (String post : allPosts) {
            LOG.info("Получен Пост: " + post);
        }
        Assertions.assertTrue(allPosts.size() > 0, "Нет ни одного поста в полученном от системы списке");

    }

    @Test
    @DisplayName("Добавление новой записи POST")
    public void addNewPost() {
        PostMethods postMethods = new PostMethods(11, "New Post", "New created post makes my life better");
        postMethods.sendNewPost();
    }
}

