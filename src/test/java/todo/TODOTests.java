package todo;

import io.restassured.response.Response;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

public class TODOTests {
  TODOApiClient client = new TODOApiClient();
  Faker faker = new Faker();

  @Test
  void registerTest() {
    Response response = client.register(faker.internet().emailAddress(), faker.internet().password());
    System.out.println(String.valueOf(response.path("accessToken")));
  }

  @Test
  void loginTest() {
    String email = faker.internet().emailAddress();
    String password = faker.internet().password();

    client.register(email, password);
    Response login = client.login(email, password);
    System.out.println((String) login.jsonPath().get("accessToken"));
  }
}
