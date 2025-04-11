package stat.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.sleep;

public class EmployeesListPage {
  private final SelenideElement
      addButton = $(byText("Добавить")),
      editButton = $(byText("Изменить")),
      deleteButton = $(byText("Удалить")),
      confirmButton = $(byText("ОК")),
      lastNameHeader = $(byText("Фамилия"));

  @Step("Открыть страницу сотрудников")
  public void openEmployeesPage() {
    $(byText("Сотрудники")).click();
  }

  @Step("Нажать Добавить")
  public EmployeeInfoPage addEmployee() {
    addButton.click();

    return new EmployeeInfoPage();
  }

  @Step("Нажать Изменить")
  public EmployeeInfoPage editEmployee() {
    editButton.click();

    return new EmployeeInfoPage();
  }

  @Step("Нажать Удалить")
  public EmployeesListPage deleteEmployee() {
    deleteButton.click();

    return this;
  }

  @Step("Нажать Удалить")
  public EmployeesListPage confirmDeletion() {
    confirmButton.click();

    return this;
  }

  @Step("Нажать Удалить")
  public EmployeesListPage assertEmployeeNotExists(String email) {
    $(byText(email)).shouldNot(exist);

    return this;
  }

  @Step("Нажать на Фамилия для сортировки")
  public EmployeesListPage sortByLastName() {
    lastNameHeader.click();

    return this;
  }

  @Step("Нажать на сотрудника с email = {email}")
  public EmployeesListPage selectEmployee(String email) {
    $(byText(email)).click();

    return this;
  }

  @Step("Получить данные сотрудника из таблицы")
  public List<String> getEmployeeDataFromTable(String email) {
    sleep(500);
    ElementsCollection tds = $(byText(email)).ancestor("tr").$$("td");
    return tds.stream()
        .map(td -> td.$("div").text())
        .toList();
  }
} 