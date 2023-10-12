package in.reqres.tests;

import in.reqres.models.UpdateUserBodyModel;
import in.reqres.models.UpdateUserResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static in.reqres.specs.UpdateUserSpec.updateUserRequestSpec;
import static in.reqres.specs.UpdateUserSpec.updateUserResponseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UpdateUserTest extends TestBase {
    @Test
    @DisplayName("Проверка обновления пользователя")
    void successUpdateUserTest() {
        UpdateUserBodyModel updateUserData = new UpdateUserBodyModel();
        updateUserData.setName("morpheus");
        updateUserData.setJob("zion resident");

        UpdateUserResponseModel response = step("Обновление имени и должности юзера", ()->
                given(updateUserRequestSpec)
                .body(updateUserData)
                .when()
                .put("/users/2")
                .then()
                .spec(updateUserResponseSpec)
                .extract().as(UpdateUserResponseModel.class));

        step("Проверка ответа об успешном обновлении юзера", () -> {
            assertEquals("morpheus", response.getName());
            assertEquals("zion resident", response.getJob());
            assertNotNull(response.getUpdatedAt());
        });

    }

    @Test
    @DisplayName("Проверка частичного обновления юзера")
    void updateUserWithPatchMethodTest() {

        UpdateUserBodyModel updateUserData = new UpdateUserBodyModel();
        updateUserData.setName("Dark");
        updateUserData.setJob("zion resident");

        UpdateUserResponseModel response = step("Обновление имени юзера", () ->
                given(updateUserRequestSpec)
                        .body(updateUserData)
                        .when()
                        .patch("/users/2")
                        .then()
                        .spec(updateUserResponseSpec)
                        .extract().as(UpdateUserResponseModel.class));

        step("Проверка ответа об успешном обновлении имени юзера", () -> {
            assertEquals("Dark", response.getName());
            assertEquals("zion resident", response.getJob());
            assertNotNull(response.getUpdatedAt());
        });
    }
}
