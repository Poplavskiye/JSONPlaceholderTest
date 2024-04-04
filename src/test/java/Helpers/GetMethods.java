package Helpers;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GetMethods {
    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(AllureLogger.class));
    private String Path;

    public GetMethods(String path) {
        Path = path;
    }

    public List<String> getAllPosts() {
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get(Path);

        JsonPath jsonPathEvaluator = new JsonPath(response.asString());
        return jsonPathEvaluator.getList("body");

    }
}
