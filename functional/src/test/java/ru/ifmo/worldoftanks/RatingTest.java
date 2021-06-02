package ru.ifmo.worldoftanks;

import org.junit.jupiter.api.Test;
import ru.ifmo.AbstractUiTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RatingTest extends AbstractUiTest {

    @Override
    public String getUrl() {
        return "https://worldoftanks.ru/";
    }

    @Test
    public void rateArticle() throws InterruptedException {
        var loginTest = new LoginTest(); // hehe
        loginTest.set(getDriver());
        loginTest.login();
        var mainPage = new MainPage(getDriver());

        waitUntilClickable(mainPage.getSupportLink());
        var supportPage = mainPage.clickSupport();

        supportPage.clickCardWithCategory("Аккаунт");
        supportPage.rateArticleWith("Как изменить пароль");
        assertTrue(supportPage.ratingIsSuccessful());

    }
}
