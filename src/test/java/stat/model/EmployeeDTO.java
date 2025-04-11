package stat.model;

import net.datafaker.Faker;
import stat.jupiter.Employee;

import java.util.Locale;

public record EmployeeDTO(
    long id,
    String firstName,
    String lastName,
    String surname,
    String email,
    int typeId,
    int groupId
) {
  public EmployeeDTO(String firstName, String lastName, String surname, String email) {
    this(-1, firstName, lastName, surname, email, 4, -2); // id по умолчанию -1
  }

  public static EmployeeDTO randomEmployee() {
    Faker faker = new Faker(new Locale("ru"));

    return new EmployeeDTO(
        faker.name().firstName(),
        "." + faker.name().lastName(),
        faker.name().lastName(),
        faker.internet().emailAddress()
    );
  }
  
  /**
   * Создает объект EmployeeDTO из аннотации @Employee
   * 
   * @param anno аннотация @Employee
   * @return объект EmployeeDTO
   */
  public static EmployeeDTO fromAnno(Employee anno) {
    Faker faker = new Faker(new Locale("ru"));
    
    return new EmployeeDTO(
        -1, // id по умолчанию -1
        anno.firstName().isEmpty() ? faker.name().firstName() : anno.firstName(),
        anno.lastName().isEmpty() ? "." + faker.name().lastName() : anno.lastName(),
        anno.surname().isEmpty() ? faker.name().lastName() : anno.surname(),
        anno.email().isEmpty() ? faker.internet().emailAddress() : anno.email(),
        anno.sellerTypeId(),
        anno.sellerGroupId()
    );
  }

public EmployeeDTO withId(long id) {
    return new EmployeeDTO(id, this.firstName, this.lastName, this.surname, this.email, this.typeId, this.groupId);
}
}
