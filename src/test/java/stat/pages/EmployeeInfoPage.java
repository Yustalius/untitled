package stat.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import stat.model.EmployeeDTO;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class EmployeeInfoPage {
    private final ElementsCollection inputs = $$("input");
    private final SelenideElement saveButton = $(byText("Сохранить"));

    @Step("Заполнение данных сотрудника")
    public EmployeeInfoPage fillEmployeeData(EmployeeDTO employee) {
        setLastName(employee.lastName());
        setFirstName(employee.firstName());
        setSurname(employee.surname());
        selectPosition("Стажер");
        selectDepartment("Служебная");
        setEmail(employee.email());
        
        return this;
    }

    @Step("Установить фамилию: {0}")
    private EmployeeInfoPage setLastName(String lastName) {
        inputs.get(1).setValue(lastName);

        return this;
    }

    @Step("Установить имя: {0}")
    private EmployeeInfoPage setFirstName(String firstName) {
        inputs.get(2).setValue(firstName);

        return this;
    }

    @Step("Установить отчество: {0}")
    private EmployeeInfoPage setSurname(String surname) {
        inputs.get(3).setValue(surname);

        return this;
    }

    @Step("Выбрать позицию: {0}")
    private EmployeeInfoPage selectPosition(String position) {
        inputs.get(4).click();
        $(byText(position)).click();

        return this;
    }

    @Step("Выбрать отдел: {0}")
    private EmployeeInfoPage selectDepartment(String department) {
        inputs.get(5).click();
        $(byText(department)).click();

        return this;
    }

    @Step("Установить email: {0}")
    private EmployeeInfoPage setEmail(String email) {
        inputs.get(6).setValue(email);

        return this;
    }

    @Step("Нажать кнопку Сохранить")
    public EmployeesListPage save() {
        saveButton.click();

        return new EmployeesListPage();
    }
} 