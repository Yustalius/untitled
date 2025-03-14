import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class SelenideTest {

    @BeforeAll
    static void setUp() {
      Configuration.pageLoadTimeout = 100000;
      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setCapability("enableVNC", true);
      capabilities.setCapability("enableVideo", true);

      Configuration.browserCapabilities = capabilities;
      Configuration.browserSize = "1920x1080";
      Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }

  @Test
  void shouldFindSelenideTest() {
    open("https://demowebshop.tricentis.com/");
    $(".ico-login").click();
    $("div.page-title").shouldHave(text("Welcome, Please Sign In!"));
    $("[name=Email]").setValue("008778909876789.876678hkh@gmail.com");
    $("[name=Password]").setValue("123qweasdzxc");
    $(".login-button").click();
    $(".topic-html-content-header").shouldHave(text("Welcome to our store"));
    $("li.answer").$("[name=pollanswers-1]").click();
    $$("ul.top-menu li").get(1).click();
    $$("div.sub-category-grid div").first().click();
    $("div.page-title").shouldHave(text("Desktops"));
    $$("div.item-box").get(2).$(".button-2").click();
    $("div.product-name").shouldHave(text("Build your own expensive computer"));
    $("[id=add-to-cart-button-74]").click();
    $$(".header-links ul li").get(2).click();
    $(".page-title").shouldHave(text("Shopping cart"));
    $("#termsofservice").click();
    $("#checkout").click();
    $("#BillingNewAddress_Company").setValue("Joppaaaa");
    $("#BillingNewAddress_City").setValue("Рязань");
    $("#BillingNewAddress_Address1").setValue("Улица Пушкина Дом Калатушкина");
    $("#BillingNewAddress_ZipPostalCode").setValue("390535");
    $("#BillingNewAddress_PhoneNumber").setValue("+7-930-875-03-95");
  }
}
