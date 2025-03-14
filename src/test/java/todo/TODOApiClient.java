package todo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TODOApiClient {

  public String register(String email, String password) {
    String requestBody = "{"
        + "\"email\": \"" + email + "\","
        + "\"password\": \"" + password + "\""
        + "}";

    Response response = given()
        .contentType(ContentType.JSON)
        .body(requestBody)
        .when()
        .post("http://2.59.41.2:7320/api/auth/register")
        .then()
        .statusCode(201)
        .body("user.email", equalTo(email))
        .extract()
        .response();

    return response.path("accessToken");
  }
}
