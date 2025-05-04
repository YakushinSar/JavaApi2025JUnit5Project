package lesson12;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class SelenideTests {
    //селенид это надстройка над селениумом, не надо объявлять драйвер, ставить ожидания, писать путь к локатору - все идет из коробки Селенида
    @Test
    void openHomePageTest(){
        Selenide.open("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");

        Assertions.assertEquals("Hands-On Selenium WebDriver with Java",Selenide.title());
        Assertions.assertEquals("https://bonigarcia.dev/selenium-webdriver-java/web-form.html",WebDriverRunner.url());
    }

    @Test
    void successfulLoginTest() {
        Selenide.open("https://bonigarcia.dev/selenium-webdriver-java/login-form.html");

        SelenideElement subTitle = $(By.className("display-6"));
        //можно сохранять как SelenideElement и WebElement, разница в том что в SelenideElement методов больше
        WebElement loginInput = $(By.cssSelector("#username"));
        //cssSelector можно явно не указывать, подставляется автоматически
        WebElement passwordInput = $("#password");
        WebElement submitButton = $(By.xpath("//button[@type='submit']"));

        loginInput.sendKeys("user");
        passwordInput.sendKeys("user");
        String textBeforeClick = subTitle.getText();
        submitButton.click();

        //assertThat(textBeforeClick).isEqualTo("Login form");
        //можно заменить на
        subTitle.shouldHave(text("Login form"));
        subTitle.should(visible);
        WebElement successMessage = $("#success");
        assertThat(successMessage.isDisplayed()).isTrue();
    }

    @Test
    void openForm() {
        open("https://bonigarcia.dev/selenium-webdriver-java/");
        WebElement webFormButton = $(By.xpath("//div[@class = 'card-body']")).find(By.xpath(".//a[contains(@class, 'btn')]"));
        webFormButton.click();
        SelenideElement actualH1 = $(By.xpath("//h1[@class='display-6']"));
        actualH1.shouldHave(text("Web form"));
    }

    @Test
    void loadingImagesWithUpdatedTimeoutWaitTest() {
        open("https://bonigarcia.dev/selenium-webdriver-java/loading-images.html");
        //если не хватает таймаута 4 сек, то можно увеличить
        Configuration.timeout = 10_000;
        $("#landscape").shouldHave(attributeMatching("src", ".*landscape.*"));
    }

    @Test
    void loadingImagesWithExplicitTimeoutWaitTest() {
        open("https://bonigarcia.dev/selenium-webdriver-java/loading-images.html");
//ждем когда ВСЕ элементы будут видимы
        ElementsCollection images = $$("img").filter(visible);
        //тут ждем появления четырех элементов за 10 секунд
        images.shouldHave(size(4), Duration.ofSeconds(10));
    }

    /* для POM
    @Test
    void pageObjectTest() {
        HomePage homePage = new HomePage();
        homePage.open();
        WebFormPage webFormPage = homePage.openWebForm();
        webFormPage.submit();

        Assertions.assertThat(url()).contains("https://bonigarcia.dev/selenium-webdriver-java/submitted-form.html");
    }

     */

}
