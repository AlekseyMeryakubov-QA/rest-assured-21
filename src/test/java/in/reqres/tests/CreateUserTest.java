package in.reqres.tests;

import in.reqres.models.CreateUserBodyModel;
import in.reqres.models.CreateUserResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static in.reqres.specs.CreateUserSpec.createUserRequestSpec;
import static in.reqres.specs.CreateUserSpec.createUserResponseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class CreateUserTest {
    @Test
    @DisplayName("Проверка создания Юзера")
    void successCreateUserTest() {
        CreateUserBodyModel createUserData = new CreateUserBodyModel();
        createUserData.setName("morpheus");
        createUserData.setJob("leader");

        CreateUserBodyModel response = step ("Cоздание Юзера с именем и должностью", () ->
                given(createUserRequestSpec)
                .body(createUserData)
                .when()
                .post("/users")
                .then()
                .spec(createUserResponseSpec)
                .extract().as(CreateUserBodyModel.class));

        step("Проверка создания Юзера", () -> {
            assertEquals("morpheus", response.getName());
            assertEquals("leader", response.getJob());
            assertNotNull(response.getId());
            assertNotNull(response.getCreatedAt());
        });
    }

    @Test
    @DisplayName("Проверка создания Юзера без имени")
    void createUserWithoutNameTest() {
        CreateUserBodyModel withoutNameData = new CreateUserBodyModel();
        withoutNameData.setJob("leader");


        CreateUserBodyModel response = step("Cоздание Юзера без имени", () ->
                given(createUserRequestSpec)
                        .body(withoutNameData)
                        .when()
                        .post("/users")
                        .then()
                        .spec(createUserResponseSpec)
                        .extract().as(CreateUserBodyModel.class));

        step("Проверка создания Юзера без имени", () -> {
            assertEquals("leader", response.getJob());
            assertNotNull(response.getId());
            assertNotNull(response.getCreatedAt());
        });
    }

    @Test
    @DisplayName("Проверка создания Юзера без должности")
    void createUserWithoutJobTest() {
        CreateUserBodyModel withoutJobData = new CreateUserBodyModel();
        withoutJobData.setName("morpheus");


        CreateUserBodyModel response = step("создание Юзера без имени", () ->
                given(createUserRequestSpec)
                        .body(withoutJobData)
                        .when()
                        .post("/users")
                        .then()
                        .spec(createUserResponseSpec)
                        .extract().as(CreateUserBodyModel.class));

        step("Проверка создания Юзера без имени", () -> {
            assertEquals("leader", response.getJob());
            assertNotNull(response.getId());
            assertNotNull(response.getCreatedAt());
        });
    }
}
