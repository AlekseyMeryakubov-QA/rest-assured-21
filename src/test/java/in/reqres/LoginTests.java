package in.reqres;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class LoginTests extends TestBase {

    @Test
    void successfulLoginTest() {
        String authData = "{\"email\": \"eve.holt@reqres.in\",\"password\": \"cityslicka\"}";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(authData)
                .when()
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void negative400Test() {
        String authData = "{\"email\": \"eve.holt@reqres.in\",\"password\": \"cityslicka\"}";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .body(authData)
                .when()
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }

    @Test
    void negative415Test() {

        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(415);
    }

    @Test
    void missingPasswordTest() {
        String authData = "{\"email\": \"eve.holt@reqres.in\",\"password\": \"\"}";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(authData)
                .when()
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void missingEmailTest() {
        String authData = "{\"email\": \"\",\"password\": \"cityslicka\"}";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(authData)
                .when()
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }
}
