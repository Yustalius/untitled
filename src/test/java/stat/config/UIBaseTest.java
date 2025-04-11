package stat.config;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class UIBaseTest {
    @BeforeAll
    public static void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 20000;
        Configuration.reportsFolder = "target/selenide";
        Configuration.screenshots = true;
        Configuration.savePageSource = true;
        Configuration.fastSetValue = true;

        com.codeborne.selenide.logevents.SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void after() {
        closeWebDriver();
    }
} 