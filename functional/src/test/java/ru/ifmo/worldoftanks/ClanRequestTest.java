package ru.ifmo.worldoftanks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ifmo.AbstractUiTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClanRequestTest extends AbstractUiTest {

    @ParameterizedTest
    @CsvSource(value = {"[NNIK];Пожалуйста, не принимайте эту заявку. Я тестирую данный сайт."}, delimiter = ';')
    public void clanRequestTest(String givenName, String givenMessage) throws InterruptedException {
        var mainPage = new MainPage(getDriver());
        waitUntilClickable(mainPage.getClanCenterLink());
        var clanPage = mainPage.clickClan();
        waitUntilClickable(clanPage.getClanPortalLink());
        clanPage.openClanPortal();

        waitUntilClickable(clanPage.getSubMenuButton());
        clanPage.closeSubMenu();

        var loginTest = new LoginTest(); // hehe
        loginTest.set(getDriver());
        loginTest.login();

        new WebDriverWait(getDriver(), 5);
        clanPage.selectClanWith(givenName);
        clanPage.sendRequestWith(givenMessage);
        assertTrue(clanPage.submissionIsSuccessful());
    }

}
