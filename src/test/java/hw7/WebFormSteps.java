package hw7;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebFormSteps {
    private static final int TIMEOUT_IN_SEC = 2000;
    
    public static void openLongPage(WebDriver driver) throws InterruptedException {
        WebElement longPageButton = driver.findElement(By.xpath("//a[@href = 'long-page.html']"));
        Thread.sleep(TIMEOUT_IN_SEC);
        longPageButton.click();
    }

    public static void openInfiniteScrollPage(WebDriver driver) throws InterruptedException {
        WebElement infiniteScrollButton = driver.findElement(By.xpath("//a[@href = 'infinite-scroll.html']"));
        Thread.sleep(TIMEOUT_IN_SEC);
        infiniteScrollButton.click();
    }

    public static void openShadowDomPage(WebDriver driver) throws InterruptedException {
        WebElement shadowDomButton = driver.findElement(By.xpath("//a[@href = 'shadow-dom.html']"));
        Thread.sleep(TIMEOUT_IN_SEC);
        shadowDomButton.click();
    }

    public static void openCookiesPage(WebDriver driver) throws InterruptedException {
        WebElement cookiesButton = driver.findElement(By.xpath("//a[@href = 'cookies.html']"));
        Thread.sleep(TIMEOUT_IN_SEC);
        cookiesButton.click();
    }

    public static void openFramesPage(WebDriver driver) throws InterruptedException {
        WebElement framesButton = driver.findElement(By.xpath("//a[@href = 'frames.html']"));
        Thread.sleep(TIMEOUT_IN_SEC);
        framesButton.click();
    }

    public static void openIFramesPage(WebDriver driver) throws InterruptedException {
        WebElement iFramesButton = driver.findElement(By.xpath("//a[@href = 'iframes.html']"));
        Thread.sleep(TIMEOUT_IN_SEC);
        iFramesButton.click();
    }

    public static void openDialogBoxesPage(WebDriver driver) throws InterruptedException {
        WebElement dialogBoxesButton = driver.findElement(By.xpath("//a[@href = 'dialog-boxes.html']"));
        Thread.sleep(TIMEOUT_IN_SEC);
        dialogBoxesButton.click();
    }

    public static void openWebStoragePage(WebDriver driver) throws InterruptedException {
        WebElement webStorageButton = driver.findElement(By.xpath("//a[@href = 'web-storage.html']"));
        Thread.sleep(TIMEOUT_IN_SEC);
        webStorageButton.click();
    }

    public static void openLoadingImagesPage(WebDriver driver) throws InterruptedException {
        WebElement LoadingImagesButton = driver.findElement(By.xpath("//a[@href = 'loading-images.html']"));
        Thread.sleep(TIMEOUT_IN_SEC);
        LoadingImagesButton.click();
    }

    public static void openSlowCalculatorPage(WebDriver driver) throws InterruptedException {
        WebElement SlowCalculatorButton = driver.findElement(By.xpath("//a[@href = 'slow-calculator.html']"));
        Thread.sleep(TIMEOUT_IN_SEC);
        SlowCalculatorButton.click();
    }
}
