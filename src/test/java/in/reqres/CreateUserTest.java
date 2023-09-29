package in.reqres;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.is;

public class CreateUserTest extends TestBase {
    @Test
    void successCreateUserTest() {
        String createData = "{\"name\": \"morpheus\",\"job\": \"leader\"}";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(createData)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"))
                .body("id", is(not(empty())))
                .body("createdAt", is(not(empty())));

    }

    @Test
    void createUserWithoutNameTest() {
        String createData = "{\"name\": \"\",\"job\": \"leader\"}";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(createData)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("job", is("leader"))
                .body("id", is(not(empty())))
                .body("createdAt", is(not(empty())));

    }

    @Test
    void createUserWithoutJobTest() {
        String noneJobData = "{ \"name\": \"morpheus\" }";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(noneJobData)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"),
                        "id", is(not(empty())),
                        "createdAt", is(not(empty())));
    }
}
