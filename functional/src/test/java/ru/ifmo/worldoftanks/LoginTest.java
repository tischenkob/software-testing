package ru.ifmo.worldoftanks;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ifmo.AbstractUiTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends AbstractUiTest {

    @Test
    public void login() throws InterruptedException {

        var mainPage = new MainPage(getDriver());

        waitUntilClickable(mainPage.getEnterLink());
        var loginPage = mainPage.clickEnter();

        waitUntilClickable(loginPage.getEmailField());
        loginPage.enterWith("eapxtyrbpfozcfpycq@kiabws.com", "rh48vhcZXnAiYRy");

        waitUntilClickable(mainPage.getProfileLink());
        assertTrue(mainPage.isAuthenticated());
    }
}
