package in.reqres.tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.empty;

public class UpdateUserTest extends TestBase {
    @Test
    void successUpdateUserTest() {
        String createData = "{\"name\": \"morpheus\",\"job\": \"zion resident\"}";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(createData)
                .when()
                .post("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"))
                .body("createdAt", is(not(empty())));
    }

    @Test
    void updateUserWithPatchMethodTest() {
        String createData = "{\"name\": \"Dark\",\"job\": \"zion resident\"}";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(createData)
                .when()
                .patch("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("Dark"))
                .body("job", is("zion resident"))
                .body("createdAt", is(not(empty())));
    }
}
