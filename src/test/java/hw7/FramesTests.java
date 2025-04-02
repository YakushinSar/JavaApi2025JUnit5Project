package hw7;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static hw7.WebFormSteps.openFramesPage;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FramesTests {
    WebDriver driver;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";

    @BeforeEach
    void start() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        openFramesPage(driver);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    @Tag("positive")
    void FramesTest() {
        WebElement frameBodyElement = driver.findElement(By.name("frame-body"));
        driver.switchTo().frame(frameBodyElement);

        assertThat(driver.findElement(By.className("lead")).getText())
                .contains("Lorem ipsum dolor sit amet consectetur adipiscing");
    }
}
