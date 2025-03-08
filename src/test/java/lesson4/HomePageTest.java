package lesson4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePageTest {
    //        выносим драйвер для видимости в классе
    WebDriver driver;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";

    @BeforeEach
    void setUp(){
        driver = new ChromeDriver();
//        WebDriver driver2 = new SafariDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown(){
//        метод постусловие для закрытия браузера после каждого теста
        driver.quit();
//        закрывает весь браузер
//        или можно использовать
//        закрывает текущее окно или вкладку
//        driver.close();
    }

    @Test
    void openHomePageTest(){
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        driver.manage().window().maximize();
        String actualTitle = driver.getTitle();
/*
import static org.junit.jupiter.api.Assertions.assertEquals можно сделать чтобы не писать Assertions.assertEquals,
а использовать assertEquals(expected, actual)
*/
        assertEquals("Hands-On Selenium WebDriver with Java",actualTitle);
/*
       если проверка на определенном шаге упадет, то последующие шаки НЕ выполнятся!!!
        поэтому нужно делать отдельные методы для пред- и постусловий
                */
    }

    @Test
    void openWebFormTest(){
//        driver.get(BASE_URL); уже не нужно так как вынесли в setUp
//        driver.manage().window().maximize(); уже не нужно так как вынесли в setUp
        String webFormUrl = "web-form.html";

        WebElement webFormLink = driver.findElement(By.linkText("Web form"));
        webFormLink.click();
        String currentUrl = driver.getCurrentUrl();

        WebElement webFormTitle = driver.findElement(By.className("display-6"));

        assertEquals(BASE_URL + webFormUrl,currentUrl);
        assertEquals("Web form",webFormTitle.getText());
    }

    @Test
    void chapterXpathTest(){
        String webFormUrl = "web-form.html";

        driver.findElement(By.xpath("//h5[text() = 'Chapter 3. WebDriver Fundamentals']/../a[contains(@href, 'web-form')]")).click();
        String currentUrl = driver.getCurrentUrl();
        WebElement title = driver.findElement(By.className("display-6"));

        assertEquals(BASE_URL + webFormUrl, currentUrl);
        assertEquals("Web form", title.getText());
    }
}
