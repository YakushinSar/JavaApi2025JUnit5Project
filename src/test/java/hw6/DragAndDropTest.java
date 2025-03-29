package hw6;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DragAndDropTest {
    private WebDriver driver;
    private static final String BASE_URL =
            "https://bonigarcia.dev/selenium-webdriver-java/drag-and-drop.html";

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @AfterEach
    void cleanUp() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void dragAndDropTest() throws InterruptedException {
        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("target"));
        Point initialPosition = draggable.getLocation();
        System.out.println("initialPosition" + initialPosition);

        new Actions(driver)
                .dragAndDrop(draggable, target)
                .perform();

        Thread.sleep(1000);

        Point finalPosition = draggable.getLocation();
        System.out.println("finalPosition" + finalPosition);
        System.out.println("targetPosition" + target.getLocation());
        Assertions.assertNotEquals(initialPosition, finalPosition, "Элемент не переместился!");

    }
}
