package stat.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class LoginPage {
  private final SelenideElement loginButton = $(byText("Войти"));
  private final ElementsCollection inputs = $$("input");

  @Step("Ввод имени пользователя")
  public LoginPage setUsername(String username) {
    inputs.get(0).setValue(username);
    
    return this;
  }

  @Step("Ввод пароля")
  public LoginPage setPassword(String password) {
    inputs.get(1).setValue(password);
    
    return this;
  }

  @Step("Нажатие кнопки входа")
  public EmployeesListPage clickLoginButton() {
    loginButton.click();

    return new EmployeesListPage();
  }

  @Step("Авторизация в системе")
  public void login(String username, String password) {
    setUsername(username);
    setPassword(password);
    clickLoginButton();
  }
} 