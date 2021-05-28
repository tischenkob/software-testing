package ru.ifmo.worldoftanks;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import ru.ifmo.PageObject;

public class MainPage extends PageObject {
    @Getter
    private final String url = "https://worldoftanks.ru";

    @FindBy(xpath = "//a[text() = 'создать аккаунт']")
    private WebElement registerLink;

    @FindBy(xpath = "//a[text() = 'Центр поддержки']")
    @CacheLookup
    private WebElement supportLink;

    @FindBy(xpath = "//a[contains(text(), 'Кланы') and @class='nav-submenu_link']")
    @CacheLookup
    private WebElement clanCenterLink;

    @FindBy(xpath = "//a[contains(@class, 'cm-link') and text() = 'Войти']")
    private WebElement enterLink;

    @FindBy(xpath = "//a//preceding-sibling::div[contains(@class, 'cm-notifications')]")
    private WebElement profileLink;

    @FindBy(xpath = "//a[contains(@class, 'cm-footer-logout_link') and contains(text(), 'Выйти')]")
    private WebElement exitLink;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAuthenticated() {
        return profileLink.isDisplayed();
    }

    public boolean isNotAuthenticated() {
        return !this.isAuthenticated();
    }

    public RegistrationPage clickRegister() {
        registerLink.click();
        return new RegistrationPage(driver);
    }

    public SupportPage clickSupport() {
        supportLink.click();
        return new SupportPage(driver);
    }

    public ClanPage clickClan() {
        clanCenterLink.click();
        return new ClanPage(driver);
    }

    public LoginPage clickEnter() {
        enterLink.click();
        return new LoginPage(driver);
    }

    public MainPage clickExit() {
        if (this.isAuthenticated()) {
            profileLink.click();
            exitLink.click();
        } else {
            throw new IllegalStateException();
        }
        return new MainPage(driver);
    }

}
