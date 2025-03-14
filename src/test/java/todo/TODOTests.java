package todo;

import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

public class TODOTests {
  TODOApiClient client = new TODOApiClient();
  Faker faker = new Faker();

  @Test
  void registerTest() {
    String accessToken = client.register(faker.internet().emailAddress(), faker.internet().password());
    System.out.println(accessToken);
  }
}
