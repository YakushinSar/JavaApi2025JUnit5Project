package hw4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
Откройте страницу https://bonigarcia.dev/selenium-webdriver-java/
Проверьте, что все ссылки для Chapter 3-9 доступны и при нажатии открывается соответствующая страница
Проверьте заголовок страницы и url
Проверьте, что все ссылки принадлежат определенному Chapter, например, WebForm в Chapter 3. WebDriver Fundamentals
 */

class BonigarciaTest {
    private static final String URL = "https://bonigarcia.dev/selenium-webdriver-java/";
    private static final String FORM_TITLE = "Hands-On Selenium WebDriver with Java";
    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().setSize(new Dimension(1920, 1080));
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }

    @Test
    void openPracticeSiteTest(){
        WebElement title = driver.findElement(By.xpath("//*[@class='display-4']"));
        String actualTitile = driver.getTitle();

        assertEquals("Hands-On Selenium WebDriver with Java", title.getText());
        assertEquals(driver.getCurrentUrl(),URL);
        assertEquals("Hands-On Selenium WebDriver with Java",actualTitile);
    }

    @Test
    void ChapterWebdriverTest() {
        String webFormUrl = "web-form.html";

        driver.findElement(By.xpath("//a[text()='Web form']"))
                .click();
        String currentUrl = driver.getCurrentUrl();

        WebElement webFormTitle = driver.findElement(By.xpath("//h1[text()='Web form']"));

        assertEquals(URL + webFormUrl,currentUrl);
        assertEquals("Web form",webFormTitle.getText());
    }

    @Test
    void ChapterAgnosticTest(){
        String currentUrl = "long-page.html";
        String currentTitlePage = "This is a long page";

        WebElement longPage = driver.findElement(By.xpath("//a[text()='Long page']"));
        longPage.click();

        WebElement webFormTitle = driver.findElement(By.className("display-6"));

        assertEquals(URL + currentUrl, driver.getCurrentUrl());
        assertEquals(currentTitlePage,webFormTitle.getText());
        assertEquals("Hands-On Selenium WebDriver with Java", driver.getTitle());
    }

    @Test
    void ChapterSpecificTest(){
        String currentUrl = "geolocation.html";
        String currentTitlePage = "Geolocation";

        driver.findElement(By.xpath("//*[text()='Geolocation']")).click();
        WebElement webFormTitle = driver.findElement(By.xpath("//h1[text()='Geolocation']"));

        assertEquals(URL + currentUrl, driver.getCurrentUrl());
        assertEquals("Hands-On Selenium WebDriver with Java", driver.getTitle());
        assertEquals(currentTitlePage, webFormTitle.getText());
    }

    @Test
    void ChapterObjectTest(){
        String currentUrl = "login-form.html";
        String currentTitlePage = "Login form";

        driver.findElement(By.xpath("//*[text()='Login form']")).click();
        WebElement loginFormText = driver.findElement(By.xpath("//*[text()='Login form']"));

        assertEquals(URL + currentUrl, driver.getCurrentUrl());
        assertEquals(FORM_TITLE, driver.getTitle());
        assertEquals(currentTitlePage,loginFormText.getText() );
    }

    @Test
    void ChapterFrameworkTest() {
        String currentUrl = "random-calculator.html";
        driver.findElement(By.xpath("//a[text()='Random calculator']")).click();

        WebElement calculatorTitle = driver.findElement(By.xpath("//h1[@class='display-6']"));

        assertEquals(FORM_TITLE, driver.getTitle());
        assertEquals(URL + currentUrl, driver.getCurrentUrl());
        assertEquals("Random calculator", calculatorTitle.getText());
    }

}
