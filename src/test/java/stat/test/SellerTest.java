package stat.test;

import helpers.config.configuration.Config;
import helpers.utils.testrail.CaseID;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import stat.jupiter.Employee;
import stat.model.EmployeeDTO;
import helpers.config.UIBaseTest;
import stat.pages.EmployeesListPage;
import stat.pages.LoginPage;

import java.util.List;

import static bpm.helpers.Credentials.getBillingPassword;
import static bpm.helpers.Credentials.getBillingUsername;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

@Execution(ExecutionMode.CONCURRENT)
public class SellerTest extends UIBaseTest {
  private static final Config CFG = Config.getInstance();

  public static final String
      LOGIN_URL = CFG.statUrl() + "/login.zul",
      SALARY_LIST_URL = CFG.statUrl() + "/salary/main.zul";

  @BeforeEach
  void before() {
    open(LOGIN_URL, LoginPage.class)
        .login(getBillingUsername(), getBillingPassword());
  }

  @Test
  @CaseID(8212820)
  @DisplayName("Создание сотрудника")
  void createSellerTest() {
    EmployeeDTO employee = EmployeeDTO.randomEmployee();

    List<String> employeeDataFromTable =
        open(SALARY_LIST_URL, EmployeesListPage.class)
            .addEmployee()
            .fillEmployeeData(employee)
            .save()
            .sortByLastName()
            .getEmployeeDataFromTable(employee.email());

    assertEmployeeData(employeeDataFromTable, employee);
  }

  @Test
  @Employee
  @CaseID(8212820)
  @DisplayName("Обновление данных сотрудника")
  void updateSellerTest(EmployeeDTO employee) {
    EmployeeDTO updatedEmployee = EmployeeDTO.randomEmployee();

    List<String> employeeDataFromTable =
        open(SALARY_LIST_URL, EmployeesListPage.class)
            .sortByLastName()
            .selectEmployee(employee.email())
            .editEmployee()
            .fillEmployeeData(updatedEmployee)
            .save()
            .sortByLastName()
            .getEmployeeDataFromTable(updatedEmployee.email());

    assertEmployeeData(employeeDataFromTable, updatedEmployee);
  }

  @Test
  @Employee
  @CaseID(8212820)
  @DisplayName("Удаление сотрудника")
  void deleteSellerTest(EmployeeDTO employee) {
    open(SALARY_LIST_URL, EmployeesListPage.class)
        .sortByLastName()
        .selectEmployee(employee.email())
        .deleteEmployee()
        .confirmDeletion()
        .sortByLastName()
        .sortByLastName()
        .assertEmployeeNotExists(employee.email());
  }

  @Step("Проверить, что данные {0} совпадают с данными {1}")
  private static void assertEmployeeData(List<String> employeeData, EmployeeDTO employee) {
    assertThat(employeeData)
        .hasSize(7)
        .containsExactly(
            employeeData.get(0), // ID
            employee.lastName(),
            employee.firstName(),
            employee.surname(),
            "Стажер",
            "Служебная",
            employee.email()
        );
  }
}
