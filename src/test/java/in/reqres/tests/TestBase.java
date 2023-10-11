package in.reqres.tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static in.reqres.helpers.CustomAllureListener.withCustomTemplates;

public class TestBase {


    @BeforeAll
    public static void beforeAll() {
        RestAssured.filters(withCustomTemplates());

    }
}
