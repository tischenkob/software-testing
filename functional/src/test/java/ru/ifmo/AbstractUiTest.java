package ru.ifmo;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.util.concurrent.TimeUnit.SECONDS;

public abstract class AbstractUiTest {
    @Getter
    WebDriver driver;
    @Getter
    String url = "https://worldoftanks.ru/";

    private final static int TIMEOUT = 15;

    protected void waitUntilClickable(WebElement element) {
        new WebDriverWait(getDriver(), TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    @BeforeAll
    protected static void setup() {
        WebDriverManager.chromedriver().setup();
    }

    public void set(WebDriver instance) {
        driver = instance;
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
    }

    @BeforeEach
    public void setupTest() {
        driver = new ChromeDriver();
        driver.get(getUrl());
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
