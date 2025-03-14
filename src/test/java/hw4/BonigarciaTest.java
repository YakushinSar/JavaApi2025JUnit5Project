package hw4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

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

    // Проверка, что загрузилось 6 Chapter
    @Test
    public void testAllChapter() {
        List<WebElement> list = driver.findElements(By.xpath("//div[@class='card-body']"));

        assertEquals(list.size(), 6);
    }

    // Проверка каждой ссылки в каждом Chapter
    @Test()
    public void testChapterAllLink() {
        List<WebElement> webElementList = driver.findElements(By.xpath("//div[@class='card-body']/a"));

        for (WebElement element : webElementList) {
            String link = element.getDomAttribute("href");
            String title = element.getText();

            // Title кнопки не соответствует Title страницы
            switch (link) {
                case "navigation1.html" -> title = "Navigation example";
                case "draw-in-canvas.html" -> title = "Drawing in canvas";
                case "long-page.html" -> title = "This is a long page";
                case "iframes.html" -> title = "IFrame";
                case "login-slow.html" -> title = "Slow login form";
            }
            element.click();

            String resultUrl = driver.getCurrentUrl();

            //Страницы без Title
            if (link.equals("frames.html") || link.equals("multilanguage.html")) {
                assertEquals(resultUrl, URL + link);
                driver.navigate().back();
                continue;
            }
            String resultTitle = driver.findElement(By.xpath("//h1[@class='display-6']")).getText();

            assertEquals(resultUrl, URL + link);
            assertEquals(resultTitle, title);

            driver.navigate().back();
        }
    }

    @ParameterizedTest
    @DisplayName("Проверка фреймов")
    @CsvSource ({
            "Chapter 4. Browser-Agnostic Features, frames.html, Frames"
    })
    void frameTests (String chapterName, String path, String title) {
        driver.findElement(By.xpath("//h5[text() = '" + chapterName + "']/../a[@href = '" + path + "']")).click();
        String actualUrl = driver.getCurrentUrl();
        WebElement frame = driver.findElement(By.cssSelector("frame[name='frame-header']"));
        driver.switchTo().frame(frame);
        String actualTitle = driver.findElement(By.className("display-6")).getText();
        assertEquals(URL + path, actualUrl, "The URLs don't match");
        assertEquals(title, actualTitle, "The titles don't match");
    }

    @Test
    void openAllLinksTest() throws InterruptedException {
        int qtyLinks = 0;
        List<WebElement> chapters = driver.findElements(By.cssSelector("h5.card-title"));
        for (WebElement chapter : chapters) {
            List<WebElement> links = chapter.findElements(By.xpath("./../a"));
            qtyLinks += links.size();
            System.out.println(chapter.getText());
            for (WebElement link : links) {
                System.out.println(link.getText());
                link.click();
                Thread.sleep(500);
                driver.navigate().back();
            }
        }
        assertEquals(6, chapters.size());
        assertEquals(27, qtyLinks);
    }
}
